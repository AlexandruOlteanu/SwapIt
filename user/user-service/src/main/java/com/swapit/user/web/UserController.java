package com.swapit.user.web;

import com.swapit.user.api.domain.request.LoginRequest;
import com.swapit.user.api.domain.request.RegisterRequest;
import com.swapit.user.api.domain.response.LoginResponse;
import com.swapit.user.api.domain.response.RegisterResponse;
import com.swapit.user.api.service.UserService;
import com.swapit.user.service.AuthenticationService;
import com.swapit.user.service.InternalRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController implements UserService {

    private final AuthenticationService authenticationService;
    private final InternalRequestService internalRequestService;
    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @Override
    public ResponseEntity<RegisterResponse> register(RegisterRequest request) throws Exception {
        return ResponseEntity.ok(authenticationService.register(request));
    }


    // Internal Requests
    @Override
    public Integer getUserIdByUsernameOrEmail(String username, String email) throws Exception {
        return internalRequestService.getUserIdByUsernameOrEmail(username, email);
    }
}
