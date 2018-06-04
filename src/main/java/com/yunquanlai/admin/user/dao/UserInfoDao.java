package com.yunquanlai.admin.user.dao;


import com.yunquanlai.admin.system.dao.BaseDao;
import com.yunquanlai.admin.user.entity.UserInfoEntity;

/**
 * 客户信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 15:44:08
 */
public interface UserInfoDao extends BaseDao<UserInfoEntity> {

    UserInfoEntity queryObjectByUid(String uid);
}
