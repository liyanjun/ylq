package com.yunquanlai.admin.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yunquanlai.admin.user.dao.UserDepositFlowDao;
import com.yunquanlai.admin.user.entity.UserDepositFlowEntity;
import com.yunquanlai.admin.user.service.UserDepositFlowService;
import org.springframework.transaction.annotation.Transactional;


@Service("userDepositFlowService")
@Transactional(rollbackFor = Exception.class)
public class UserDepositFlowServiceImpl implements UserDepositFlowService {
	@Autowired
	private UserDepositFlowDao userDepositFlowDao;
	
	@Override
	public UserDepositFlowEntity queryObject(Long id){
		return userDepositFlowDao.queryObject(id,false);
	}
	
	@Override
	public List<UserDepositFlowEntity> queryList(Map<String, Object> map){
		return userDepositFlowDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userDepositFlowDao.queryTotal(map);
	}
	
	@Override
	public void save(UserDepositFlowEntity userDepositFlow){
		userDepositFlowDao.save(userDepositFlow);
	}
	
	@Override
	public void update(UserDepositFlowEntity userDepositFlow){
		userDepositFlowDao.update(userDepositFlow);
	}
	
	@Override
	public void delete(Long id){
		userDepositFlowDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		userDepositFlowDao.deleteBatch(ids);
	}
	
}
