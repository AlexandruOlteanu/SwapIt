package com.swapit.user.api.service;

import com.swapit.user.api.domain.request.LoginRequest;
import com.swapit.user.api.domain.request.RegisterRequest;
import com.swapit.user.api.domain.request.SpecificUserDetailRequest;
import com.swapit.user.api.domain.response.LoginResponse;
import com.swapit.user.api.domain.response.RegisterResponse;
import com.swapit.user.api.domain.response.UserDetailsResponse;
import com.swapit.user.api.util.SpecificUserDetailActionType;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public interface UserService {

    String MEDIA_TYPE_APPLICATION_JSON = "application/json";
    String BASE_URL = "/api/v1/swapIt/user/";
    String AUTHENTICATION = "auth/";
    String LOGIN = "login";
    String REGISTER = "register";
    String USER_DETAILS = "getUserDetails";
    String SPECIFIC_USER_DETAIL = "getSpecificUserDetail";

    @PostMapping(value = BASE_URL + AUTHENTICATION + LOGIN, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request);

    @PutMapping(value = BASE_URL + AUTHENTICATION + REGISTER, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) throws Exception;

    @GetMapping(value = BASE_URL + USER_DETAILS)
    ResponseEntity<UserDetailsResponse> getUserDetails(@RequestParam(value = "userId") Integer userId);

    @PostMapping(value = BASE_URL + SPECIFIC_USER_DETAIL, consumes = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<Object> getSpecificUserDetail(@Valid @RequestBody SpecificUserDetailRequest request) throws Exception;

}
