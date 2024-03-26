package com.swapit.user.service.impl;

import com.swapit.user.api.domain.request.LoginRequest;
import com.swapit.user.api.domain.request.Oauth2Request;
import com.swapit.user.api.domain.request.RegisterRequest;
import com.swapit.user.api.domain.response.LoginResponse;
import com.swapit.user.api.domain.response.Oauth2Response;
import com.swapit.user.api.domain.response.RegisterResponse;
import com.swapit.user.domain.User;
import com.swapit.user.repository.UserRepository;
import com.swapit.user.security.JwtService;
import com.swapit.user.service.AuthenticationService;
import com.swapit.user.utils.AuthProvider;
import com.swapit.user.utils.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public RegisterResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .authProvider(AuthProvider.LOCAL.name())
                .build();
        Optional<User> existingUser = userRepository.findUserByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Username already registered");
        }
        Integer userId = userRepository.save(user).getUserId();
        return RegisterResponse.builder()
                .userId(userId)
                .role(Role.USER.name())
                .jwtToken(jwtService.generateToken(user))
                .build();
    }

    @Override
    public Oauth2Response oauth2login(Oauth2Request request) {
        User existingUser = userRepository.findUserByOauth2UserId(request.getOauth2UserId())
                .orElse(null);
        Integer userId;
        if (existingUser != null) {
            existingUser.setEmail(request.getEmail());
            existingUser.setName(request.getName());
            existingUser.setSurname(request.getSurname());
            userRepository.save(existingUser);
            userId = existingUser.getUserId();
        } else {
            String username = generateNewUsername(request.getSurname().toLowerCase(), request.getName().toLowerCase());
            User newUser = User.builder()
                    .username(username)
                    .name(request.getName())
                    .surname(request.getSurname())
                    .email(request.getEmail())
                    .role(Role.OAUTH2_USER)
                    .authProvider(AuthProvider.OAUTH2.name())
                    .oauth2UserId(request.getOauth2UserId())
                    .build();
            userId = userRepository.save(newUser).getUserId();
        }

        return Oauth2Response.builder()
                .userId(userId)
                .role(Role.OAUTH2_USER.name())
                .build();
    }

    private String generateNewUsername(String surname, String name) {
        String joinedPrefix = String.join("_", surname, name);
        List<String> results = userRepository.getUsernameStartingWith(joinedPrefix)
                .orElse(new ArrayList<>());
        Pattern pattern = Pattern.compile(joinedPrefix + "(\\d*)$");
        List<Integer> suffix = new ArrayList<>();
        results.forEach(hit -> {
                    Matcher matcher = pattern.matcher(hit);
                    if (matcher.find()) {
                        if (matcher.group(1).isEmpty()) {
                            suffix.add(0);
                        }
                        else {
                            suffix.add(Integer.valueOf(matcher.group(1)));
                        }
                    }
                });
        suffix.sort(Comparator.naturalOrder());
        String endValue = suffix.isEmpty() ? "" : String.valueOf(findFirstMissing(suffix));
        return joinedPrefix + endValue;
    }

    private Integer findFirstMissing(List<Integer> numbers) {
        int low = 0, high = numbers.size() - 1;
        int answer = -1;
        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (numbers.get(middle).equals(middle)) {
                answer = middle;
                low = middle + 1;
                continue;
            }
            high = middle - 1;
        }
        return answer + 1;
    }
}
