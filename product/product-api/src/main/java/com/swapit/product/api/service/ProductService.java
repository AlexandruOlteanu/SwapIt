package com.swapit.product.api.service;

import com.swapit.product.api.domain.request.ProductCreationRequest;
import com.swapit.product.api.domain.response.GetProductsResponse;
import com.swapit.product.api.domain.response.SearchProductData;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public interface ProductService {

    String MEDIA_TYPE_APPLICATION_JSON = "application/json";
    String BASE_URL = "/api/v1/swapIt/product/";
    String CREATE_PRODUCT = "create";
    String GET_ALL_PRODUCTS_BY_USER_ID = "getAllProductsByUserId";
    String SEARCH_PRODUCT_DATA = "searchProductData";


    @PutMapping(value = BASE_URL + CREATE_PRODUCT, consumes = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<Integer> createProduct(@Valid @RequestBody ProductCreationRequest request);

    @GetMapping(value = BASE_URL + GET_ALL_PRODUCTS_BY_USER_ID)
    ResponseEntity<GetProductsResponse> getAllProductsByUserId(@RequestParam(value = "userId") Integer userId);

    @GetMapping(value = BASE_URL + SEARCH_PRODUCT_DATA)
    ResponseEntity<SearchProductData> searchProductData(@RequestParam(value = "productId") Integer productId);

}
