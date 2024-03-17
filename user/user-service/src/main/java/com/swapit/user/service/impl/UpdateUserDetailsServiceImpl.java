package com.swapit.user.service.impl;

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


@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateUserDetailsServiceImpl implements UpdateUserDetailsService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void updateBasicUserDetails(UpdateBasicUserDetailsRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow();
        request.getUserDetails().forEach((key, value) -> {
             switch (key) {
                 case NAME -> user.setName(value);
                 case SURNAME -> user.setSurname(value);
                 default -> throw new RuntimeException("Unrecognised field value for User Update " + key);
             }
        });
        userRepository.save(user);
    }

    @Override
    public void updateProtectedUserDetails(UpdateProtectedUserDetailsRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), request.getPassword()));
        request.getUserDetails().forEach((key, value) -> {
            switch (key) {
                case EMAIL -> user.setEmail(value);
                case USERNAME -> user.setSurname(value);
                case PASSWORD -> user.setPassword(passwordEncoder.encode(value));
                default -> throw new RuntimeException("Unrecognised field value for User Update " + key);
            }
        });
        userRepository.save(user);
    }
}
