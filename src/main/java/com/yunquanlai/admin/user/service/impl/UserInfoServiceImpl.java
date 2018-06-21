package com.yunquanlai.admin.user.service.impl;

import com.yunquanlai.admin.order.dao.OrderDeliveryInfoDao;
import com.yunquanlai.admin.order.entity.OrderDeliveryInfoEntity;
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


@Service("userInfoService")
@Transactional(rollbackFor = Exception.class)
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private UserEmptyBucketFlowDao userEmptyBucketFlowDao;

    @Autowired
    private OrderDeliveryInfoDao orderDeliveryInfoDao;

    @Override
    public UserInfoEntity queryObject(Long id) {
        return userInfoDao.queryObject(id, false);
    }

    @Override
    public List<UserInfoEntity> queryList(Map<String, Object> map) {
        return userInfoDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return userInfoDao.queryTotal(map);
    }

    @Override
    public void save(UserInfoEntity userInfo) {
        userInfoDao.save(userInfo);
    }

    @Override
    public UserInfoEntity queryObjectByUid(String uid) {
        return userInfoDao.queryObjectByUid(uid);
    }

    @Override
    public R recyclingEmptyBarrels(OrderDeliveryInfoEntity orderDeliveryInfoEntity, Integer number, Long deliveryDistributorId) {
        orderDeliveryInfoEntity = orderDeliveryInfoDao.queryObject(orderDeliveryInfoEntity.getId(), true);
        orderDeliveryInfoEntity.setEmptyBarrels(orderDeliveryInfoEntity.getEmptyBarrels() + number);
        orderDeliveryInfoDao.update(orderDeliveryInfoEntity);
        UserInfoEntity userInfoEntity = userInfoDao.queryObject(orderDeliveryInfoEntity.getUserInfoId(), true);
        Assert.isNull(userInfoEntity, "找不到配送单的用户信息。");
        if (userInfoEntity.getEmptyBucketNumber().intValue() < number.intValue()) {
            throw new RRException("用户持有空桶数小于回送空桶数。");
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
        return R.ok().put("name", userInfoEntity.getUsername()).put("emptyBarrels", userInfoEntity.getEmptyBucketNumber());
    }

}
