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

    DeliveryDistributorEntity pickOne(Long id);

    void updateDeliveryEndpointName(Map<String, Object> map);

    /**
     * 根据手机号查询
     *
     * @param mobile
     *
     * @return
     */
    DeliveryDistributorEntity queryObjectByPhone(String mobile);

    /**
     * 根据配送点id获取配送员列表
     * @param deliveryEndpointId
     * @return
     */
    List<DeliveryDistributorEntity> queryListByDeliveryEndpointId(Long deliveryEndpointId);
}
