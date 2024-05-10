package com.swapit.user.service.impl;

import com.swapit.commons.exception.ExceptionFactory;
import com.swapit.commons.exception.ExceptionType;
import com.swapit.user.api.domain.request.*;
import com.swapit.user.api.util.SecurityCodeType;
import com.swapit.user.domain.User;
import com.swapit.user.repository.SecurityCodeRepository;
import com.swapit.user.repository.UserRepository;
import com.swapit.user.service.SecurityCodeService;
import com.swapit.user.service.UpdateUserDetailsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.swapit.user.api.util.EmailResetProcessPhase.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateUserDetailsServiceImpl implements UpdateUserDetailsService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final SecurityCodeRepository securityCodeRepository;
    private final SecurityCodeService securityCodeService;
    private final ExceptionFactory exceptionFactory;

    @Override
    public void updateBasicUserDetails(Integer userId, UpdateBasicUserDetailsRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> exceptionFactory.create(ExceptionType.USER_NOT_FOUND));
        request.getUserDetails().forEach((key, value) -> {
             switch (key) {
                 case NAME -> user.setName(value);
                 case SURNAME -> user.setSurname(value);
                 case IMAGE -> user.setUserImage(value);
                 case ADDRESS -> user.setAddress(value);
                 case COUNTRY -> user.setCountry(value);
                 case STATE_REGION -> user.setStateRegion(value);
                 case PHONE_NUMBER -> user.setPhoneNumber(value);
                 default -> throw exceptionFactory.create(ExceptionType.INVALID_USER_UPDATE_FIELD);
             }
        });
        userRepository.save(user);
    }

    @Override
    public void passwordReset(Integer userId, PasswordResetRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> exceptionFactory.create(ExceptionType.USER_NOT_FOUND));
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), request.getPassword()));
        } catch (Exception e) {
            throw exceptionFactory.create(ExceptionType.WRONG_PASSWORD);
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void emailReset(Integer userId, EmailResetRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> exceptionFactory.create(ExceptionType.USER_NOT_FOUND));
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), request.getPassword()));
        } catch (Exception e) {
            throw exceptionFactory.create(ExceptionType.WRONG_PASSWORD);
        }
        Optional<User> existingUser = userRepository.findUserByEmail(request.getNewEmail());
        if (existingUser.isPresent()) {
            throw exceptionFactory.create(ExceptionType.EMAIL_ALREADY_EXISTS);
        }
        if (SEND_SECURITY_CODE.equals(request.getProcessPhase())) {
            securityCodeService.sendSecurityCode(SendSecurityCodeRequest.builder()
                            .email(request.getNewEmail())
                            .securityCodeType(SecurityCodeType.EMAIL_RESET)
                    .build());
        }
        if (FINALIZE.equals(request.getProcessPhase())) {
            securityCodeRepository.findByEmailAndCodeAndCodeType(request.getNewEmail(), request.getSecurityCode(), SecurityCodeType.EMAIL_RESET)
                    .orElseThrow(() -> exceptionFactory.create(ExceptionType.WRONG_SECURITY_CODE));
            securityCodeRepository.deleteByEmailAndCodeType(request.getNewEmail(), SecurityCodeType.EMAIL_RESET);
            user.setEmail(request.getNewEmail());
        }
    }

    @Override
    public void usernameReset(Integer userId, UsernameResetRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> exceptionFactory.create(ExceptionType.USER_NOT_FOUND));
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), request.getPassword()));
        } catch (Exception e) {
            throw exceptionFactory.create(ExceptionType.WRONG_PASSWORD);
        }
        Optional<User> existingUser = userRepository.findUserByUsername(request.getNewUsername());
        if (existingUser.isPresent()) {
            throw exceptionFactory.create(ExceptionType.USERNAME_ALREADY_EXISTS);
        }
        user.setUsername(request.getNewUsername());
        userRepository.save(user);
    }
}
