package com.yunquanlai.admin.delivery.service.impl;

import com.yunquanlai.admin.delivery.dao.DeliveryDistributorDao;
import com.yunquanlai.admin.delivery.entity.DeliveryDistributorEntity;
import com.yunquanlai.admin.delivery.service.DeliveryDistributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("deliveryDistributorService")
@Transactional(rollbackFor = Exception.class)
public class DeliveryDistributorServiceImpl implements DeliveryDistributorService {
	@Autowired
	private DeliveryDistributorDao deliveryDistributorDao;
	
	@Override
	public DeliveryDistributorEntity queryObject(Long id){
		return deliveryDistributorDao.queryObject(id,false);
	}
	
	@Override
	public List<DeliveryDistributorEntity> queryList(Map<String, Object> map){
		return deliveryDistributorDao.queryList(map);
	}

	@Override
	public List<DeliveryDistributorEntity> queryListByDeliveryEndpointId(Long deliveryEndpointId) {
		return deliveryDistributorDao.queryListByDeliveryEndpointId(deliveryEndpointId);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return deliveryDistributorDao.queryTotal(map);
	}
	
	@Override
	public void save(DeliveryDistributorEntity deliveryDistributor){
		deliveryDistributorDao.save(deliveryDistributor);
	}
	
	@Override
	public void update(DeliveryDistributorEntity deliveryDistributor){
		deliveryDistributorDao.update(deliveryDistributor);
	}
	
	@Override
	public void delete(Long id){
		deliveryDistributorDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		deliveryDistributorDao.deleteBatch(ids);
	}

    @Override
    public void updateDeliveryEndpointName(Map<String, Object> map) {

    }

	@Override
	public DeliveryDistributorEntity queryObjectByPhone(String mobile) {
		return deliveryDistributorDao.queryObjectByPhone(mobile);
	}
}
