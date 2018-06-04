package com.yunquanlai.admin.order.service;

import com.yunquanlai.admin.order.entity.OrderOpreateFlowEntity;

import java.util.List;
import java.util.Map;

/**
 * 订单手工处理流水记录表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 22:42:21
 */
public interface OrderOpreateFlowService {
	
	OrderOpreateFlowEntity queryObject(Integer id);
	
	List<OrderOpreateFlowEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OrderOpreateFlowEntity orderOpreateFlow);
	
	void update(OrderOpreateFlowEntity orderOpreateFlow);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
