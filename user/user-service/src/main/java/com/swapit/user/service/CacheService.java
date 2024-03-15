package com.swapit.user.service;

import com.swapit.user.domain.User;

public interface CacheService {
    User getCompleteUserDetails(Integer userId);
}
