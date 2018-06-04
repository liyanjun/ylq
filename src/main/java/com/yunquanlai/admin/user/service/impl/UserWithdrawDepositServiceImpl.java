package com.yunquanlai.admin.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yunquanlai.admin.user.dao.UserWithdrawDepositDao;
import com.yunquanlai.admin.user.entity.UserWithdrawDepositEntity;
import com.yunquanlai.admin.user.service.UserWithdrawDepositService;
import org.springframework.transaction.annotation.Transactional;


@Service("userWithdrawDepositService")
@Transactional(rollbackFor = Exception.class)
public class UserWithdrawDepositServiceImpl implements UserWithdrawDepositService {
	@Autowired
	private UserWithdrawDepositDao userWithdrawDepositDao;
	
	@Override
	public UserWithdrawDepositEntity queryObject(Long id){
		return userWithdrawDepositDao.queryObject(id,false);
	}
	
	@Override
	public List<UserWithdrawDepositEntity> queryList(Map<String, Object> map){
		return userWithdrawDepositDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userWithdrawDepositDao.queryTotal(map);
	}
	
	@Override
	public void save(UserWithdrawDepositEntity userWithdrawDeposit){
		userWithdrawDepositDao.save(userWithdrawDeposit);
	}
	
	@Override
	public void update(UserWithdrawDepositEntity userWithdrawDeposit){
		userWithdrawDepositDao.update(userWithdrawDeposit);
	}
	
	@Override
	public void delete(Long id){
		userWithdrawDepositDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		userWithdrawDepositDao.deleteBatch(ids);
	}
	
}
