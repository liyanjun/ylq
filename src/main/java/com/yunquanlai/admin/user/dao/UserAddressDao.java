package com.yunquanlai.admin.user.dao;


import com.yunquanlai.admin.system.dao.BaseDao;
import com.yunquanlai.admin.user.entity.UserAddressEntity;

import java.util.List;

/**
 * 客户地址信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 15:44:08
 */
public interface UserAddressDao extends BaseDao<UserAddressEntity> {

    List<UserAddressEntity> queryByUserId(Long id);
}
