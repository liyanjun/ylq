package com.yunquanlai.admin.order.service.impl;

import com.yunquanlai.admin.product.dao.ProductInfoDao;
import com.yunquanlai.admin.product.dao.ProductStockDao;
import com.yunquanlai.admin.product.entity.ProductInfoEntity;
import com.yunquanlai.admin.product.service.ProductInfoService;
import com.yunquanlai.api.comsumer.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ProductInfoDao productInfoDao;

    @Autowired
    private ProductStockDao productStockDao;

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
    public boolean newOrder(OrderVO orderVO) {
        OrderInfoEntity orderInfoEntity = new OrderInfoEntity();
        // TODO 先找到配送点（判断一下是否有可用的配送员）

        for (OrderVO.ProductOrderVO productOrderVO : orderVO.getProductOrderVOList()) {
            ProductInfoEntity productInfoEntity = productInfoDao.queryObject(productOrderVO.getProductInfoId(), false);
            // TODO 根据配送点ID,商品ID查询库存
            // TODO 判断库存是否充足
            // TODO 计算订单金额，押金金额
        }
        //TODO 插入订单表，订单配送信息表，订单商品信息表(下单成功)


        return true;
    }

}
