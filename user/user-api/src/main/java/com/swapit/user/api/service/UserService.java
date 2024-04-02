package com.swapit.user.api.service;

import com.swapit.user.api.domain.request.*;
import com.swapit.user.api.domain.response.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public interface UserService {

    String MEDIA_TYPE_APPLICATION_JSON = "application/json";
    String BASE_URL = "/api/v1/swapIt/user/";
    String LOGIN = "auth/login";
    String OAUTH2_LOGIN = "auth/oauth2login";
    String REGISTER = "auth/register";
    String SEND_REGISTRATION_CODE = "auth/sendRegistrationCode";
    String GET_USER_DETAILS = "getUserDetails";
    String GET_SPECIFIC_USERS_DETAILS = "getSpecificUsersDetails";
    String UPDATE_BASIC_USER_DETAILS = "updateBasicUserDetails";
    String UPDATE_PROTECTED_USER_DETAILS = "updateProtectedUserDetails";

    @PostMapping(value = BASE_URL + LOGIN, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request);

    @PostMapping(value = BASE_URL + OAUTH2_LOGIN, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<Oauth2Response> oauth2login(@Valid @RequestBody Oauth2Request request);

    @PutMapping(value = BASE_URL + REGISTER, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request);

    @PostMapping(value = BASE_URL + SEND_REGISTRATION_CODE, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void sendRegistrationCode(@Valid @RequestBody SendRegistrationCodeRequest request);

    @GetMapping(value = BASE_URL + GET_USER_DETAILS)
    ResponseEntity<GetUserDetailsResponse> getUserDetails(@RequestParam(value = "userId") Integer userId);

    @PostMapping(value = BASE_URL + GET_SPECIFIC_USERS_DETAILS, consumes = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<GetSpecificUsersDetailsResponse> getSpecificUsersDetails(@Valid @RequestBody GetSpecificUsersDetailsRequest request);

    @PutMapping(value = BASE_URL + UPDATE_BASIC_USER_DETAILS, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    void updateBasicUserDetails(@RequestParam("userId") Integer userId, @Valid @RequestBody UpdateBasicUserDetailsRequest request);

    @PutMapping(value = BASE_URL + UPDATE_PROTECTED_USER_DETAILS, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    void updateProtectedUserDetails(@RequestParam("userId") Integer userId, @Valid @RequestBody UpdateProtectedUserDetailsRequest request);

}
