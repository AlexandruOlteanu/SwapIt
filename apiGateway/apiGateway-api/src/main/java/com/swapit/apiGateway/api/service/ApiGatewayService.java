package com.swapit.apiGateway.api.service;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public interface ApiGatewayService {

    String MEDIA_TYPE_APPLICATION_JSON = "application/json";
    String BASE_URL = "/api/v1/swapIt/apiGateway/";
    String GET_USER_ID_BY_USERNAME_OR_EMAIL = "getUserIdByUsernameOrEmail";

    @GetMapping(value = BASE_URL + GET_USER_ID_BY_USERNAME_OR_EMAIL)
    Integer getUserIdByUsernameOrEmail(@RequestParam(value = "username", required = false) String username,
                                                      @RequestParam(value = "email", required = false) String email);

}
