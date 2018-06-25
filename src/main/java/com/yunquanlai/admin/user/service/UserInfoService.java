package com.yunquanlai.admin.user.service;


import com.yunquanlai.admin.order.entity.OrderDeliveryInfoEntity;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.utils.R;

import java.util.List;
import java.util.Map;

/**
 * 客户信息表
 *
 * @author liyanjun
 * @email
 * @date 2018-06-04 15:44:08
 */
public interface UserInfoService {

    UserInfoEntity queryObject(Long id);

    List<UserInfoEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(UserInfoEntity userInfo);

    void update(UserInfoEntity userInfo);

/*		void delete(Long id);

	void deleteBatch(Long[] ids);*/

    UserInfoEntity queryObjectByOpenId(String openId);

    /**
     * 用户回收空桶
     *
     * @param orderDeliveryInfoEntity
     * @param number
     * @param deliveryDistributorId
     * @return
     */
    R recyclingEmptyBarrels(OrderDeliveryInfoEntity orderDeliveryInfoEntity, Integer number, Long deliveryDistributorId);
}
