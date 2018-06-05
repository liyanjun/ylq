package com.yunquanlai.admin.order.service.impl;

import com.yunquanlai.admin.delivery.dao.DeliveryEndpointDao;
import com.yunquanlai.admin.order.dao.OrderDeliveryInfoDao;
import com.yunquanlai.admin.order.dao.OrderProductDetailDao;
import com.yunquanlai.admin.order.entity.OrderDeliveryInfoEntity;
import com.yunquanlai.admin.order.entity.OrderProductDetailEntity;
import com.yunquanlai.admin.product.dao.ProductInfoDao;
import com.yunquanlai.admin.product.dao.ProductStockDao;
import com.yunquanlai.admin.product.entity.ProductInfoEntity;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.api.comsumer.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yunquanlai.admin.order.dao.OrderInfoDao;
import com.yunquanlai.admin.order.entity.OrderInfoEntity;
import com.yunquanlai.admin.order.service.OrderInfoService;
import org.springframework.transaction.annotation.Transactional;


@Service("orderInfoService")
@Transactional(rollbackFor = Exception.class)
public class OrderInfoServiceImpl implements OrderInfoService {
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

    @Override
    public OrderInfoEntity queryObject(Integer id) {
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
    public boolean newOrder(OrderVO orderVO, UserInfoEntity user) {
        OrderDeliveryInfoEntity orderDeliveryInfoEntity = new OrderDeliveryInfoEntity();
        OrderInfoEntity orderInfoEntity = new OrderInfoEntity();
        List<OrderProductDetailEntity> orderProductDetailEntities = new ArrayList<>(16);
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal amountTotal = BigDecimal.ZERO;
        BigDecimal amountDeliveryFee = BigDecimal.ZERO;

        for (OrderVO.ProductOrderVO productOrderVO : orderVO.getProductOrderVOList()) {
            // 计算订单总额
            ProductInfoEntity productInfoEntity = productInfoDao.queryObject(productOrderVO.getProductInfoId(), false);
            amount = amount.add(productInfoEntity.getAmount().multiply(new BigDecimal(productOrderVO.getCount())));
            amountTotal = amountTotal.add(productInfoEntity.getAmountShow().multiply(new BigDecimal(productOrderVO.getCount())));
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
// TODO       orderDeliveryInfoEntity.setDeliveryTime(orderVO.getDeliveryTime());
        orderDeliveryInfoEntity.setLocationX(orderVO.getLocationX());
        orderDeliveryInfoEntity.setLocationY(orderVO.getLocationY());
        orderDeliveryInfoEntity.setOrderInfoId(orderInfoEntity.getId());
        orderDeliveryInfoEntity.setPhone(orderVO.getPhone());
        orderDeliveryInfoEntity.setName(orderVO.getName());
        orderDeliveryInfoEntity.setRemark(orderVO.getRemark());
        orderDeliveryInfoEntity.setSex(orderVO.getSex());
        orderDeliveryInfoEntity.setUserInfoId(user.getId());
        orderDeliveryInfoEntity.setOrderInfoId(orderInfoEntity.getId());
        // 配送单
        orderDeliveryInfoDao.save(orderDeliveryInfoEntity);
        // TODO 发票，发票抬头

        for (OrderProductDetailEntity orderProductDetailEntity : orderProductDetailEntities) {
            orderProductDetailEntity.setOrderInfoId(orderInfoEntity.getId());
            // 订单商品明细
            orderProductDetailDao.save(orderProductDetailEntity);
        }

        return true;
    }

}
