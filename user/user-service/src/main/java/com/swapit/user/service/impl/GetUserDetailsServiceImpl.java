package com.swapit.user.service.impl;

import com.swapit.commons.exception.ExceptionFactory;
import com.swapit.commons.exception.ExceptionType;
import com.swapit.user.api.domain.request.GetSpecificUsersDetailsRequest;
import com.swapit.user.api.domain.response.GetSpecificUsersDetailsResponse;
import com.swapit.user.api.domain.response.GetUserDetailsResponse;
import com.swapit.user.api.util.UserBasicDetailType;
import com.swapit.user.domain.User;
import com.swapit.user.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final ExceptionFactory exceptionFactory;

    @Override
    public GetUserDetailsResponse getUserDetails(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> exceptionFactory.create(ExceptionType.USER_NOT_FOUND));
        return GetUserDetailsResponse.builder()
                .userId(userId)
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .userImage(user.getUserImage())
                .joinDate(user.getJoinDate())
                .userRole(user.getUserRole().name())
                .address(user.getAddress())
                .country(user.getCountry())
                .stateRegion(user.getStateRegion())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    @Override
    public GetSpecificUsersDetailsResponse getSpecificUsersDetails(GetSpecificUsersDetailsRequest request){
        Map<Integer, Map<UserBasicDetailType, Object>> requestedDetails = new HashMap<>();
        request.getRequestedUserDetails()
                .forEach((key, value) -> {
                    Map<UserBasicDetailType, Object> currentUserDetails = new HashMap<>();
                    User user = userRepository.findById(key)
                            .orElseThrow(() -> exceptionFactory.create(ExceptionType.USER_NOT_FOUND));
                    value.forEach(userBasicDetailType -> currentUserDetails.put(userBasicDetailType, getSpecificDetail(userBasicDetailType, user)));
                    requestedDetails.put(key, currentUserDetails);
                });
        return GetSpecificUsersDetailsResponse.builder()
                .requestedUserDetails(requestedDetails)
                .build();
    }

    @Override
    public GetUserDetailsResponse getUserDetailsByUsername(String username) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> exceptionFactory.create(ExceptionType.USER_NOT_FOUND));
        return GetUserDetailsResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .userImage(user.getUserImage())
                .joinDate(user.getJoinDate())
                .userRole(user.getUserRole().name())
                .address(user.getAddress())
                .country(user.getCountry())
                .stateRegion(user.getStateRegion())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    private Object getSpecificDetail(UserBasicDetailType userBasicDetailType, User user) {
        return switch (userBasicDetailType) {
            case NAME -> user.getName();
            case SURNAME -> user.getSurname();
            case USERNAME -> user.getUsername();
            case IMAGE -> user.getUserImage();
            case STATUS -> user.getStatus();
            case BAN_EXPIRY_TIME -> user.getBanExpiryTime();
            case ADDRESS -> user.getAddress();
            case COUNTRY -> user.getCountry();
            case STATE_REGION -> user.getStateRegion();
            case PHONE_NUMBER -> user.getPhoneNumber();
        };
    }
}
