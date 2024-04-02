package com.swapit.user.service.impl;

import com.swapit.commons.exception.ExceptionFactory;
import com.swapit.commons.exception.ExceptionType;
import com.swapit.commons.generator.RandomCodeGenerator;
import com.swapit.user.api.domain.request.LoginRequest;
import com.swapit.user.api.domain.request.Oauth2Request;
import com.swapit.user.api.domain.request.RegisterRequest;
import com.swapit.user.api.domain.request.SendRegistrationCodeRequest;
import com.swapit.user.api.domain.response.LoginResponse;
import com.swapit.user.api.domain.response.Oauth2Response;
import com.swapit.user.api.domain.response.RegisterResponse;
import com.swapit.user.domain.RegistrationCode;
import com.swapit.user.domain.User;
import com.swapit.user.repository.RegistrationCodeRepository;
import com.swapit.user.repository.UserRepository;
import com.swapit.user.security.JwtService;
import com.swapit.user.service.AuthenticationService;
import com.swapit.user.service.EmailSenderService;
import com.swapit.user.utils.AuthProvider;
import com.swapit.user.utils.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${email.code.length}")
    private Integer emailCodeLength;

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailSenderService emailSenderService;
    private final RandomCodeGenerator randomCodeGenerator;
    private final RegistrationCodeRepository registrationCodeRepository;
    private final ExceptionFactory exceptionFactory;

    @Override
    public LoginResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (Exception e) {
            throw exceptionFactory.create(ExceptionType.WRONG_USERNAME_OR_PASSWORD);
        }
        User user = userRepository.findUserByUsername(request.getUsername())
                .orElseThrow(() -> exceptionFactory.create(ExceptionType.USER_NOT_FOUND));
        return LoginResponse.builder()
                .userId(user.getUserId())
                .role(user.getRole().toString())
                .jwtToken(jwtService.generateToken(user))
                .build();
    }

    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        Optional<User> existingUser = userRepository.findUserByUsername(request.getUsername());
        if (existingUser.isPresent()) {
            throw exceptionFactory.create(ExceptionType.USERNAME_ALREADY_EXISTS);
        }
        existingUser = userRepository.findUserByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw exceptionFactory.create(ExceptionType.EMAIL_ALREADY_EXISTS);
        }
        registrationCodeRepository.findByEmailAndCode(request.getEmail(), request.getRegistrationCode())
                .orElseThrow(() -> exceptionFactory.create(ExceptionType.WRONG_REGISTRATION_CODE));
        registrationCodeRepository.deleteByEmailAndCode(request.getEmail(), request.getRegistrationCode());
        User user = User.builder()
                .username(request.getUsername())
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .authProvider(AuthProvider.LOCAL.name())
                .userImage(request.getUserImage())
                .build();

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
            String username = generateNewUsername(request.getSurname(), request.getName());
            User newUser = User.builder()
                    .username(username)
                    .name(request.getName())
                    .surname(request.getSurname())
                    .email(request.getEmail())
                    .role(Role.OAUTH2_USER)
                    .authProvider(AuthProvider.OAUTH2.name())
                    .oauth2UserId(request.getOauth2UserId())
                    .userImage(request.getUserImage())
                    .build();
            userId = userRepository.save(newUser).getUserId();
        }

        return Oauth2Response.builder()
                .userId(userId)
                .role(Role.OAUTH2_USER.name())
                .build();
    }

    @Override
    @Transactional
    public void sendRegistrationCode(SendRegistrationCodeRequest request) {
        Optional<User> existingUser = userRepository.findUserByUsername(request.getUsername());
        if (existingUser.isPresent()) {
            throw exceptionFactory.create(ExceptionType.USERNAME_ALREADY_EXISTS);
        }
        existingUser = userRepository.findUserByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw exceptionFactory.create(ExceptionType.EMAIL_ALREADY_EXISTS);
        }
        String subject = "SwapIt Registration Code";
        String code = randomCodeGenerator.generateRandomCode(emailCodeLength);
        String message = "Code: " + code + "\nDon't share this code with anyone!";
        emailSenderService.sendSimpleEmail(request.getEmail(), subject, message);
        registrationCodeRepository.deleteByEmail(request.getEmail());
        registrationCodeRepository.save(RegistrationCode.builder()
                        .email(request.getEmail())
                        .code(code)
                .build());
    }

    private String generateNewUsername(String surname, String name) {
        if (surname != null) surname = surname.toLowerCase();
        if (name != null) name = name.toLowerCase();
        String joinedPrefix = Stream.of(surname, name).filter(Objects::nonNull)
                .collect(Collectors.joining("_"));
        List<String> results = userRepository.getUsernameStartingWith(joinedPrefix)
                .orElse(new ArrayList<>());
        Pattern pattern = Pattern.compile(joinedPrefix + "(\\d*)$");
        List<Integer> suffix = new ArrayList<>();
        results.forEach(hit -> {
                    Matcher matcher = pattern.matcher(hit);
                    if (matcher.find()) {
                        Integer value = matcher.group(1).isEmpty() ? 0 : Integer.parseInt(matcher.group(1));
                        suffix.add(value);
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
