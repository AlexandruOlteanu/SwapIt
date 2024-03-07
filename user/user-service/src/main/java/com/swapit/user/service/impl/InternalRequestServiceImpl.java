package com.swapit.user.service.impl;

import com.swapit.user.repository.UserRepository;
import com.swapit.user.service.InternalRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InternalRequestServiceImpl implements InternalRequestService {

    private final UserRepository userRepository;

    @Override
    public Integer getUserIdByUsernameOrEmail(String username, String email) throws Exception {
        if (username != null) {
            return userRepository.getUserIdByUsername(username)
                    .orElseThrow(() -> new Exception("User not found with username: " + username));
        }
        return userRepository.getUserIdByEmail(email)
                .orElseThrow(() -> new Exception("User not found with email: " + email));
    }
}
