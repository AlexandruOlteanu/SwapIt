package com.swapit.user.service;

import com.swapit.user.domain.User;

public interface CacheService {
    User getUserDetailsFromDb(Integer userId) throws Exception;
    void deleteCachedUserDetailsFromDb(Integer userId);
}
