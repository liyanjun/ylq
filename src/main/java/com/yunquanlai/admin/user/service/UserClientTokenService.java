package com.yunquanlai.admin.user.service;


import com.yunquanlai.admin.user.entity.UserClientTokenEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

/**
 * @author liyanjun
 * @email
 * @date 2018-06-04 15:44:08
 */
public interface UserClientTokenService {

    UserClientTokenEntity queryObject(Long id);

    List<UserClientTokenEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(UserClientTokenEntity userClientToken);

    void update(UserClientTokenEntity userClientToken, String oldToken);

    UserClientTokenEntity queryByToken(String token);

    void delete(Long id);

    void deleteBatch(Long[] ids);

    UserClientTokenEntity queryByUserId(Long userId);
}
