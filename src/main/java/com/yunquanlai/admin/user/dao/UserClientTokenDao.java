package com.yunquanlai.admin.user.dao;


import com.yunquanlai.admin.system.dao.BaseDao;
import com.yunquanlai.admin.user.entity.UserClientTokenEntity;

/**
 * 
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 15:44:08
 */
public interface UserClientTokenDao extends BaseDao<UserClientTokenEntity> {

    UserClientTokenEntity queryObjectByUserId(Long userId);

    UserClientTokenEntity queryObjectByToken(String token);
}
