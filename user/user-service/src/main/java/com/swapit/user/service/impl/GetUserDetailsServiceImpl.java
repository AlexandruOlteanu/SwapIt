package com.swapit.user.service.impl;

import com.swapit.product.api.domain.response.GetProductsResponse;
import com.swapit.user.api.domain.dto.UserProductDTO;
import com.swapit.user.api.domain.request.GetSpecificUsersDetailsRequest;
import com.swapit.user.api.domain.response.GetSpecificUsersDetailsResponse;
import com.swapit.user.api.domain.response.GetUserDetailsResponse;
import com.swapit.user.api.util.UserBasicDetailType;
import com.swapit.user.domain.User;
import com.swapit.user.repository.UserRepository;
import com.swapit.user.service.ExternalOperationsService;
import com.swapit.user.service.GetUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Slf4j
public class GetUserDetailsServiceImpl implements GetUserDetailsService {
    private final ExternalOperationsService externalOperationsService;
    private final UserRepository userRepository;

    @Override
    public GetUserDetailsResponse getUserDetails(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow();
        GetProductsResponse productsResponse = externalOperationsService.getAllProductsByUserId(userId);
        List<UserProductDTO> products = productsResponse.getProducts().stream()
                .map(productDTO -> UserProductDTO.builder()
                        .productId(productDTO.getProductId())
                        .creationDate(productDTO.getCreationDate())
                        .title(productDTO.getTitle())
                        .description(productDTO.getDescription())
                        .categoryId(productDTO.getCategoryId())
                        .popularity(productDTO.getPopularity())
                        .build()).toList();
        return GetUserDetailsResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .joinDate(user.getJoinDate())
                .products(products)
                .build();
    }

    @Override
    public GetSpecificUsersDetailsResponse getSpecificUsersDetails(GetSpecificUsersDetailsRequest request){
        Map<Integer, Map<UserBasicDetailType, Object>> requestedDetails = new HashMap<>();
        request.getRequestedUserDetails()
                .forEach((key, value) -> {
                    Map<UserBasicDetailType, Object> currentUserDetails = new HashMap<>();
                    User user = userRepository.findById(key).orElseThrow();
                    value.forEach(userBasicDetailType -> currentUserDetails.put(userBasicDetailType, getSpecificDetail(userBasicDetailType, user)));
                    requestedDetails.put(key, currentUserDetails);
                });
        return GetSpecificUsersDetailsResponse.builder()
                .requestedUserDetails(requestedDetails)
                .build();
    }

    private Object getSpecificDetail(UserBasicDetailType userBasicDetailType, User user) {
        return switch (userBasicDetailType) {
            case NAME -> user.getName();
            case SURNAME -> user.getSurname();
            case USERNAME -> user.getUsername();
        };
    }
}
