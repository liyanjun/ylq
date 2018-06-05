package com.yunquanlai.admin.delivery.dao;

import com.yunquanlai.admin.delivery.entity.DeliveryDistributorEntity;
import com.yunquanlai.admin.system.dao.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * 配送员信息
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 08:25:11
 */
public interface DeliveryDistributorDao extends BaseDao<DeliveryDistributorEntity> {
    List<DeliveryDistributorEntity> queryListForDeliveryEndpoint(Map<String, Object> map);
}
