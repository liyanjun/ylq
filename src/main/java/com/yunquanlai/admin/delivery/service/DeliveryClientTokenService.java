package com.yunquanlai.admin.delivery.service;

import com.yunquanlai.admin.delivery.entity.DeliveryClientTokenEntity;
import com.yunquanlai.admin.user.entity.UserClientTokenEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

/**
 * @author liyanjun
 * @email
 * @date 2018-06-07 21:02:38
 */
public interface DeliveryClientTokenService {

    DeliveryClientTokenEntity queryObject(Long id);

    List<DeliveryClientTokenEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(DeliveryClientTokenEntity deliveryClientToken);

    void update(DeliveryClientTokenEntity deliveryClientToken);

    void delete(Long id);

    void deleteBatch(Long[] ids);

    @CacheEvict(value = "delivery_tokens", key = "#oldToken")
    void update(DeliveryClientTokenEntity deliveryClientTokenEntity, String oldToken);

    @Cacheable(value = "delivery_tokens", key = "#token")
    DeliveryClientTokenEntity queryByToken(String token);

    /**
     * 通过配送员 ID 查询
     *
     * @param userId
     * @return
     */
    DeliveryClientTokenEntity queryByDistributorId(Long userId);
}
