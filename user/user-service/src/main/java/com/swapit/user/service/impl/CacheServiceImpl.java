package com.swapit.user.service.impl;


import com.swapit.user.domain.User;
import com.swapit.user.repository.UserRepository;
import com.swapit.user.service.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.swapit.commons.cache.ConfigConstants.CACHE_USER_GET_DETAILS;

@Service
@RequiredArgsConstructor
@Slf4j
public class CacheServiceImpl implements CacheService {

    private final UserRepository userRepository;

    @Cacheable(value = CACHE_USER_GET_DETAILS, key = "#userId")
    public User getUserDetailsFromDb(Integer userId) throws Exception {
        return userRepository.findUserByUserId(userId)
                .orElseThrow(() -> new Exception("User with id " + userId + "doesn't exist"));
    }

    @Override
    @CacheEvict(value = CACHE_USER_GET_DETAILS, key = "#userId")
    public void deleteCachedUserDetailsFromDb(Integer userId) {

    }

}
