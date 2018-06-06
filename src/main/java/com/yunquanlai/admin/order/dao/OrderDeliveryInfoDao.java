package com.yunquanlai.admin.order.dao;

import com.yunquanlai.admin.order.entity.OrderDeliveryInfoEntity;
import com.yunquanlai.admin.system.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

/**
 * 订单配送信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 22:42:21
 */
public interface OrderDeliveryInfoDao extends BaseDao<OrderDeliveryInfoEntity> {

    OrderDeliveryInfoEntity queryObjectByOrderId(@Param("id")Object id, @Param("isLock") boolean isLocks);
}
