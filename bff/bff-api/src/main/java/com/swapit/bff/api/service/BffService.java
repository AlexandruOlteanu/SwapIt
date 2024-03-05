package com.swapit.bff.api.service;

import com.swapit.chat.api.domain.request.PrivateChatMessage;
import com.swapit.product.api.domain.request.ProductCreationRequest;
import com.swapit.user.api.domain.request.LoginRequest;
import com.swapit.user.api.domain.request.RegisterRequest;
import com.swapit.user.api.domain.response.LoginResponse;
import com.swapit.user.api.domain.response.RegisterResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public interface BffService {

    String MEDIA_TYPE_APPLICATION_JSON = "application/json";
    String BASE_URL = "/api/v1/swapIt/bff/";
    String AUTHENTICATION = "auth/";
    String LOGIN = "login";
    String REGISTER = "register";
    String PRODUCT_CREATION = "productCreation";
    String SEND_PRIVATE_MESSAGE = "sendPrivateMessage";

    @PostMapping(value = BASE_URL + AUTHENTICATION + LOGIN, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request);

    @PutMapping(value = BASE_URL + AUTHENTICATION + REGISTER, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) throws Exception;

    @PutMapping(value = BASE_URL + PRODUCT_CREATION, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void productCreation(@Valid @RequestBody ProductCreationRequest request);

    @PostMapping(value = BASE_URL + SEND_PRIVATE_MESSAGE, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void sendPrivateMessage(@Valid @RequestBody PrivateChatMessage request) throws Exception;

}
