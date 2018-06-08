package com.yunquanlai.admin.order.service.impl;

import com.yunquanlai.admin.delivery.dao.DeliveryDistributorDao;
import com.yunquanlai.admin.delivery.dao.DeliveryEndpointDao;
import com.yunquanlai.admin.delivery.entity.DeliveryDistributorEntity;
import com.yunquanlai.admin.delivery.entity.DeliveryEndpointEntity;
import com.yunquanlai.admin.order.dao.OrderDeliveryInfoDao;
import com.yunquanlai.admin.order.dao.OrderProductDetailDao;
import com.yunquanlai.admin.order.entity.OrderDeliveryInfoEntity;
import com.yunquanlai.admin.order.entity.OrderProductDetailEntity;
import com.yunquanlai.admin.product.dao.ProductInfoDao;
import com.yunquanlai.admin.product.dao.ProductStockDao;
import com.yunquanlai.admin.product.entity.ProductInfoEntity;
import com.yunquanlai.admin.product.entity.ProductStockEntity;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.api.comsumer.vo.OrderVO;
import com.yunquanlai.api.comsumer.vo.ProductOrderVO;
import com.yunquanlai.api.event.OrderDeliveryNotifyEvent;
import com.yunquanlai.api.event.OrderPaidEvent;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.validator.Assert;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import com.yunquanlai.admin.order.dao.OrderInfoDao;
import com.yunquanlai.admin.order.entity.OrderInfoEntity;
import com.yunquanlai.admin.order.service.OrderInfoService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("orderInfoService")
@Transactional(rollbackFor = Exception.class)
public class OrderInfoServiceImpl implements OrderInfoService {
    Logger logger = LoggerFactory.getLogger(OrderInfoServiceImpl.class);
    @Autowired
    private OrderInfoDao orderInfoDao;

    @Autowired
    private OrderProductDetailDao orderProductDetailDao;

    @Autowired
    private OrderDeliveryInfoDao orderDeliveryInfoDao;

    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private ProductStockDao productStockDao;

    @Autowired
    private DeliveryEndpointDao deliveryEndpointDao;

    @Autowired
    private DeliveryDistributorDao deliveryDistributorDao;


    /**
     * 上下文对象
     */
    @Resource
    private ApplicationContext applicationContext;


    @Override
    public OrderInfoEntity queryObject(Long id) {
        return orderInfoDao.queryObject(id, false);
    }

    @Override
    public List<OrderInfoEntity> queryList(Map<String, Object> map) {
        return orderInfoDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return orderInfoDao.queryTotal(map);
    }

    @Override
    public void save(OrderInfoEntity orderInfo) {
        orderInfoDao.save(orderInfo);
    }

    @Override
    public void update(OrderInfoEntity orderInfo) {
        orderInfoDao.update(orderInfo);
    }

    @Override
    public void delete(Integer id) {
        orderInfoDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        orderInfoDao.deleteBatch(ids);
    }

