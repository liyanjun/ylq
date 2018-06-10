package com.yunquanlai.admin.user.service.impl;

import com.yunquanlai.admin.user.dao.UserEmptyBucketFlowDao;
import com.yunquanlai.admin.user.entity.UserEmptyBucketFlowEntity;
import com.yunquanlai.admin.user.service.UserEmptyBucketFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;




@Service("userEmptyBucketFlowService")
@Transactional(rollbackFor = Exception.class)
public class UserEmptyBucketFlowServiceImpl implements UserEmptyBucketFlowService {
	@Autowired
	private UserEmptyBucketFlowDao userEmptyBucketFlowDao;
	
	@Override
	public UserEmptyBucketFlowEntity queryObject(Long id){
		return userEmptyBucketFlowDao.queryObject(id,false);
	}
	
	@Override
	public List<UserEmptyBucketFlowEntity> queryList(Map<String, Object> map){
		return userEmptyBucketFlowDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userEmptyBucketFlowDao.queryTotal(map);
	}
	
	@Override
	public void save(UserEmptyBucketFlowEntity userEmptyBucketFlow){
		userEmptyBucketFlowDao.save(userEmptyBucketFlow);
	}
	
	@Override
	public void update(UserEmptyBucketFlowEntity userEmptyBucketFlow){
		userEmptyBucketFlowDao.update(userEmptyBucketFlow);
	}
	
	@Override
	public void delete(Long id){
		userEmptyBucketFlowDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		userEmptyBucketFlowDao.deleteBatch(ids);
	}
	
}
