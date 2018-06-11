package com.yunquanlai.admin.user.service.impl;

import com.yunquanlai.admin.user.dao.UserEmptyBucketFlowDao;
import com.yunquanlai.admin.user.entity.UserEmptyBucketFlowEntity;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.RRException;
import com.yunquanlai.utils.validator.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yunquanlai.admin.user.dao.UserInfoDao;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.admin.user.service.UserInfoService;
import org.springframework.transaction.annotation.Transactional;
import org.weixin4j.User;


@Service("userInfoService")
@Transactional(rollbackFor = Exception.class)
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private UserInfoDao userInfoDao;

	private UserEmptyBucketFlowDao userEmptyBucketFlowDao;
	
	@Override
	public UserInfoEntity queryObject(Long id){
		return userInfoDao.queryObject(id,false);
	}
	
	@Override
	public List<UserInfoEntity> queryList(Map<String, Object> map){
		return userInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userInfoDao.queryTotal(map);
	}

	@Override
	public void save(UserInfoEntity userInfo){
		userInfoDao.save(userInfo);
	}

/*
	@Override
	public void update(UserInfoEntity userInfo){
		userInfoDao.update(userInfo);
	}

	@Override
	public void delete(Long id){
		userInfoDao.delete(id);
	}

	@Override
	public void deleteBatch(Long[] ids){
		userInfoDao.deleteBatch(ids);
	}
*/

	@Override
	public UserInfoEntity queryObjectByUid(String uid) {
		return userInfoDao.queryObjectByUid(uid);
	}

	@Override
	public R recyclingEmptyBarrels(Long userInfoId, Integer number, Long deliveryDistributorId) {
		UserInfoEntity userInfoEntity = userInfoDao.queryObject(userInfoId,true);
		Assert.isNull(userInfoEntity,"找不到配送单的用户信息。");
		if(userInfoEntity.getEmptyBucketNumber().intValue() < number.intValue()){
			throw new RRException("用户持有空桶数大于回送空桶数。");
		}
		userInfoEntity.setEmptyBucketNumber(userInfoEntity.getEmptyBucketNumber() - number);
		userInfoDao.update(userInfoEntity);
		UserEmptyBucketFlowEntity userEmptyBucketFlowEntity = new UserEmptyBucketFlowEntity();
		userEmptyBucketFlowEntity.setBeforeEmptyBucket(userInfoEntity.getEmptyBucketNumber());
		userEmptyBucketFlowEntity.setUserInfoId(userInfoEntity.getId());
		userEmptyBucketFlowEntity.setEmptyBucketNumber(number);
		userEmptyBucketFlowEntity.setType(10);
		userEmptyBucketFlowEntity.setOperatorId(deliveryDistributorId);
		userEmptyBucketFlowEntity.setAfterEmptyBucket(userInfoEntity.getEmptyBucketNumber());
		userEmptyBucketFlowEntity.setCreationTime(new Date());
		userEmptyBucketFlowDao.save(userEmptyBucketFlowEntity);
		return R.ok().put("name",userInfoEntity.getUsername()).put("emptyBarrels",userInfoEntity.getEmptyBucketNumber());
	}

}