    @Override
    public R newOrder(OrderVO orderVO, UserInfoEntity user) {
        OrderDeliveryInfoEntity orderDeliveryInfoEntity = new OrderDeliveryInfoEntity();
        OrderInfoEntity orderInfoEntity = new OrderInfoEntity();
        List<OrderProductDetailEntity> orderProductDetailEntities = new ArrayList<>(16);
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal amountTotal = BigDecimal.ZERO;
        BigDecimal amountDeliveryFee = BigDecimal.ZERO;

        for (ProductOrderVO productOrderVO : orderVO.getProductOrderVOList()) {
            // 计算订单总额
            ProductInfoEntity productInfoEntity = productInfoDao.queryObject(productOrderVO.getProductInfoId(), false);
            if (productInfoEntity == null) {
                return R.error("找不到购买的商品");
            }
            amount = amount.add(productInfoEntity.getAmount().multiply(new BigDecimal(productOrderVO.getCount())));
            if (productInfoEntity.getAmountShow() != null) {
                amountTotal = amountTotal.add(productInfoEntity.getAmountShow().multiply(new BigDecimal(productOrderVO.getCount())));
            }
            amountDeliveryFee = amountDeliveryFee.add(productInfoEntity.getDeliveryFee());
            OrderProductDetailEntity orderProductDetailEntity = new OrderProductDetailEntity();
            orderProductDetailEntity.setCount(productOrderVO.getCount());
            orderProductDetailEntity.setProductInfoId(productInfoEntity.getId());
            orderProductDetailEntity.setProductName(productInfoEntity.getName());
            orderProductDetailEntities.add(orderProductDetailEntity);
        }
        orderInfoEntity.setAmount(amount);
        orderInfoEntity.setAmountTotal(amountTotal);
        orderInfoEntity.setAmountDeliveryFee(amountDeliveryFee);
        orderInfoEntity.setUsername(user.getUsername());
        orderInfoEntity.setUserInfoId(user.getId());
        orderInfoEntity.setCreationTime(new Date());
        orderInfoEntity.setStatus(OrderInfoEntity.STATUS_NEW);
        orderInfoEntity.setType(OrderInfoEntity.TYPE_NORMAL);
        orderInfoEntity.setRemark(orderVO.getRemark());
        // 订单
        orderInfoDao.save(orderInfoEntity);
        orderDeliveryInfoEntity.setAddress(orderVO.getAddress());
        // TODO orderDeliveryInfoEntity.setDeliveryTime(orderVO.getDeliveryTime()); 可能需要一个待配送订单表
        orderDeliveryInfoEntity.setLocationX(orderVO.getLocationX());
        orderDeliveryInfoEntity.setLocationY(orderVO.getLocationY());
        orderDeliveryInfoEntity.setOrderInfoId(orderInfoEntity.getId());
        orderDeliveryInfoEntity.setPhone(orderVO.getPhone());
        orderDeliveryInfoEntity.setName(orderVO.getName());
        orderDeliveryInfoEntity.setRemark(orderVO.getRemark());
        orderDeliveryInfoEntity.setSex(orderVO.getSex());
        orderDeliveryInfoEntity.setUserInfoId(user.getId());
        orderDeliveryInfoEntity.setOrderInfoId(orderInfoEntity.getId());
        orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_NEW);
        orderDeliveryInfoEntity.setCreationTime(new Date());
        // 配送单
        orderDeliveryInfoDao.save(orderDeliveryInfoEntity);
        // TODO 发票，发票抬头

        for (OrderProductDetailEntity orderProductDetailEntity : orderProductDetailEntities) {
            orderProductDetailEntity.setOrderInfoId(orderInfoEntity.getId());
            // 订单商品明细
            orderProductDetailDao.save(orderProductDetailEntity);
        }

