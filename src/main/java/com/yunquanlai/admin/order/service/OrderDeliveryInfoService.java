package com.yunquanlai.admin.order.service;

import com.yunquanlai.admin.order.entity.OrderDeliveryInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 订单配送信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 22:42:21
 */
public interface OrderDeliveryInfoService {
	
	OrderDeliveryInfoEntity queryObject(Integer id);
	
	List<OrderDeliveryInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OrderDeliveryInfoEntity orderDeliveryInfo);
	
	void update(OrderDeliveryInfoEntity orderDeliveryInfo);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
