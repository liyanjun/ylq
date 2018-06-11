package com.yunquanlai.admin.user.service;


import com.yunquanlai.admin.user.entity.UserEmptyBucketFlowEntity;

import java.util.List;
import java.util.Map;

/**
 * 客户押金流水信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-10 12:04:01
 */
public interface UserEmptyBucketFlowService {
	
	UserEmptyBucketFlowEntity queryObject(Long id);
	
	List<UserEmptyBucketFlowEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserEmptyBucketFlowEntity userEmptyBucketFlow);
	
	void update(UserEmptyBucketFlowEntity userEmptyBucketFlow);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
