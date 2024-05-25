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
    String FORGOTTEN_PASSWORD_RESET = "auth/forgottenPasswordReset";
    String PASSWORD_RESET = "passwordReset";
    String USERNAME_RESET = "usernameReset";
    String EMAIL_RESET = "emailReset";
    String GET_USER_DETAILS = "getUserDetails";
    String GET_USER_DETAILS_BY_USERNAME = "getUserDetailsByUsername";
    String BAN_USER = "banUser";
    String GET_USER_ACCOUNT_STATUS = "getUserAccountStatus";
    String MANUAL_SECURITY_CODES_EXPIRE = "manualSecurityCodesExpire";
    String MANUAL_REMOVE_USERS_BAN = "manualRemoveUsersBan";
    String REMOVE_USER_BAN = "removeUserBan";
    String GET_SPECIFIC_USERS_DETAILS = "getSpecificUsersDetails";
    String UPDATE_BASIC_USER_DETAILS = "updateBasicUserDetails";

    @PostMapping(value = BASE_URL + LOGIN, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request);

    @PostMapping(value = BASE_URL + OAUTH2_LOGIN, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<Oauth2Response> oauth2login(@Valid @RequestBody Oauth2Request request);

    @PutMapping(value = BASE_URL + REGISTER, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request);

    @PutMapping(value = BASE_URL + FORGOTTEN_PASSWORD_RESET, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void forgottenPasswordReset(@Valid @RequestBody ForgottenPasswordResetRequest request);

    @PutMapping(value = BASE_URL + PASSWORD_RESET, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void passwordReset(@RequestParam("userId") Integer userId, @Valid @RequestBody PasswordResetRequest request);

    @PutMapping(value = BASE_URL + EMAIL_RESET, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void emailReset(@RequestParam("userId") Integer userId, @Valid @RequestBody EmailResetRequest request);

    @PutMapping(value = BASE_URL + USERNAME_RESET, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void usernameReset(@RequestParam("userId") Integer userId, @Valid @RequestBody UsernameResetRequest request);

    @GetMapping(value = BASE_URL + GET_USER_DETAILS)
    ResponseEntity<GetUserDetailsResponse> getUserDetails(@RequestParam(value = "userId") Integer userId);

    @GetMapping(value = BASE_URL + GET_USER_DETAILS_BY_USERNAME)
    ResponseEntity<GetUserDetailsResponse> getUserDetailsByUsername(@RequestParam(value = "username") String username);

    @PostMapping(value = BASE_URL + GET_SPECIFIC_USERS_DETAILS, consumes = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<GetSpecificUsersDetailsResponse> getSpecificUsersDetails(@Valid @RequestBody GetSpecificUsersDetailsRequest request);

    @PutMapping(value = BASE_URL + UPDATE_BASIC_USER_DETAILS, consumes = MEDIA_TYPE_APPLICATION_JSON, produces = MEDIA_TYPE_APPLICATION_JSON)
    void updateBasicUserDetails(@RequestParam("userId") Integer userId, @Valid @RequestBody UpdateBasicUserDetailsRequest request);

    @PostMapping(value = BASE_URL + BAN_USER)
    void banUser(@RequestParam("userId") Integer userId, @RequestParam(value = "banDaysDuration", required = false) Integer banDaysDuration);

    @GetMapping(value = BASE_URL + GET_USER_ACCOUNT_STATUS)
    ResponseEntity<GetUserAccountStatusResponse> getUserAccountStatus(@RequestParam("userId") Integer userId);

    @DeleteMapping(value = BASE_URL + REMOVE_USER_BAN)
    void removeUserBan(@RequestParam("userId") Integer userId);

    @DeleteMapping(value = BASE_URL + MANUAL_SECURITY_CODES_EXPIRE)
    void manualSecurityCodesExpire();

    @DeleteMapping(value = BASE_URL + MANUAL_REMOVE_USERS_BAN)
    void manualRemoveUsersBan();
}
