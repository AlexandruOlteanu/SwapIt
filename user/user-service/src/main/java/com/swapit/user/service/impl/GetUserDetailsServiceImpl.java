package com.swapit.user.service.impl;

import com.swapit.product.api.domain.response.GetProductsResponse;
import com.swapit.user.api.domain.request.SpecificUsersDetailsRequest;
import com.swapit.user.api.domain.response.SpecificUsersDetailsResponse;
import com.swapit.user.api.domain.response.UserDetailsResponse;
import com.swapit.user.api.util.UserDetailType;
import com.swapit.user.domain.User;
import com.swapit.user.repository.UserRepository;
import com.swapit.user.service.ExternalOperationsService;
import com.swapit.user.service.GetUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Slf4j
public class GetUserDetailsServiceImpl implements GetUserDetailsService {
    private final ExternalOperationsService externalOperationsService;
    private final UserRepository userRepository;

    @Override
    public UserDetailsResponse getCompleteUserDetails(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow();
        GetProductsResponse productsResponse = externalOperationsService.getAllProductsByUserId(userId);
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
    public SpecificUsersDetailsResponse getSpecificUsersDetails(SpecificUsersDetailsRequest request){
        Map<Integer, Map<UserDetailType, Object>> requestedDetails = new HashMap<>();
        request.getRequestedUserDetails()
                .forEach((key, value) -> {
                    Map<UserDetailType, Object> currentUserDetails = new HashMap<>();
                    User user = userRepository.findById(key).orElseThrow();
                    value.forEach(userDetailType -> currentUserDetails.put(userDetailType, getSpecificDetail(userDetailType, user)));
                    requestedDetails.put(key, currentUserDetails);
                });
        return SpecificUsersDetailsResponse.builder()
                .requestedUserDetails(requestedDetails)
                .build();
    }

    private Object getSpecificDetail(UserDetailType userDetailType, User user) {
        return switch (userDetailType) {
            case NAME -> user.getName();
            case SURNAME -> user.getSurname();
            case USERNAME -> user.getUsername();
        };
    }
}
