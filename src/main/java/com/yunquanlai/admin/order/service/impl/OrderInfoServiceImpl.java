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
import com.yunquanlai.admin.system.dao.SysConfigDao;
import com.yunquanlai.admin.user.dao.UserInfoDao;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.api.comsumer.vo.OrderVO;
import com.yunquanlai.api.comsumer.vo.ProductOrderVO;
import com.yunquanlai.api.event.OrderDeliveryNotifyEvent;
import com.yunquanlai.api.event.OrderDistributeEvent;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.RRException;
import com.yunquanlai.utils.validator.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Autowired
    private SysConfigDao sysConfigDao;

    @Autowired
    private UserInfoDao userInfoDao;

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
    public void delete(Long id) {
        orderInfoDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        orderInfoDao.deleteBatch(ids);
    }

    @Override
    public R newOrder(OrderVO orderVO, UserInfoEntity user) throws ParseException {
        OrderDeliveryInfoEntity orderDeliveryInfoEntity = new OrderDeliveryInfoEntity();
        OrderInfoEntity orderInfoEntity = new OrderInfoEntity();
        List<OrderProductDetailEntity> orderProductDetailEntities = new ArrayList<>(16);
        BigDecimal bEmptyValue;
        String emptyValue = sysConfigDao.queryByKey("emptyValue");
        if (emptyValue == null) {
            throw new RRException("单个空桶价值未配置");
        }
        bEmptyValue = new BigDecimal(emptyValue);
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal amountTotal = BigDecimal.ZERO;
        BigDecimal amountDeliveryFee = BigDecimal.ZERO;
        BigDecimal deposit = BigDecimal.ZERO;

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
            if (productInfoEntity.getBucketType() == ProductInfoEntity.BUCKET_TYPE_RECYCLE) {
                deposit = deposit.add(bEmptyValue.multiply(new BigDecimal(productOrderVO.getCount())));
            }

            amountDeliveryFee = amountDeliveryFee.add(productInfoEntity.getDeliveryFee());
            OrderProductDetailEntity orderProductDetailEntity = new OrderProductDetailEntity();
            orderProductDetailEntity.setCount(productOrderVO.getCount());
            orderProductDetailEntity.setProductInfoId(productInfoEntity.getId());
            orderProductDetailEntity.setProductName(productInfoEntity.getName());
            orderProductDetailEntities.add(orderProductDetailEntity);
        }
        orderInfoEntity.setDeposit(deposit);
        orderInfoEntity.setAmount(amount);
        orderInfoEntity.setAmountTotal(amountTotal);
        orderInfoEntity.setAmountDeliveryFee(amountDeliveryFee);
        orderInfoEntity.setUsername(user.getUsername());
        orderInfoEntity.setUserInfoId(user.getId());
        orderInfoEntity.setCreationTime(new Date());
        orderInfoEntity.setStatus(OrderInfoEntity.STATUS_NEW);
        orderInfoEntity.setType(OrderInfoEntity.TYPE_NORMAL);
        orderInfoEntity.setPayType(OrderInfoEntity.PAY_TYPE_CASH);
        orderInfoEntity.setRemark(orderVO.getRemark());
        // 订单
        orderInfoDao.save(orderInfoEntity);
        orderDeliveryInfoEntity.setAddress(orderVO.getAddress());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (orderVO.getDeliveryTime() != null) {
            orderDeliveryInfoEntity.setDeliveryTime(sdf.parse(orderVO.getDeliveryTime()));
        }
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
        // TODO 发票，发票抬头(先不做)

        for (OrderProductDetailEntity orderProductDetailEntity : orderProductDetailEntities) {
            orderProductDetailEntity.setOrderInfoId(orderInfoEntity.getId());
            // 订单商品明细
            orderProductDetailDao.save(orderProductDetailEntity);
        }
        // todo 测试用，下单后直接走支付完成流程
        orderPay(orderInfoEntity.getId(), orderInfoEntity.getAmount().multiply(BigDecimal.TEN).multiply(BigDecimal.TEN));
        return R.ok().put("orderInfo", orderInfoEntity).put("orderDetail", orderProductDetailEntities).put("minDeposit", deposit);
    }

    @Override
    public void orderPay(Object outTradeNo, Object totalFee) {
        OrderInfoEntity orderInfoEntity = orderInfoDao.queryObject(outTradeNo, true);
        if (orderInfoEntity == null) {
            throw new RuntimeException("找不到有效的订单");
        }
        // 订单不是可支付状态,幂等性返回(已关闭的订单，如果收到支付完成通知，把它拉起来到已支付，然后配送，已关闭指的是不能发起支付)
        if (orderInfoEntity.getStatus() != OrderInfoEntity.STATUS_NEW && orderInfoEntity.getStatus() != OrderInfoEntity.STATUS_CLOSE) {
            return;
        }
        // 校验金额，微信返回的金额单位是分，我们先除以100
        BigDecimal wechatBackFee = new BigDecimal(totalFee.toString()).divide(BigDecimal.TEN).divide(BigDecimal.TEN);
        if (orderInfoEntity.getDeposit() != null && !orderInfoEntity.getDeposit().equals(BigDecimal.ZERO)) {
            // 订单包含押金
            if (!(orderInfoEntity.getAmount().add(orderInfoEntity.getDeposit())).equals(wechatBackFee)) {
                throw new RuntimeException("支付金额不等于订单金额");
            }
            // 更新用户押金
            UserInfoEntity userInfoEntity = userInfoDao.queryObject(orderInfoEntity.getUserInfoId(), true);
            userInfoEntity.setDepositAmount(userInfoEntity.getDepositAmount().add(orderInfoEntity.getDeposit()));
            userInfoEntity.setEnableDepositAmount(userInfoEntity.getEnableDepositAmount().add(orderInfoEntity.getDeposit()));
            userInfoDao.update(userInfoEntity);
        } else {
            // 订单不包含押金
            if (!orderInfoEntity.getAmount().equals(wechatBackFee)) {
                throw new RuntimeException("支付金额不等于订单金额");
            }
        }
        orderInfoEntity.setStatus(OrderInfoEntity.STATUS_PAID);
        orderInfoEntity.setPaidTime(new Date());
        orderInfoDao.update(orderInfoEntity);

        if (orderInfoEntity.getDistributeTime() != null && orderInfoEntity.getDistributeTime().after(new Date())) {
            // 还未到期望配送时间，先不处理配送单，等定时任务处理分配
            return;
        }

        OrderDeliveryInfoEntity orderDeliveryInfoEntity = orderDeliveryInfoDao.queryObjectByOrderId(orderInfoEntity.getId(), true);
        if (OrderDeliveryInfoEntity.STATUS_NEW != orderDeliveryInfoEntity.getStatus()) {
            logger.error("配送单" + orderDeliveryInfoEntity.getId() + "已支付并处理派送【" + orderDeliveryInfoEntity.getStatus() + "】");
            return;
        }
        orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_UN_DISTRIBUTE);
        orderDeliveryInfoDao.update(orderDeliveryInfoEntity);

        applicationContext.publishEvent(new OrderDistributeEvent(orderInfoEntity.getId()));
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
        Assert.isNotEqual(orderInfoEntity.getUserInfoId(), userId, "不能关闭别人的订单");
        Assert.isNotEqual(orderInfoEntity.getStatus(), OrderInfoEntity.STATUS_NEW, "订单不是可关闭状态");
        orderInfoEntity.setStatus(OrderInfoEntity.STATUS_CLOSE);
        orderInfoEntity.setCloseTime(new Date());
        orderInfoDao.update(orderInfoEntity);
    }

    @Override
    public R confirm(OrderVO orderVO, UserInfoEntity user) {
        List<OrderProductDetailEntity> orderProductDetailEntities = new ArrayList<>(16);
        BigDecimal bEmptyValue;
        String emptyValue = sysConfigDao.queryByKey("emptyValue");
        if (emptyValue == null) {
            throw new RRException("单个空桶价值未配置");
        }
        bEmptyValue = new BigDecimal(emptyValue);
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal deposit = BigDecimal.ZERO;

        for (ProductOrderVO productOrderVO : orderVO.getProductOrderVOList()) {
            // 计算订单总额，押金总额
            ProductInfoEntity productInfoEntity = productInfoDao.queryObject(productOrderVO.getProductInfoId(), false);
            if (productInfoEntity == null) {
                return R.error("找不到购买的商品【" + productOrderVO.getProductInfoId() + "】");
            }
            amount = amount.add(productInfoEntity.getAmount().multiply(new BigDecimal(productOrderVO.getCount())));
            if (productInfoEntity.getBucketType() == ProductInfoEntity.BUCKET_TYPE_RECYCLE) {
                deposit = deposit.add(bEmptyValue.multiply(new BigDecimal(productOrderVO.getCount())));
            }

            OrderProductDetailEntity orderProductDetailEntity = new OrderProductDetailEntity();
            orderProductDetailEntity.setCount(productOrderVO.getCount());
            orderProductDetailEntity.setProductInfoId(productInfoEntity.getId());
            orderProductDetailEntity.setProductName(productInfoEntity.getName());
            orderProductDetailEntity.setAmount(productInfoEntity.getAmount());
            orderProductDetailEntities.add(orderProductDetailEntity);
        }
        return R.ok().put("orderProductDetails", orderProductDetailEntities).put("deposit", deposit).put("amount", amount);
    }

    @Override
    public void markException(OrderInfoEntity orderInfoEntity, OrderDeliveryInfoEntity orderDeliveryInfoEntity) {
        orderDeliveryInfoDao.update(orderDeliveryInfoEntity);
        orderInfoDao.update(orderInfoEntity);
    }

    @Override
    public void payTimeOut(OrderInfoEntity temp) {
        OrderInfoEntity orderInfoEntity = orderInfoDao.queryObject(temp.getId(), true);
        if (orderInfoEntity.getStatus() == OrderInfoEntity.STATUS_NEW) {
            orderInfoEntity.setStatus(OrderInfoEntity.STATUS_CLOSE);
            OrderDeliveryInfoEntity orderDeliveryInfoEntity = orderDeliveryInfoDao.queryObjectByOrderId(temp.getId(), true);
            orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_CLOSE);
            orderDeliveryInfoDao.update(orderDeliveryInfoEntity);
            orderInfoDao.update(orderInfoEntity);
        }
    }

    @Override
    public void distributeOrder(OrderInfoEntity orderInfoEntity) {
        OrderDeliveryInfoEntity orderDeliveryInfoEntity = orderDeliveryInfoDao.queryObjectByOrderId(orderInfoEntity.getId(), true);
        if (OrderDeliveryInfoEntity.STATUS_NEW != orderDeliveryInfoEntity.getStatus()) {
            logger.error("配送单" + orderDeliveryInfoEntity.getId() + "已处理，状态【" + orderDeliveryInfoEntity.getStatus() + "】");
            return;
        }
        orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_UN_DISTRIBUTE);
        orderDeliveryInfoDao.update(orderDeliveryInfoEntity);

        applicationContext.publishEvent(new OrderDistributeEvent(orderInfoEntity.getId()));
    }

    /**
     * 检查库存，并预生成要扣除的库存数
     *
     * @param orderProductDetailEntities 订单所购买的商品
     * @param deliveryEndpointEntity     配送点实体
     *
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
