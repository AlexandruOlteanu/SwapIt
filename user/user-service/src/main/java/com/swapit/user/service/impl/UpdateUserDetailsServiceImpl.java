package com.swapit.user.service.impl;

import com.swapit.commons.exception.ExceptionFactory;
import com.swapit.commons.exception.ExceptionType;
import com.swapit.user.api.domain.request.UpdateBasicUserDetailsRequest;
import com.swapit.user.api.domain.request.UpdateProtectedUserDetailsRequest;
import com.swapit.user.domain.User;
import com.swapit.user.repository.UserRepository;
import com.swapit.user.service.UpdateUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateUserDetailsServiceImpl implements UpdateUserDetailsService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final ExceptionFactory exceptionFactory;

    @Override
    public void updateBasicUserDetails(UpdateBasicUserDetailsRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> exceptionFactory.create(ExceptionType.USER_NOT_FOUND));
        request.getUserDetails().forEach((key, value) -> {
             switch (key) {
                 case NAME -> user.setName(value);
                 case SURNAME -> user.setSurname(value);
                 case IMAGE -> user.setUserImage(value);
                 default -> throw exceptionFactory.create(ExceptionType.INVALID_USER_UPDATE_FIELD);
             }
        });
        userRepository.save(user);
    }

    @Override
    public void updateProtectedUserDetails(UpdateProtectedUserDetailsRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> exceptionFactory.create(ExceptionType.USER_NOT_FOUND));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), request.getPassword()));
        request.getUserDetails().forEach((key, value) -> {
            switch (key) {
                case EMAIL -> {
                    Optional<User> existingUser = userRepository.findUserByEmail(value);
                    if (existingUser.isPresent()) {
                        throw exceptionFactory.create(ExceptionType.EMAIL_ALREADY_EXISTS);
                    }
                    user.setEmail(value);
                }
                case USERNAME -> {
                    Optional<User> existingUser = userRepository.findUserByUsername(value);
                    if (existingUser.isPresent()) {
                        throw exceptionFactory.create(ExceptionType.USERNAME_ALREADY_EXISTS);
                    }
                    user.setUsername(value);
                }
                case PASSWORD -> user.setPassword(passwordEncoder.encode(value));
                default -> throw exceptionFactory.create(ExceptionType.INVALID_USER_UPDATE_FIELD);
            }
        });
        userRepository.save(user);
    }
}
