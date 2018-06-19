package com.yunquanlai.admin.order.dao;

import com.yunquanlai.admin.order.entity.OrderDeliveryInfoEntity;
import com.yunquanlai.admin.system.dao.BaseDao;
import com.yunquanlai.api.delivery.vo.DeliveryDateVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 订单配送信息表
 *
 * @author liyanjun
 * @email
 * @date 2018-06-04 22:42:21
 */
public interface OrderDeliveryInfoDao extends BaseDao<OrderDeliveryInfoEntity> {

    OrderDeliveryInfoEntity queryObjectByOrderId(@Param("id") Object id, @Param("isLock") boolean isLocks);

    /**
     * 查找配送员的配送单
     *
     * @param filter
     * @return
     */
    List<OrderDeliveryInfoEntity> queryByDistributorId(Map<String, Object> filter);

    DeliveryDateVO queryDeliveryDate(Map<String, Object> filter);
}
