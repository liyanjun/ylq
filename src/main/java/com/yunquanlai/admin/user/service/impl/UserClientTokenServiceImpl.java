package com.yunquanlai.admin.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yunquanlai.admin.user.dao.UserClientTokenDao;
import com.yunquanlai.admin.user.entity.UserClientTokenEntity;
import com.yunquanlai.admin.user.service.UserClientTokenService;
import org.springframework.transaction.annotation.Transactional;


@Service("userClientTokenService")
@Transactional(rollbackFor = Exception.class)
public class UserClientTokenServiceImpl implements UserClientTokenService {
    @Autowired
    private UserClientTokenDao userClientTokenDao;

    @Override
    public UserClientTokenEntity queryObject(Long id) {
        return userClientTokenDao.queryObject(id, false);
    }

    @Override
    public List<UserClientTokenEntity> queryList(Map<String, Object> map) {
        return userClientTokenDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return userClientTokenDao.queryTotal(map);
    }

    @Override
    public void save(UserClientTokenEntity userClientToken) {
        userClientTokenDao.save(userClientToken);
    }

    @Override
    @CacheEvict(value = "tokens", key = "#oldToken")
    public void update(UserClientTokenEntity userClientToken, String oldToken) {
        userClientTokenDao.update(userClientToken);
    }

    @Override
    @Cacheable(value="tokens", key="#token")
    public UserClientTokenEntity queryByToken(String token) {
        return userClientTokenDao.queryObjectByToken(token);
    }

    @Override
    public void delete(Long id) {
        userClientTokenDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        userClientTokenDao.deleteBatch(ids);
    }

    @Override
    public UserClientTokenEntity queryByUserId(Long userId) {
        return userClientTokenDao.queryObjectByUserId(userId);
    }

}
