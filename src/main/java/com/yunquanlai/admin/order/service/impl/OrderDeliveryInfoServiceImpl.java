package com.yunquanlai.admin.order.service.impl;

import com.yunquanlai.admin.delivery.dao.DeliveryDistributorDao;
import com.yunquanlai.admin.delivery.dao.DeliveryDistributorFinancialFlowDao;
import com.yunquanlai.admin.delivery.entity.DeliveryDistributorEntity;
import com.yunquanlai.admin.delivery.entity.DeliveryDistributorFinancialFlowEntity;
import com.yunquanlai.admin.order.dao.OrderDeliveryInfoDao;
import com.yunquanlai.admin.order.dao.OrderInfoDao;
import com.yunquanlai.admin.order.dao.OrderProductDetailDao;
import com.yunquanlai.admin.order.entity.OrderDeliveryInfoEntity;
import com.yunquanlai.admin.order.entity.OrderInfoEntity;
import com.yunquanlai.admin.order.entity.OrderProductDetailEntity;
import com.yunquanlai.admin.order.service.OrderDeliveryInfoService;
import com.yunquanlai.admin.product.entity.ProductInfoEntity;
import com.yunquanlai.admin.user.dao.UserEmptyBucketFlowDao;
import com.yunquanlai.admin.user.dao.UserInfoDao;
import com.yunquanlai.admin.user.entity.UserEmptyBucketFlowEntity;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.api.delivery.vo.DeliveryDateVO;
import com.yunquanlai.api.event.OrderDistributeEvent;
import com.yunquanlai.utils.validator.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("orderDeliveryInfoService")
@Transactional(rollbackFor = Exception.class)
public class OrderDeliveryInfoServiceImpl implements OrderDeliveryInfoService {

    Logger logger = LoggerFactory.getLogger(OrderDeliveryInfoServiceImpl.class);

    @Autowired
    private OrderDeliveryInfoDao orderDeliveryInfoDao;

    @Autowired
    private DeliveryDistributorDao deliveryDistributorDao;

    @Autowired
    private OrderInfoDao orderInfoDao;

    @Autowired
    private OrderProductDetailDao orderProductDetailDao;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private UserEmptyBucketFlowDao userEmptyBucketFlowDao;

    @Autowired
    private DeliveryDistributorFinancialFlowDao deliveryDistributorFinancialFlowDao;


    /**
     * 上下文对象
     */
    @Resource
    private ApplicationContext applicationContext;

    @Override
    public OrderDeliveryInfoEntity queryObject(Long id) {
        return orderDeliveryInfoDao.queryObject(id, false);
    }

    @Override
    public List<OrderDeliveryInfoEntity> queryList(Map<String, Object> map) {
        return orderDeliveryInfoDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return orderDeliveryInfoDao.queryTotal(map);
    }

    @Override
    public void save(OrderDeliveryInfoEntity orderDeliveryInfo) {
        orderDeliveryInfoDao.save(orderDeliveryInfo);
    }

    @Override
    public void update(OrderDeliveryInfoEntity orderDeliveryInfo) {
        orderDeliveryInfoDao.update(orderDeliveryInfo);
    }

    @Override
    public void delete(Long id) {
        orderDeliveryInfoDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        orderDeliveryInfoDao.deleteBatch(ids);
    }

    @Override
    public List<OrderDeliveryInfoEntity> queryByDistributorId(Map<String, Object> filter) {
        return orderDeliveryInfoDao.queryByDistributorId(filter);
    }

