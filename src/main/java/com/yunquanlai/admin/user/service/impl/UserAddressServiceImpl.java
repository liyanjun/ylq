package com.yunquanlai.admin.user.service.impl;

import com.yunquanlai.admin.user.dao.UserAddressDao;
import com.yunquanlai.admin.user.entity.UserAddressEntity;
import com.yunquanlai.admin.user.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;




@Service("userAddressService")
@Transactional(rollbackFor = Exception.class)
public class UserAddressServiceImpl implements UserAddressService {
	@Autowired
	private UserAddressDao userAddressDao;
	
	@Override
	public UserAddressEntity queryObject(Long id){
		return userAddressDao.queryObject(id,false);
	}

	@Override
	public List<UserAddressEntity> queryList(Map<String, Object> map){
		return userAddressDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userAddressDao.queryTotal(map);
	}
	
	@Override
	public void save(UserAddressEntity userAddress){
		userAddressDao.save(userAddress);
	}
	
	@Override
	public void update(UserAddressEntity userAddress){
		userAddressDao.update(userAddress);
	}
	
	@Override
	public void delete(Long id){
		userAddressDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		userAddressDao.deleteBatch(ids);
	}
	
}
