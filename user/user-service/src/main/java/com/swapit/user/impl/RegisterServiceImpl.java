package com.swapit.user.impl;

import com.swapit.user.api.domain.request.RegisterRequest;
import com.swapit.user.api.domain.response.RegisterResponse;
import com.swapit.user.domain.User;
import com.swapit.user.repository.UserRepository;
import com.swapit.user.security.Role;
import com.swapit.user.security.config.JwtService;
import com.swapit.user.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

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
        userRepository.save(user);
        return RegisterResponse.builder()
                .jwtToken(jwtService.generateToken(user))
                .build();
    }
}
