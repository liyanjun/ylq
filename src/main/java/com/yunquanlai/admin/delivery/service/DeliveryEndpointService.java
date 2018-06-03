package com.yunquanlai.admin.delivery.service;


import com.yunquanlai.admin.delivery.entity.DeliveryEndpointEntity;

import java.util.List;
import java.util.Map;

/**
 * 配送点信息
 * 
 * @author weicc
 * @email 
 * @date 2018-05-31 15:26:38
 */
public interface DeliveryEndpointService {
	
	DeliveryEndpointEntity queryObject(Long id);
	
	List<DeliveryEndpointEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DeliveryEndpointEntity deliveryEndpoint);
	
	void update(DeliveryEndpointEntity deliveryEndpoint);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
