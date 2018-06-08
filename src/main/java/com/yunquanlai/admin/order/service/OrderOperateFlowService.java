package com.yunquanlai.admin.order.service;

import com.yunquanlai.admin.order.entity.OrderOperateFlowEntity;

import java.util.List;
import java.util.Map;

/**
 * 订单手工处理流水记录表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 22:42:21
 */
public interface OrderOperateFlowService {
	
	OrderOperateFlowEntity queryObject(Integer id);
	
	List<OrderOperateFlowEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OrderOperateFlowEntity orderOpreateFlow);
	
	void update(OrderOperateFlowEntity orderOpreateFlow);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
