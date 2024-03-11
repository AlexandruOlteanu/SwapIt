package com.swapit.product.api.service;

import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.product.api.domain.request.ProductCreationRequest;
import com.swapit.product.api.domain.response.GetProductsResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public interface ProductService {

    String MEDIA_TYPE_APPLICATION_JSON = "application/json";
    String BASE_URL = "/api/v1/swapIt/product/";
    String CREATE_PRODUCT = "create";
    String GET_ALL_PRODUCTS_BY_USER_ID = "getAllProductsByUserId";


    @PutMapping(value = BASE_URL + CREATE_PRODUCT, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void createProduct(@Valid @RequestBody ProductCreationRequest request) throws Exception;

    @GetMapping(value = BASE_URL + GET_ALL_PRODUCTS_BY_USER_ID)
    ResponseEntity<GetProductsResponse> getAllProductsByUserId(@RequestParam(value = "userId") Integer userId);

}
