package com.yunquanlai.admin.order.service;

import com.yunquanlai.admin.order.entity.OrderProductDetailEntity;

import java.util.List;
import java.util.Map;

/**
 * 订单商品信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 22:42:21
 */
public interface OrderProductDetailService {
	
	OrderProductDetailEntity queryObject(Integer id);
	
	List<OrderProductDetailEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OrderProductDetailEntity orderProductDetail);
	
	void update(OrderProductDetailEntity orderProductDetail);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<OrderProductDetailEntity> queryListByOrderId(Long orderInfoId);
}
