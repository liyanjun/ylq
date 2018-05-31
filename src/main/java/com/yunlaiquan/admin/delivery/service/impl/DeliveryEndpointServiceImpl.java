package com.yunlaiquan.admin.delivery.service.impl;

import com.yunlaiquan.admin.delivery.dao.DeliveryEndpointDao;
import com.yunlaiquan.admin.delivery.entity.DeliveryEndpointEntity;
import com.yunlaiquan.admin.delivery.service.DeliveryEndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service("deliveryEndpointService")
public class DeliveryEndpointServiceImpl implements DeliveryEndpointService {
	@Autowired
	private DeliveryEndpointDao deliveryEndpointDao;
	
	@Override
	public DeliveryEndpointEntity queryObject(Long id){
		return deliveryEndpointDao.queryObject(id);
	}
	
	@Override
	public List<DeliveryEndpointEntity> queryList(Map<String, Object> map){
		return deliveryEndpointDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return deliveryEndpointDao.queryTotal(map);
	}
	
	@Override
	public void save(DeliveryEndpointEntity deliveryEndpoint){
		deliveryEndpointDao.save(deliveryEndpoint);
	}
	
	@Override
	public void update(DeliveryEndpointEntity deliveryEndpoint){
		deliveryEndpointDao.update(deliveryEndpoint);
	}
	
	@Override
	public void delete(Long id){
		deliveryEndpointDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		deliveryEndpointDao.deleteBatch(ids);
	}
	
}
