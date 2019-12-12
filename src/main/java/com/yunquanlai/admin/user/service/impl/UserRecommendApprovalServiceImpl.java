package com.yunquanlai.admin.user.service.impl;

import com.yunquanlai.admin.user.dao.UserInfoDao;
import com.yunquanlai.admin.user.dao.UserRecommendApprovalDao;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.admin.user.entity.UserRecommendApprovalEntity;
import com.yunquanlai.admin.user.service.UserRecommendApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("userRecommenderApprovalService")
@Transactional(rollbackFor = Exception.class)
public class UserRecommendApprovalServiceImpl implements UserRecommendApprovalService {
	@Autowired
	private UserRecommendApprovalDao userRecommenderApprovalDao;

    @Autowired
    private UserInfoDao userInfoDao;
	
	@Override
	public UserRecommendApprovalEntity queryObject(Long id){
		return userRecommenderApprovalDao.queryObject(id,false);
	}
	
	@Override
	public List<UserRecommendApprovalEntity> queryList(Map<String, Object> map){
		return userRecommenderApprovalDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userRecommenderApprovalDao.queryTotal(map);
	}
	
	@Override
	public void save(UserRecommendApprovalEntity userRecommenderApproval){
		userRecommenderApprovalDao.save(userRecommenderApproval);
	}
	
	@Override
	public void update(UserRecommendApprovalEntity userRecommenderApproval){
        userRecommenderApproval.setApproveTime(new Date());
		Integer isApproved = userRecommenderApproval.getIsApproved();
		if(isApproved.equals(10)){
			UserInfoEntity userInfoEntity = userInfoDao.queryObject(userRecommenderApproval.getUserId(), true);
			userInfoEntity.setIsRecommender(10);
			userInfoDao.update(userInfoEntity);
		}
		userRecommenderApprovalDao.update(userRecommenderApproval);
	}
	
	@Override
	public void delete(Long id){
		userRecommenderApprovalDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		userRecommenderApprovalDao.deleteBatch(ids);
	}
	
}
