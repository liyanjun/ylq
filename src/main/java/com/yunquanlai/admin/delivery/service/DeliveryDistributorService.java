package com.yunquanlai.admin.delivery.service;

import com.yunquanlai.admin.delivery.entity.DeliveryDistributorEntity;

import java.util.List;
import java.util.Map;

/**
 * 配送员信息
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 08:25:11
 */
public interface DeliveryDistributorService {
	
	DeliveryDistributorEntity queryObject(Long id);
	
	List<DeliveryDistributorEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DeliveryDistributorEntity deliveryDistributor);
	
	void update(DeliveryDistributorEntity deliveryDistributor);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	void updateDeliveryEndpointName(Map<String, Object> map);

}
