package com.swapit.apiGateway.api.service;

import com.swapit.apiGateway.api.dto.response.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public interface ApiGatewayService {

    String MEDIA_TYPE_APPLICATION_JSON = "application/json";
    String BASE_URL = "/api/v1/swapIt/apiGateway/";
    String GET_USER_ID_BY_USERNAME_OR_EMAIL = "getUserIdByUsernameOrEmail";
    String GET_ALL_PRODUCTS_BY_USER_ID = "getAllProductsByUserId";

    @GetMapping(value = BASE_URL + GET_USER_ID_BY_USERNAME_OR_EMAIL)
    ResponseEntity<Integer> getUserIdByUsernameOrEmail(@RequestParam(value = "username", required = false) String username,
                                                      @RequestParam(value = "email", required = false) String email);

    @GetMapping(value = BASE_URL + GET_ALL_PRODUCTS_BY_USER_ID)
    ResponseEntity<List<ProductDTO>> getAllProductsByUserId(@RequestParam(value = "userId") Integer userId);

}
