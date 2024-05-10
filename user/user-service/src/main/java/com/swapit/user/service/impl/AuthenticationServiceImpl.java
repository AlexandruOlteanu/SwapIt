package com.swapit.user.service.impl;

import com.swapit.commons.exception.ExceptionFactory;
import com.swapit.commons.exception.ExceptionType;
import com.swapit.user.api.domain.request.*;
import com.swapit.user.api.domain.response.LoginResponse;
import com.swapit.user.api.domain.response.Oauth2Response;
import com.swapit.user.api.domain.response.RegisterResponse;
import com.swapit.user.api.util.RegisterProcessPhase;
import com.swapit.user.domain.User;
import com.swapit.user.repository.SecurityCodeRepository;
import com.swapit.user.repository.UserRepository;
import com.swapit.user.security.JwtService;
import com.swapit.user.service.AuthenticationService;
import com.swapit.user.service.SecurityCodeService;
import com.swapit.user.utils.AuthProvider;
import com.swapit.user.api.util.SecurityCodeType;
import com.swapit.user.utils.UserRole;
import com.swapit.user.utils.UserStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.swapit.user.api.util.ForgottenPasswordResetProcessPhase.*;
import static com.swapit.user.api.util.SecurityCodeType.REGISTRATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final SecurityCodeRepository securityCodeRepository;
    private final SecurityCodeService securityCodeService;
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
        if (user.getStatus().equals(UserStatus.INACTIVE)) {
            throw exceptionFactory.create(ExceptionType.USER_BANNED);
        }
        return LoginResponse.builder()
                .jwtToken(jwtService.generateToken(user))
                .build();
    }

    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        if (RegisterProcessPhase.VERIFY_DATA.equals(request.getProcessPhase())) {
            Optional<User> existingUser = userRepository.findUserByUsername(request.getUsername());
            if (existingUser.isPresent()) {
                throw exceptionFactory.create(ExceptionType.USERNAME_ALREADY_EXISTS);
            }
            existingUser = userRepository.findUserByEmail(request.getEmail());
            if (existingUser.isPresent()) {
                throw exceptionFactory.create(ExceptionType.EMAIL_ALREADY_EXISTS);
            }
        }

        if (RegisterProcessPhase.SEND_SECURITY_CODE.equals(request.getProcessPhase())) {
            securityCodeService.sendSecurityCode(SendSecurityCodeRequest.builder()
                            .email(request.getEmail())
                            .securityCodeType(REGISTRATION)
                    .build());
        }

        if (RegisterProcessPhase.FINALIZE.equals(request.getProcessPhase())) {
            securityCodeRepository.findByEmailAndCodeAndCodeType(request.getEmail(), request.getSecurityCode(), REGISTRATION)
                    .orElseThrow(() -> exceptionFactory.create(ExceptionType.WRONG_SECURITY_CODE));
            securityCodeRepository.deleteByEmailAndCodeAndCodeType(request.getEmail(), request.getSecurityCode(), REGISTRATION);
            User user = User.builder()
                    .username(request.getUsername())
                    .name(request.getName())
                    .surname(request.getSurname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .userRole(UserRole.USER)
                    .status(UserStatus.ACTIVE)
                    .authProvider(AuthProvider.LOCAL)
                    .userImage(request.getUserImage())
                    .build();
            user = userRepository.save(user);
            return RegisterResponse.builder()
                    .jwtToken(jwtService.generateToken(user))
                    .build();
        }
        return RegisterResponse.builder().build();
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
                    .userRole(UserRole.OAUTH2_USER)
                    .status(UserStatus.ACTIVE)
                    .authProvider(AuthProvider.OAUTH2)
                    .oauth2UserId(request.getOauth2UserId())
                    .userImage(request.getUserImage())
                    .build();
            userId = userRepository.save(newUser).getUserId();
        }

        return Oauth2Response.builder()
                .userId(userId)
                .build();
    }

    @Override
    @Transactional
    public void forgottenPasswordReset(ForgottenPasswordResetRequest request) {
        if (VERIFY_DATA.equals(request.getProcessPhase())) {
            userRepository.findUserByEmail(request.getEmail())
                    .orElseThrow(() -> exceptionFactory.create(ExceptionType.USER_NOT_FOUND));
        }

        if (SEND_SECURITY_CODE.equals(request.getProcessPhase())) {
            securityCodeService.sendSecurityCode(SendSecurityCodeRequest.builder()
                            .email(request.getEmail())
                            .securityCodeType(SecurityCodeType.FORGOTTEN_PASSWORD_RESET)
                    .build());
        }

        if (FINALIZE.equals(request.getProcessPhase())) {
            User user = userRepository.findUserByEmail(request.getEmail())
                    .orElseThrow(() -> exceptionFactory.create(ExceptionType.USER_NOT_FOUND));
            securityCodeRepository.findByEmailAndCodeAndCodeType(request.getEmail(), request.getSecurityCode(), SecurityCodeType.FORGOTTEN_PASSWORD_RESET)
                    .orElseThrow(() -> exceptionFactory.create(ExceptionType.WRONG_SECURITY_CODE));
            securityCodeRepository.deleteByEmailAndCodeType(request.getEmail(), SecurityCodeType.FORGOTTEN_PASSWORD_RESET);
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        }
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
