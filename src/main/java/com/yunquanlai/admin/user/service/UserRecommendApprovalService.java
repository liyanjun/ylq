package com.yunquanlai.admin.user.service;

import com.yunquanlai.admin.user.entity.UserRecommendApprovalEntity;

import java.util.List;
import java.util.Map;

/**
 * 推广审批申请表
 * 
 * @author weicc
 * @email 
 * @date 2018-07-21 18:00:31
 */
public interface UserRecommendApprovalService {
	
	UserRecommendApprovalEntity queryObject(Long id);
	
	List<UserRecommendApprovalEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserRecommendApprovalEntity userRecommenderApproval);
	
	void update(UserRecommendApprovalEntity userRecommenderApproval);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
