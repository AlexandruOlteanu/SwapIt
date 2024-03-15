package com.swapit.user.service.impl;

import com.swapit.product.api.domain.response.GetProductsResponse;
import com.swapit.user.api.domain.request.SpecificUserDetailRequest;
import com.swapit.user.api.domain.response.UserDetailsResponse;
import com.swapit.user.domain.User;
import com.swapit.user.repository.UserRepository;
import com.swapit.user.service.CacheService;
import com.swapit.user.service.ExternalOperationsService;
import com.swapit.user.service.GetUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetUserDetailsServiceImpl implements GetUserDetailsService {
    private final ExternalOperationsService externalOperationsService;
    private final CacheService cacheService;
    private final UserRepository userRepository;

    @Override
    public UserDetailsResponse getCompleteUserDetails(Integer userId) {
        var userFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return cacheService.getCompleteUserDetails(userId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        var productsResponseFuture = CompletableFuture.supplyAsync(() -> externalOperationsService.getAllProductsByUserId(userId));
        User user = userFuture.join();
        GetProductsResponse productsResponse = productsResponseFuture.join();
        var products = productsResponse.getProducts();
        return UserDetailsResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .joinDate(user.getJoinDate())
                .products(products)
                .build();
    }

    @Override
    public Object getSpecificUserDetail(SpecificUserDetailRequest request){
        return switch (request.getUserDetailType()) {
            case NAME -> getNameByUserId(request.getUserId());
            case SURNAME -> getSurnameByUserId(request.getUserId());
            case USERNAME -> getUsernameByUserId(request.getUserId());
        };
    }

    private String getUsernameByUserId(Integer userId) {
        return userRepository.findUsernameByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User id doesn't exist"));
    }

    private String getNameByUserId(Integer userId) {
        return userRepository.findNameByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User id doesn't exist"));
    }

    private String getSurnameByUserId(Integer userId) {
        return userRepository.findSurnameByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User id doesn't exist"));
    }
}
