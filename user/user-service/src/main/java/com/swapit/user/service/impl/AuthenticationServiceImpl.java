package com.swapit.user.service.impl;

import com.swapit.user.api.domain.request.LoginRequest;
import com.swapit.user.api.domain.request.RegisterRequest;
import com.swapit.user.api.domain.response.LoginResponse;
import com.swapit.user.api.domain.response.RegisterResponse;
import com.swapit.user.domain.User;
import com.swapit.user.repository.UserRepository;
import com.swapit.user.security.JwtService;
import com.swapit.user.service.AuthenticationService;
import com.swapit.user.utils.Role;
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
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findUserByUsername(request.getUsername())
                .orElseThrow();
        return LoginResponse.builder()
                .userId(user.getUserId())
                .role(user.getRole().toString())
                .jwtToken(jwtService.generateToken(user))
                .build();
    }

    @Override
    public RegisterResponse register(RegisterRequest request) throws Exception {
        User user = User.builder()
                .username(request.getUsername())
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        Optional<User> existingUser = userRepository.findUserByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new Exception("Username already registered");
        }
        Integer userId = userRepository.save(user).getUserId();
        return RegisterResponse.builder()
                .userId(userId)
                .role(Role.USER.toString())
                .jwtToken(jwtService.generateToken(user))
                .build();
    }
}
