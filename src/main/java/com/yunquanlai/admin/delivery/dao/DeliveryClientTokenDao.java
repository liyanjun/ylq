package com.yunquanlai.admin.delivery.dao;

import com.yunquanlai.admin.delivery.entity.DeliveryClientTokenEntity;
import com.yunquanlai.admin.system.dao.BaseDao;
import com.yunquanlai.admin.user.entity.UserClientTokenEntity;

/**
 * 
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-07 21:02:38
 */
public interface DeliveryClientTokenDao extends BaseDao<DeliveryClientTokenEntity> {

    /**
     * 根据token查询
     * @param token
     * @return
     */
    DeliveryClientTokenEntity queryObjectByToken(String token);

    DeliveryClientTokenEntity queryByDistributorId(Long userId);
}
