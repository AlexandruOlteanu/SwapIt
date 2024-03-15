package com.swapit.commons.cache;

public interface CacheInvalidateService {
    // USER CACHE
    void invalidateAllCacheWithValue(String value);

    void invalidateCache(String value, Object... key);
}
