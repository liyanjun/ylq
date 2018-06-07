package com.yunquanlai.admin.user.dao;


import com.yunquanlai.admin.system.dao.BaseDao;
import com.yunquanlai.admin.user.entity.UserDepositEntity;
import com.yunquanlai.admin.user.entity.UserDepositVO;

import java.util.List;
import java.util.Map;

/**
 * 客户押金信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 15:44:08
 */
public interface UserDepositDao extends BaseDao<UserDepositEntity> {
    List<UserDepositVO> queryDepositList(Map<String, Object> map);
}
