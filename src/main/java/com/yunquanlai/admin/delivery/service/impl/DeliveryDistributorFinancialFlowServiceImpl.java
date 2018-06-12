package com.yunquanlai.admin.delivery.service.impl;

import com.yunquanlai.admin.delivery.dao.DeliveryDistributorFinancialFlowDao;
import com.yunquanlai.admin.delivery.entity.DeliveryDistributorFinancialFlowEntity;
import com.yunquanlai.admin.delivery.service.DeliveryDistributorFinancialFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;




@Service("deliveryDistributorFinancialFlowService")
@Transactional(rollbackFor = Exception.class)
public class DeliveryDistributorFinancialFlowServiceImpl implements DeliveryDistributorFinancialFlowService {
	@Autowired
	private DeliveryDistributorFinancialFlowDao deliveryDistributorFinancialFlowDao;
	
	@Override
	public DeliveryDistributorFinancialFlowEntity queryObject(Integer id){
		return deliveryDistributorFinancialFlowDao.queryObject(id,false);
	}
	
	@Override
	public List<DeliveryDistributorFinancialFlowEntity> queryList(Map<String, Object> map){
		return deliveryDistributorFinancialFlowDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return deliveryDistributorFinancialFlowDao.queryTotal(map);
	}
	
	@Override
	public void save(DeliveryDistributorFinancialFlowEntity deliveryDistributorFinancialFlow){
		deliveryDistributorFinancialFlowDao.save(deliveryDistributorFinancialFlow);
	}
	
	@Override
	public void update(DeliveryDistributorFinancialFlowEntity deliveryDistributorFinancialFlow){
		deliveryDistributorFinancialFlowDao.update(deliveryDistributorFinancialFlow);
	}
	
	@Override
	public void delete(Integer id){
		deliveryDistributorFinancialFlowDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		deliveryDistributorFinancialFlowDao.deleteBatch(ids);
	}
	
}
