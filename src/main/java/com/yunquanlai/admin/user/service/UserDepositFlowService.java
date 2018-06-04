package com.yunquanlai.admin.user.service;


import com.yunquanlai.admin.user.entity.UserDepositFlowEntity;

import java.util.List;
import java.util.Map;

/**
 * 客户押金流水信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 15:44:08
 */
public interface UserDepositFlowService {
	
	UserDepositFlowEntity queryObject(Long id);
	
	List<UserDepositFlowEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserDepositFlowEntity userDepositFlow);
	
	void update(UserDepositFlowEntity userDepositFlow);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
