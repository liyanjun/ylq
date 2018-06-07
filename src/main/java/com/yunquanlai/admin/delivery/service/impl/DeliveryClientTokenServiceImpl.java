package com.yunquanlai.admin.delivery.service.impl;

import com.yunquanlai.admin.delivery.dao.DeliveryClientTokenDao;
import com.yunquanlai.admin.delivery.entity.DeliveryClientTokenEntity;
import com.yunquanlai.admin.delivery.service.DeliveryClientTokenService;
import com.yunquanlai.admin.user.entity.UserClientTokenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;


@Service("deliveryClientTokenService")
@Transactional(rollbackFor = Exception.class)
public class DeliveryClientTokenServiceImpl implements DeliveryClientTokenService {
	@Autowired
	private DeliveryClientTokenDao deliveryClientTokenDao;
	
	@Override
	public DeliveryClientTokenEntity queryObject(Long id){
		return deliveryClientTokenDao.queryObject(id,false);
	}
	
	@Override
	public List<DeliveryClientTokenEntity> queryList(Map<String, Object> map){
		return deliveryClientTokenDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return deliveryClientTokenDao.queryTotal(map);
	}
	
	@Override
	public void save(DeliveryClientTokenEntity deliveryClientToken){
		deliveryClientTokenDao.save(deliveryClientToken);
	}
	
	@Override
	public void update(DeliveryClientTokenEntity deliveryClientToken){
		deliveryClientTokenDao.update(deliveryClientToken);
	}
	
	@Override
	public void delete(Long id){
		deliveryClientTokenDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		deliveryClientTokenDao.deleteBatch(ids);
	}

	@Override
	@CacheEvict(value = "tokens", key = "#oldToken")
	public void update(DeliveryClientTokenEntity deliveryClientTokenEntity, String oldToken) {
		deliveryClientTokenDao.update(deliveryClientTokenEntity);
	}

	@Override
	@Cacheable(value="tokens", key="#token")
	public DeliveryClientTokenEntity queryByToken(String token) {
		return deliveryClientTokenDao.queryObjectByToken(token);
	}

	@Override
	public DeliveryClientTokenEntity queryByDistributorId(Long userId) {
		return deliveryClientTokenDao.queryByDistributorId(userId);
	}

}
