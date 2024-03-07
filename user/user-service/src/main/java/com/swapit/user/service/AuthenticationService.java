package com.swapit.user.service;

import com.swapit.user.api.domain.request.LoginRequest;
import com.swapit.user.api.domain.request.RegisterRequest;
import com.swapit.user.api.domain.response.LoginResponse;
import com.swapit.user.api.domain.response.RegisterResponse;

public interface AuthenticationService {

    LoginResponse login(LoginRequest request);
    RegisterResponse register(RegisterRequest request) throws Exception;

}
