package com.yunquanlai.admin.user.service;


import com.yunquanlai.admin.user.entity.UserWithdrawDepositEntity;

import java.util.List;
import java.util.Map;

/**
 * 客户押金提现申请表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 15:44:08
 */
public interface UserWithdrawDepositService {
	
	UserWithdrawDepositEntity queryObject(Long id);
	
	List<UserWithdrawDepositEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserWithdrawDepositEntity userWithdrawDeposit);
	
	void update(UserWithdrawDepositEntity userWithdrawDeposit);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
