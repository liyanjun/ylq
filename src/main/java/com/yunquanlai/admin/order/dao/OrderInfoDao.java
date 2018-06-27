package com.yunquanlai.admin.order.dao;

import com.yunquanlai.admin.order.entity.OrderInfoEntity;
import com.yunquanlai.admin.system.dao.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * 订单信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 22:42:21
 */
public interface OrderInfoDao extends BaseDao<OrderInfoEntity> {

    List<OrderInfoEntity> queryListClient(Map<String,Object> map);
    List<OrderInfoEntity> queryUnpaidByUserId(Long userId);
}
