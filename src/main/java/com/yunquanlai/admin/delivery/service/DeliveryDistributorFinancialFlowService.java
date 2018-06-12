package com.yunquanlai.admin.delivery.service;

import com.yunquanlai.admin.delivery.entity.DeliveryDistributorFinancialFlowEntity;

import java.util.List;
import java.util.Map;

/**
 * 配送员收入信息流水
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-11 14:51:36
 */
public interface DeliveryDistributorFinancialFlowService {
	
	DeliveryDistributorFinancialFlowEntity queryObject(Integer id);
	
	List<DeliveryDistributorFinancialFlowEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DeliveryDistributorFinancialFlowEntity deliveryDistributorFinancialFlow);
	
	void update(DeliveryDistributorFinancialFlowEntity deliveryDistributorFinancialFlow);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
