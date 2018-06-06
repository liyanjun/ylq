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
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.api.comsumer.vo.OrderVO;
import com.yunquanlai.api.comsumer.vo.ProductOrderVO;
import com.yunquanlai.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import com.yunquanlai.admin.order.dao.OrderInfoDao;
import com.yunquanlai.admin.order.entity.OrderInfoEntity;
import com.yunquanlai.admin.order.service.OrderInfoService;
import org.springframework.transaction.annotation.Transactional;


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
        orderInfoEntity.setRemark(orderVO.getRemark());
        // 订单
        orderInfoDao.save(orderInfoEntity);
        orderDeliveryInfoEntity.setAddress(orderVO.getAddress());
        // TODO orderDeliveryInfoEntity.setDeliveryTime(orderVO.getDeliveryTime());
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
        orderInfoDao.update(orderInfoEntity);

        OrderDeliveryInfoEntity orderDeliveryInfoEntity = orderDeliveryInfoDao.queryObjectByOrderId(orderInfoEntity.getId(), true);
        if (OrderDeliveryInfoEntity.STATUS_NEW != orderDeliveryInfoEntity.getStatus()) {
            logger.error("配送单" + orderDeliveryInfoEntity.getId() + "已支付并处理派送【" + orderDeliveryInfoEntity.getStatus() + "】");
            return;
        }
        orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_UN_DISTRIBUTE);

    }

    @Override
    public void orderDelivery(Object orderId) {
        OrderDeliveryInfoEntity orderDeliveryInfoEntity = null;
        try {
            orderDeliveryInfoEntity = orderDeliveryInfoDao.queryObjectByOrderId(orderId, true);
            if (OrderDeliveryInfoEntity.STATUS_UN_DISTRIBUTE != orderDeliveryInfoEntity.getStatus()) {
                logger.error("配送单" + orderDeliveryInfoEntity.getId() + "已处理派送【" + orderDeliveryInfoEntity.getStatus() + "】");
                return;
            }

            // 准备选出的配送员
            DeliveryDistributorEntity deliveryDistributorEntity = pickDeliveryDistributor(orderDeliveryInfoEntity);
            if (deliveryDistributorEntity == null) {
                // 找不到配送员，标记异常
                orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_EXCEPTION);
                orderDeliveryInfoDao.update(orderDeliveryInfoEntity);
                return;
            }
            orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_ON_DELIVERY);
            orderDeliveryInfoEntity.setDeliveryDistributorId(deliveryDistributorEntity.getId());
            orderDeliveryInfoDao.update(orderDeliveryInfoEntity);
            // todo 处理推送
        } catch (Exception e) {
            logger.error("订单派送异常", e);
            if (orderDeliveryInfoEntity != null) {
                orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_EXCEPTION);
                orderDeliveryInfoDao.update(orderDeliveryInfoEntity);
            }
        }
    }

    /**
     * @param orderDeliveryInfoEntity
     * @return
     */
    private DeliveryDistributorEntity pickDeliveryDistributor(OrderDeliveryInfoEntity orderDeliveryInfoEntity) {
        // 找出所有配送点
        List<DeliveryEndpointEntity> deliveryEndpointEntities = deliveryEndpointDao.queryList(null);
        for (DeliveryEndpointEntity deliveryEndpointEntity : deliveryEndpointEntities) {
            // 求出x,y的差值的绝对值，即为距离
            BigDecimal x = orderDeliveryInfoEntity.getLocationX().subtract(deliveryEndpointEntity.getLocationX()).abs();
            BigDecimal y = orderDeliveryInfoEntity.getLocationY().subtract(deliveryEndpointEntity.getLocationY()).abs();
            BigDecimal distance = x.pow(2).add(y.pow(2));
            // 不用开方，因为开方了对比大小还是一样的。
            deliveryEndpointEntity.setDistance(distance);
        }
        // 按照距离排序
        Collections.sort(deliveryEndpointEntities);
        for (DeliveryEndpointEntity deliveryEndpointEntity : deliveryEndpointEntities) {
            //TODO 判断配送点库存
            DeliveryDistributorEntity deliveryDistributorEntity = deliveryDistributorDao.pickOne(deliveryEndpointEntity.getId());
            if (deliveryDistributorEntity != null) {
                // 分配订单，配送中订单数加一
                deliveryDistributorEntity.setOrderCount(deliveryDistributorEntity.getOrderCount() + 1);
                deliveryDistributorDao.update(deliveryDistributorEntity);
                return deliveryDistributorEntity;
            }
            // 该配送点找不到，找下一个配送点
        }
        // 全部都找不到，返回null
        return null;
    }

}
