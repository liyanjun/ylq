package com.yunquanlai.admin.order.dao;

import com.yunquanlai.admin.order.entity.OrderProductDetailEntity;
import com.yunquanlai.admin.system.dao.BaseDao;

import java.util.List;

/**
 * 订单商品信息表
 *
 * @author liyanjun
 * @email
 * @date 2018-06-04 22:42:21
 */
public interface OrderProductDetailDao extends BaseDao<OrderProductDetailEntity> {

    /**
     * 根据订单 ID 查询
     * @param orderInfoId
     * @return
     */
    List<OrderProductDetailEntity> queryListByOrderId(Long orderInfoId);
}
