package com.yunquanlai.admin.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yunquanlai.admin.user.dao.UserInfoDao;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.admin.user.service.UserInfoService;
import org.springframework.transaction.annotation.Transactional;


@Service("userInfoService")
@Transactional(rollbackFor = Exception.class)
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Override
	public UserInfoEntity queryObject(Long id){
		return userInfoDao.queryObject(id,false);
	}
	
	@Override
	public List<UserInfoEntity> queryList(Map<String, Object> map){
		return userInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(UserInfoEntity userInfo){
		userInfoDao.save(userInfo);
	}
	
	@Override
	public void update(UserInfoEntity userInfo){
		userInfoDao.update(userInfo);
	}
	
	@Override
	public void delete(Long id){
		userInfoDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		userInfoDao.deleteBatch(ids);
	}

	@Override
	public UserInfoEntity queryObjectByUid(String uid) {
		return userInfoDao.queryObjectByUid(uid);
	}

}
