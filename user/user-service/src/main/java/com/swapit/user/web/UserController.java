package com.swapit.user.web;

import com.swapit.user.api.domain.request.*;
import com.swapit.user.api.domain.response.*;
import com.swapit.user.api.service.UserService;
import com.swapit.user.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController implements UserService {

    private final AuthenticationService authenticationService;
    private final UpdateUserDetailsService updateUserDetailsService;
    private final GetUserDetailsService getUserDetailsService;

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @Override
    public ResponseEntity<Oauth2Response> oauth2login(Oauth2Request request) {
        return ResponseEntity.ok(authenticationService.oauth2login(request));
    }

    @Override
    public ResponseEntity<RegisterResponse> register(RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @Override
    public void sendRegistrationCode(SendRegistrationCodeRequest request) {
        authenticationService.sendRegistrationCode(request);
    }

    @Override
    public ResponseEntity<GetUserDetailsResponse> getUserDetails(Integer userId) {
        return ResponseEntity.ok(getUserDetailsService.getUserDetails(userId));
    }

    @Override
    public ResponseEntity<GetSpecificUsersDetailsResponse> getSpecificUsersDetails(GetSpecificUsersDetailsRequest request) {
        return ResponseEntity.ok(getUserDetailsService.getSpecificUsersDetails(request));
    }

    @Override
    public void updateBasicUserDetails(UpdateBasicUserDetailsRequest request) {
        updateUserDetailsService.updateBasicUserDetails(request);
    }

    @Override
    public void updateProtectedUserDetails(UpdateProtectedUserDetailsRequest request) {
        updateUserDetailsService.updateProtectedUserDetails(request);
    }
}
