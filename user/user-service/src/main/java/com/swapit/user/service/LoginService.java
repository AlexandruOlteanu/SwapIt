package com.swapit.user.service;

import com.swapit.user.api.domain.request.LoginRequest;
import com.swapit.user.api.domain.response.LoginResponse;

public interface LoginService {
    LoginResponse login(LoginRequest request);

}
