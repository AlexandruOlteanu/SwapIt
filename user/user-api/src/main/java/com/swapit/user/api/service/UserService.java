package com.swapit.user.api.service;

import com.swapit.user.api.domain.request.LoginRequest;
import com.swapit.user.api.domain.request.RegisterRequest;
import com.swapit.user.api.domain.request.SpecificUsersDetailsRequest;
import com.swapit.user.api.domain.request.UpdateBasicUserDetailsRequest;
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
    String AUTHENTICATION = "auth/";
    String LOGIN = "login";
    String REGISTER = "register";
    String USER_DETAILS = "getUserDetails";
    String SPECIFIC_USERS_DETAILS = "getSpecificUsersDetails";
    String UPDATE_BASIC_USER_DETAILS = "updateBasicUserDetails";

    @PostMapping(value = BASE_URL + AUTHENTICATION + LOGIN, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request);

    @PutMapping(value = BASE_URL + AUTHENTICATION + REGISTER, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request);

    @GetMapping(value = BASE_URL + USER_DETAILS)
    ResponseEntity<UserDetailsResponse> getUserDetails(@RequestParam(value = "userId") Integer userId);

    @PostMapping(value = BASE_URL + SPECIFIC_USERS_DETAILS, consumes = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<SpecificUsersDetailsResponse> getSpecificUsersDetails(@Valid @RequestBody SpecificUsersDetailsRequest request);

    @PutMapping(value = BASE_URL + UPDATE_BASIC_USER_DETAILS, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<UpdateBasicUserDetailsResponse> updateBasicUserDetails(@Valid @RequestBody UpdateBasicUserDetailsRequest request);

}
