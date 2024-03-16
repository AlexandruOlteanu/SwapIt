package com.swapit.commons.cache;

public interface CacheInvalidateService {
    void invalidateAllCacheWithValue(String value);

    void invalidateCache(String value, Object... key);
}
