package com.swapit.user.service;

import com.swapit.user.api.domain.request.*;
import com.swapit.user.api.domain.response.LoginResponse;
import com.swapit.user.api.domain.response.Oauth2Response;
import com.swapit.user.api.domain.response.RegisterResponse;

public interface AuthenticationService {

    LoginResponse login(LoginRequest request);
    RegisterResponse register(RegisterRequest request);
    Oauth2Response oauth2login(Oauth2Request request);
    void sendRegistrationCode(SendRegistrationCodeRequest request);
    void sendPasswordResetCode(SendPasswordResetCodeRequest request);
    void passwordReset(PasswordResetRequest request);
}
