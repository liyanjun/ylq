package com.yunquanlai.admin.user.service.impl;

import com.yunquanlai.admin.user.entity.UserDepositVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yunquanlai.admin.user.dao.UserDepositDao;
import com.yunquanlai.admin.user.entity.UserDepositEntity;
import com.yunquanlai.admin.user.service.UserDepositService;
import org.springframework.transaction.annotation.Transactional;


@Service("userDepositService")
@Transactional(rollbackFor = Exception.class)
public class UserDepositServiceImpl implements UserDepositService {
	@Autowired
	private UserDepositDao userDepositDao;
	
	@Override
	public UserDepositEntity queryObject(Long id){
		return userDepositDao.queryObject(id,false);
	}
	
	@Override
	public List<UserDepositEntity> queryList(Map<String, Object> map){
		return userDepositDao.queryList(map);
	}

	@Override
	public List<UserDepositVO> queryDepositList(Map<String, Object> map) {
		return userDepositDao.queryDepositList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return userDepositDao.queryTotal(map);
	}
	
	@Override
	public void save(UserDepositEntity userDeposit){
		userDepositDao.save(userDeposit);
	}
	
	@Override
	public void update(UserDepositEntity userDeposit){
		userDepositDao.update(userDeposit);
	}
	
	@Override
	public void delete(Long id){
		userDepositDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		userDepositDao.deleteBatch(ids);
	}
	
}
