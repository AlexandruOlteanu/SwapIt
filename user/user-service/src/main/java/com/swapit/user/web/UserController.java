package com.swapit.user.web;

import com.swapit.user.api.domain.request.LoginRequest;
import com.swapit.user.api.domain.request.RegisterRequest;
import com.swapit.user.api.domain.request.SpecificUsersDetailsRequest;
import com.swapit.user.api.domain.request.UpdateBasicUserDetailsRequest;
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
    public ResponseEntity<RegisterResponse> register(RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @Override
    public ResponseEntity<UserDetailsResponse> getUserDetails(Integer userId) {
        return ResponseEntity.ok(getUserDetailsService.getCompleteUserDetails(userId));
    }

    @Override
    public ResponseEntity<SpecificUsersDetailsResponse> getSpecificUsersDetails(SpecificUsersDetailsRequest request) {
        return ResponseEntity.ok(getUserDetailsService.getSpecificUsersDetails(request));
    }

    @Override
    public ResponseEntity<UpdateBasicUserDetailsResponse> updateBasicUserDetails(UpdateBasicUserDetailsRequest request) {
        return ResponseEntity.ok(updateUserDetailsService.updateBasicUserDetails(request));
    }
}
