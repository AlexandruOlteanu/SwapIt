package com.swapit.bff.service;

import com.swapit.chat.api.domain.request.PrivateChatMessage;
import com.swapit.product.api.domain.request.ProductCreationRequest;
import com.swapit.user.api.domain.request.LoginRequest;
import com.swapit.user.api.domain.request.RegisterRequest;
import com.swapit.user.api.domain.response.LoginResponse;
import com.swapit.user.api.domain.response.RegisterResponse;

public interface ExternalOperationsService {

    LoginResponse login(LoginRequest request);
    RegisterResponse register(RegisterRequest request);
    void productCreation(ProductCreationRequest request);
    void sendPrivateMessage(PrivateChatMessage request);



}
