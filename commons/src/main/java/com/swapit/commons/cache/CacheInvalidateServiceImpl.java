package com.swapit.commons.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class CacheInvalidateServiceImpl implements CacheInvalidateService {

    private final CacheManager cacheManager;

    @Override
    public void invalidateAllCacheWithValue(String value) {
        Cache cache = cacheManager.getCache(value);
        if (cache == null) {
            log.error("Error, caches with value {} was not found", value);
            return;
        }
        cache.clear();
    }

    @Override
    public void invalidateCache(String value, Object... key) {
        String joinedKey = CacheKeyGenerator.generateKey(key);
        Cache cache = cacheManager.getCache(value);
        if (cache == null) {
            log.error("Error, cache with value {} and key {{}} was not found", value, joinedKey);
            return;
        }
        cache.evictIfPresent(joinedKey);
    }


}
