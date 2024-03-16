package com.swapit.user.service.impl;

import com.swapit.user.api.domain.request.UpdateBasicUserDetailsRequest;
import com.swapit.user.api.domain.response.UpdateBasicUserDetailsResponse;
import com.swapit.user.api.util.UserDetailType;
import com.swapit.user.domain.User;
import com.swapit.user.repository.UserRepository;
import com.swapit.user.service.UpdateUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateUserDetailsServiceImpl implements UpdateUserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UpdateBasicUserDetailsResponse updateBasicUserDetails(UpdateBasicUserDetailsRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow();
        request.getUserDetails().forEach((key, value) -> {
                     switch (UserDetailType.valueOf(key)) {
                         case NAME -> user.setName(value);
                         case SURNAME -> user.setSurname(value);
                         default -> throw new RuntimeException("Unrecognised field value for User Update " + key);
                     }
                });
        userRepository.save(user);
        return UpdateBasicUserDetailsResponse.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .build();
    }
}
