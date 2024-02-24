package com.swapit.user.web;

import com.swapit.user.api.domain.request.LoginRequest;
import com.swapit.user.api.domain.request.RegisterRequest;
import com.swapit.user.api.domain.response.LoginResponse;
import com.swapit.user.api.domain.response.RegisterResponse;
import com.swapit.user.api.service.UserService;
import com.swapit.user.service.LoginService;
import com.swapit.user.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController implements UserService {
    private final LoginService loginService;
    private final RegisterService registerService;

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest request) {
        return ResponseEntity.ok(loginService.login(request));
    }

    @Override
    public ResponseEntity<RegisterResponse> register(RegisterRequest request) throws Exception {
        return ResponseEntity.ok(registerService.register(request));
    }
}