    @Override
    public void orderDelivery(DeliveryDistributorEntity deliveryDistributorEntity, OrderDeliveryInfoEntity orderDeliveryInfoEntity) {

        orderDeliveryInfoEntity = orderDeliveryInfoDao.queryObject(orderDeliveryInfoEntity.getId(), true);
        Assert.isNull(orderDeliveryInfoEntity, "找不到配送单信息");
        Assert.isEqual(orderDeliveryInfoEntity.getStatus(), OrderDeliveryInfoEntity.STATUS_DELIVERY_END, "该订单订单已标记送达");
        Assert.isNotEqual(orderDeliveryInfoEntity.getStatus(), OrderDeliveryInfoEntity.STATUS_ON_DELIVERY, "配送单不是配送中状态，无法标记送达");
        deliveryDistributorEntity = deliveryDistributorDao.queryObject(deliveryDistributorEntity.getId(), true);
        Assert.isNull(deliveryDistributorEntity, "找不到配送员信息");
        OrderInfoEntity orderInfoEntity = orderInfoDao.queryObject(orderDeliveryInfoEntity.getOrderInfoId(), true);
        Assert.isNull(orderInfoEntity, "找不到订单信息");
        List<OrderProductDetailEntity> orderProductDetailEntities = orderProductDetailDao.queryListByOrderId(orderInfoEntity.getId());
        UserInfoEntity userInfoEntity = userInfoDao.queryObject(orderInfoEntity.getUserInfoId(), true);
        Assert.isNull(userInfoEntity, "找不到订单用户信息");
        Date now = new Date();
        // 标记配送单送达
        orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_DELIVERY_END);
        orderDeliveryInfoEntity.setDeliveryEndTime(now);
        orderDeliveryInfoDao.update(orderDeliveryInfoEntity);
        // 减掉配送员 当前配送订单数，并分润，记录分润流水
        deliveryDistributorEntity.setOrderCount(deliveryDistributorEntity.getOrderCount() - 1);
        BigDecimal deliveryFee = orderInfoEntity.getAmountDeliveryFee();
        DeliveryDistributorFinancialFlowEntity deliveryDistributorFinancialFlow = new DeliveryDistributorFinancialFlowEntity();
        deliveryDistributorFinancialFlow.setDeliveryDistributorId(deliveryDistributorEntity.getId());
        deliveryDistributorFinancialFlow.setType(10);
        deliveryDistributorFinancialFlow.setBeforeAmount(deliveryDistributorEntity.getAmount());
        deliveryDistributorFinancialFlow.setAmount(deliveryFee);
        deliveryDistributorEntity.setAmount(deliveryDistributorEntity.getAmount().add(deliveryFee));
        deliveryDistributorFinancialFlow.setAfterAmount(deliveryDistributorEntity.getAmount());
        deliveryDistributorFinancialFlow.setCreationTime(new Date());
        deliveryDistributorFinancialFlowDao.save(deliveryDistributorFinancialFlow);
        deliveryDistributorDao.update(deliveryDistributorEntity);
        // 更新订单状态及配送结束时间
        orderInfoEntity.setStatus(OrderInfoEntity.STATUS_DELIVERY_END);
        orderInfoEntity.setDeliveryEndTime(now);
        orderInfoDao.update(orderInfoEntity);
        // 更新添加用户空桶信息，及空桶流水
        int emptyBucketNumber = 0;
        for (OrderProductDetailEntity orderProductDetailEntity : orderProductDetailEntities) {
            if (orderProductDetailEntity.getBucketType() == ProductInfoEntity.BUCKET_TYPE_RECYCLE) {
                emptyBucketNumber += orderProductDetailEntity.getCount();
            }
        }
        UserEmptyBucketFlowEntity userEmptyBucketFlowEntity = new UserEmptyBucketFlowEntity();
        userEmptyBucketFlowEntity.setBeforeEmptyBucket(userInfoEntity.getEmptyBucketNumber());
        userEmptyBucketFlowEntity.setUserInfoId(userInfoEntity.getId());
        userEmptyBucketFlowEntity.setUserName(userInfoEntity.getUsername());
        userEmptyBucketFlowEntity.setEmptyBucketNumber(emptyBucketNumber);
        userEmptyBucketFlowEntity.setType(20);
        userEmptyBucketFlowEntity.setOperatorId(orderDeliveryInfoEntity.getOrderInfoId());
        userInfoEntity.setEmptyBucketNumber(userInfoEntity.getEmptyBucketNumber() + emptyBucketNumber);
        userEmptyBucketFlowEntity.setAfterEmptyBucket(userInfoEntity.getEmptyBucketNumber());
        userEmptyBucketFlowEntity.setCreationTime(new Date());
        userEmptyBucketFlowDao.save(userEmptyBucketFlowEntity);
        userInfoDao.update(userInfoEntity);
    }

    @Override
    public OrderDeliveryInfoEntity queryObjectByOrderId(Long orderId) {
        return orderDeliveryInfoDao.queryObjectByOrderId(orderId, false);
    }

    @Override
    public void markerException(OrderDeliveryInfoEntity orderDeliveryInfoEntity, String exception) {
        OrderInfoEntity orderInfoEntity = orderInfoDao.queryObject(orderDeliveryInfoEntity.getOrderInfoId(), true);
        orderInfoEntity.setType(OrderInfoEntity.TYPE_EXCEPTION);
        orderInfoEntity.setException(exception);
        orderInfoDao.update(orderInfoEntity);
        orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_EXCEPTION);
        orderDeliveryInfoDao.update(orderDeliveryInfoEntity);
    }

    @Override
    public void distributorTimeOut(OrderDeliveryInfoEntity temp) {
        OrderDeliveryInfoEntity orderDeliveryInfoEntity = orderDeliveryInfoDao.queryObject(temp.getId(), true);
        if (orderDeliveryInfoEntity.getStatus() == OrderDeliveryInfoEntity.STATUS_UN_DISTRIBUTE) {
            orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_EXCEPTION);
            OrderInfoEntity orderInfoEntity = orderInfoDao.queryObject(orderDeliveryInfoEntity.getOrderInfoId(), true);
            orderInfoEntity.setType(OrderInfoEntity.TYPE_EXCEPTION);
            orderInfoEntity.setException("订单分配超时异常。");
            orderInfoDao.update(orderInfoEntity);
            orderDeliveryInfoDao.update(orderDeliveryInfoEntity);
        }

    }

    @Override
    public void distributeOrder(Long orderDeliveryId) {
        OrderDeliveryInfoEntity orderDeliveryInfoEntity = orderDeliveryInfoDao.queryObject(orderDeliveryId, true);
        if (OrderDeliveryInfoEntity.STATUS_PAID != orderDeliveryInfoEntity.getStatus()) {
            logger.error("配送单" + orderDeliveryInfoEntity.getId() + "已开始分配，状态【" + orderDeliveryInfoEntity.getStatus() + "】");
            return;
        }
        orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_UN_DISTRIBUTE);
        orderDeliveryInfoEntity.setDistributeTime(new Date());
        orderDeliveryInfoDao.update(orderDeliveryInfoEntity);

        applicationContext.publishEvent(new OrderDistributeEvent(orderDeliveryInfoEntity.getOrderInfoId()));
    }

    @Override
    public DeliveryDateVO queryDeliveryDate(Map<String, Object> filter) {
        return orderDeliveryInfoDao.queryDeliveryDate(filter);
    }
}
