package com.swapit.apiGateway.web;

import com.swapit.apiGateway.api.ApiGatewayService;
import com.swapit.apiGateway.service.ExternalOperationsService;
import com.swapit.chat.api.domain.request.PrivateChatMessage;
import com.swapit.product.api.domain.request.ProductCreationRequest;
import com.swapit.user.api.domain.request.LoginRequest;
import com.swapit.user.api.domain.request.RegisterRequest;
import com.swapit.user.api.domain.response.LoginResponse;
import com.swapit.user.api.domain.response.RegisterResponse;
import com.swapit.user.api.domain.response.UserDetailsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiGatewayController implements ApiGatewayService {

    private final ExternalOperationsService externalOperationsService;

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest request) {
        return ResponseEntity.ok(externalOperationsService.login(request));
    }

    @Override
    public ResponseEntity<RegisterResponse> register(RegisterRequest request) {
        return ResponseEntity.ok(externalOperationsService.register(request));
    }

    @Override
    public void productCreation(ProductCreationRequest request) {
        externalOperationsService.productCreation(request);
    }

    @Override
    public void sendPrivateMessage(PrivateChatMessage request) {
        externalOperationsService.sendPrivateMessage(request);
    }

    @Override
    public ResponseEntity<UserDetailsResponse> getUserDetails(String username) {
        return ResponseEntity.ok(externalOperationsService.getUserDetails(username));
    }

}
