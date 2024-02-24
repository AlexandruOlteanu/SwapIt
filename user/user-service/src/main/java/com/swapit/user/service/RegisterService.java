package com.swapit.user.service;

import com.swapit.user.api.domain.request.RegisterRequest;
import com.swapit.user.api.domain.response.RegisterResponse;

public interface RegisterService {
    RegisterResponse register(RegisterRequest request) throws Exception;
}
