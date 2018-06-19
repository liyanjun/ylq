package com.yunquanlai.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenUtils {

    private static final String CACHE = "orderTokens";

   @Autowired
    EhCacheCacheManager ehCacheCacheManager;

    public String getToken() {
        String token = UUID.randomUUID().toString();
        ehCacheCacheManager.getCache(CACHE).put(token,"1");
        return token;
    }

    public boolean isExitToken(String token) {
        Cache.ValueWrapper valueWrapper = ehCacheCacheManager.getCache(CACHE).get(token);
        if (valueWrapper == null || valueWrapper.get() == null) {
            return false;
        }
        ehCacheCacheManager.getCache(CACHE).evict(token);
        return true;

    }
}
