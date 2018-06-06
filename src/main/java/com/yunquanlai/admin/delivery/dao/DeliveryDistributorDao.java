package com.yunquanlai.admin.delivery.dao;

import com.yunquanlai.admin.delivery.entity.DeliveryDistributorEntity;
import com.yunquanlai.admin.system.dao.BaseDao;

import java.util.Map;

/**
 * 配送员信息
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 08:25:11
 */
public interface DeliveryDistributorDao extends BaseDao<DeliveryDistributorEntity> {

    DeliveryDistributorEntity pickOne(Long id);

    void updateDeliveryEndpointName(Map<String, Object> map);
}
