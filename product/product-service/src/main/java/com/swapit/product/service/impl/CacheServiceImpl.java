package com.swapit.product.service.impl;


import com.swapit.product.service.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import static com.swapit.commons.cache.ConfigConstants.CACHE_PRODUCTS_FOR_USERS;

@Service
@RequiredArgsConstructor
@Slf4j
public class CacheServiceImpl implements CacheService {

    @Override
    @CacheEvict(value = CACHE_PRODUCTS_FOR_USERS, key = "#userId")
    public void deleteCachedProductsForUser(Integer userId) {

    }
}