        return R.ok().put("orderInfo", orderInfoEntity).put("orderDetail", orderProductDetailEntities);
    }

    @Override
    public void orderPay(Object outTradeNo, Object totalFee) {
        OrderInfoEntity orderInfoEntity = orderInfoDao.queryObject(outTradeNo, true);
        if (orderInfoEntity == null) {
            throw new RuntimeException("找不到有效的订单");
        }
        // 订单不是可支付状态,幂等性返回(已关闭的订单，如果收到支付完成通知，把它拉起来到已支付，然后配送，已关闭指的是不能发起支付)
        if (orderInfoEntity.getStatus() != OrderInfoEntity.STATUS_NEW || orderInfoEntity.getStatus() != OrderInfoEntity.STATUS_CLOSE) {
            return;
        }
        BigDecimal wechatBackFee = new BigDecimal(Integer.parseInt(totalFee.toString()));
        wechatBackFee = wechatBackFee.divide(BigDecimal.TEN).divide(BigDecimal.TEN);
        if (!orderInfoEntity.getAmount().equals(wechatBackFee)) {
            throw new RuntimeException("支付金额不等于订单金额");
        }

        orderInfoEntity.setStatus(OrderInfoEntity.STATUS_PAID);
        orderInfoEntity.setPaidTime(new Date());
        orderInfoDao.update(orderInfoEntity);

        OrderDeliveryInfoEntity orderDeliveryInfoEntity = orderDeliveryInfoDao.queryObjectByOrderId(orderInfoEntity.getId(), true);
        if (OrderDeliveryInfoEntity.STATUS_NEW != orderDeliveryInfoEntity.getStatus()) {
            logger.error("配送单" + orderDeliveryInfoEntity.getId() + "已支付并处理派送【" + orderDeliveryInfoEntity.getStatus() + "】");
            return;
        }
        orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_UN_DISTRIBUTE);
        applicationContext.publishEvent(new OrderPaidEvent(orderInfoEntity.getId()));
    }

    @Override
    public boolean findDeliveryDistributor(List<OrderProductDetailEntity> orderProductDetailEntities, OrderDeliveryInfoEntity orderDeliveryInfoEntity, DeliveryEndpointEntity deliveryEndpointEntity) {
        //先锁配送点，再开始锁配送点库存，否则有可能造成死锁
        deliveryEndpointDao.queryObject(deliveryEndpointEntity.getId(), true);
        DeliveryDistributorEntity deliveryDistributorEntity;
        List<ProductStockEntity> productStockEntities;

        deliveryDistributorEntity = deliveryDistributorDao.pickOne(deliveryEndpointEntity.getId());
        if (deliveryDistributorEntity == null) {
            throw new RuntimeException();
            // 该配送点找不到能送的人，找下一个配送点
        }

        productStockEntities = checkStock(orderProductDetailEntities, deliveryEndpointEntity);
        if (productStockEntities.isEmpty()) {
            // 库存不足，回滚之前扣的库存，找下一个点
            throw new RuntimeException();
        }
        //有配送员，有库存,扣库存
        for (ProductStockEntity productStockEntity : productStockEntities) {
            productStockDao.update(productStockEntity);
        }
        // 分配订单，配送中订单数加一
        deliveryDistributorEntity.setOrderCount(deliveryDistributorEntity.getOrderCount() + 1);
        deliveryDistributorDao.update(deliveryDistributorEntity);

        // 更新订单分配时间
        OrderInfoEntity orderInfoEntity = new OrderInfoEntity();
        orderInfoEntity.setId(orderDeliveryInfoEntity.getOrderInfoId());
        orderInfoEntity.setDistributeTime(new Date());
        orderInfoEntity.setStatus(OrderInfoEntity.STATUS_ON_DELIVERY);
        orderInfoDao.update(orderInfoEntity);

        orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_ON_DELIVERY);
        orderDeliveryInfoEntity.setDeliveryDistributorId(deliveryDistributorEntity.getId());
        orderDeliveryInfoDao.update(orderDeliveryInfoEntity);
        applicationContext.publishEvent(new OrderDeliveryNotifyEvent(deliveryDistributorEntity.getClientId()));
        return true;
    }

    @Override
    public void closeOrder(Long orderId, Long userId) {
        OrderInfoEntity orderInfoEntity = orderInfoDao.queryObject(orderId, true);
        Assert.isEqual(orderInfoEntity.getUserInfoId(), userId, "不能关闭别人的订单");
        Assert.isEqual(orderInfoEntity.getStatus(), OrderInfoEntity.STATUS_NEW, "订单不是可关闭状态");
        orderInfoEntity.setStatus(OrderInfoEntity.STATUS_CLOSE);
        orderInfoEntity.setCloseTime(new Date());
        orderInfoDao.update(orderInfoEntity);
    }

    /**
     * 检查库存，并预生成要扣除的库存数
     *
     * @param orderProductDetailEntities 订单所购买的商品
     * @param deliveryEndpointEntity     配送点实体
     * @return
     */
    private List<ProductStockEntity> checkStock(List<OrderProductDetailEntity> orderProductDetailEntities, DeliveryEndpointEntity deliveryEndpointEntity) {
        List<ProductStockEntity> productStockEntities = new ArrayList<>(16);
        for (OrderProductDetailEntity orderProductDetailEntity : orderProductDetailEntities) {
            ProductStockEntity productStockEntity = productStockDao.queryByDeliveryEndpointIdAndProductId(orderProductDetailEntity.getProductInfoId(), deliveryEndpointEntity.getId(), true);
            if (!(productStockEntity.getCount().intValue() >= orderProductDetailEntity.getCount().intValue())) {
                // 库存不足
                return Collections.emptyList();
            }
            productStockEntity.setCount(productStockEntity.getCount() - orderProductDetailEntity.getCount());
            productStockEntities.add(productStockEntity);
        }
        return productStockEntities;
    }

}
