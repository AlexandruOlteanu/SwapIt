package com.swapit.user.service.impl;

import com.swapit.user.api.domain.request.SpecificUserDetailRequest;
import com.swapit.user.api.util.SpecificUserDetailActionType;
import com.swapit.user.repository.UserRepository;
import com.swapit.user.service.SpecificUserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class SpecificUserDetailServiceImpl implements SpecificUserDetailService {
    private final UserRepository userRepository;

    @Override
    public Object getSpecificUserDetail(SpecificUserDetailRequest request) throws Exception {
        return switch (SpecificUserDetailActionType.valueOf(request.getActionType())) {
            case GET_NAME -> getNameByUserId(request.getUserId());
            case GET_SURNAME -> getSurnameByUserId(request.getUserId());
            case GET_USERNAME -> getUsernameByUserId(request.getUserId());
        };
    }

    private String getUsernameByUserId(Integer userId) throws Exception {
        return userRepository.findUsernameByUserId(userId)
                .orElseThrow(() -> new Exception("User id doesn't exist"));
    }

    private String getNameByUserId(Integer userId) throws Exception {
        return userRepository.findNameByUserId(userId)
                .orElseThrow(() -> new Exception("User id doesn't exist"));
    }

    private String getSurnameByUserId(Integer userId) throws Exception {
        return userRepository.findSurnameByUserId(userId)
                .orElseThrow(() -> new Exception("User id doesn't exist"));
    }


}
