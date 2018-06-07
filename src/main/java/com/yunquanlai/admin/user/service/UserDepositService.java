package com.yunquanlai.admin.user.service;


import com.yunquanlai.admin.user.entity.UserDepositEntity;
import com.yunquanlai.admin.user.entity.UserDepositVO;

import java.util.List;
import java.util.Map;

/**
 * 客户押金信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 15:44:08
 */
public interface UserDepositService {
	
	UserDepositEntity queryObject(Long id);
	
	List<UserDepositEntity> queryList(Map<String, Object> map);

	List<UserDepositVO> queryDepositList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserDepositEntity userDeposit);
	
	void update(UserDepositEntity userDeposit);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
