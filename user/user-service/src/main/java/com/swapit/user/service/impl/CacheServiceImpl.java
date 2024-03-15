package com.swapit.user.service.impl;


import com.swapit.user.domain.User;
import com.swapit.user.repository.UserRepository;
import com.swapit.user.service.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.swapit.commons.cache.CacheConstants.CACHE_USER_FROM_DB;

@Service
@RequiredArgsConstructor
@Slf4j
public class CacheServiceImpl implements CacheService {

    private final UserRepository userRepository;

    @Cacheable(value = CACHE_USER_FROM_DB, key = "@cacheKeyGenerator.generateKey(#userId)")
    public User getCompleteUserDetails(Integer userId) {
        return userRepository.findUserByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User with id " + userId + "doesn't exist"));
    }
}
