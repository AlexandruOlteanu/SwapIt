package com.swapit.product.api.service;

import com.swapit.product.api.domain.request.ProductCreationRequest;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public interface ProductService {

    String MEDIA_TYPE_APPLICATION_JSON = "application/json";
    String BASE_URL = "/api/v1/swapIt/product/";
    String CREATE_PRODUCT = "create";

    @PutMapping(value = BASE_URL + CREATE_PRODUCT, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void createProduct(@Valid @RequestBody ProductCreationRequest request) throws Exception;

}
