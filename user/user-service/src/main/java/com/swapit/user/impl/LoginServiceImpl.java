package com.swapit.user.impl;

import com.swapit.user.api.domain.request.LoginRequest;
import com.swapit.user.api.domain.response.LoginResponse;
import com.swapit.user.domain.User;
import com.swapit.user.repository.UserRepository;
import com.swapit.user.security.config.JwtService;
import com.swapit.user.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findUserByUsername(request.getUsername())
                .orElseThrow();
        return LoginResponse.builder()
                .jwtToken(jwtService.generateToken(user))
                .build();
    }
}
