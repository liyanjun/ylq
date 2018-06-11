package com.yunquanlai.admin.user.dao;


import com.yunquanlai.admin.system.dao.BaseDao;
import com.yunquanlai.admin.user.entity.UserWithdrawDepositEntity;

/**
 * 客户押金提现申请表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 15:44:08
 */
public interface UserWithdrawDepositDao extends BaseDao<UserWithdrawDepositEntity> {

    UserWithdrawDepositEntity queryObjectByUserId(Long id);
}
