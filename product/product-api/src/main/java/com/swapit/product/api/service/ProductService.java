package com.swapit.product.api.service;

import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.product.api.domain.request.GetProductsByCategoryRequest;
import com.swapit.product.api.domain.request.GetProductsByIdsRequest;
import com.swapit.product.api.domain.request.CreateProductRequest;
import com.swapit.product.api.domain.request.UpdateProductRequest;
import com.swapit.product.api.domain.response.GetProductsByCategoryResponse;
import com.swapit.product.api.domain.response.GetProductsByIdsResponse;
import com.swapit.product.api.domain.response.GetProductsResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public interface ProductService {

    String MEDIA_TYPE_APPLICATION_JSON = "application/json";
    String BASE_URL = "/api/v1/swapIt/product/";
    String CREATE_PRODUCT = "createProduct";
    String UPDATE_PRODUCT = "updateProduct";
    String GET_ALL_PRODUCTS_BY_USER_ID = "getAllProductsByUserId";
    String GET_PRODUCTS_BY_IDS = "getProductsByIds";
    String GET_PRODUCT_BY_ID = "getProductById";
    String GET_PRODUCTS_BY_CATEGORY = "getProductsByCategory";


    @PutMapping(value = BASE_URL + CREATE_PRODUCT, consumes = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<Integer> createProduct(@Valid @RequestBody CreateProductRequest request);

    @PutMapping(value = BASE_URL + UPDATE_PRODUCT, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void updateProduct(@Valid @RequestBody UpdateProductRequest request);

    @GetMapping(value = BASE_URL + GET_ALL_PRODUCTS_BY_USER_ID)
    ResponseEntity<GetProductsResponse> getProductsByUserId(@RequestParam(value = "userId") Integer userId);

    @PostMapping(value = BASE_URL + GET_PRODUCTS_BY_IDS)
    ResponseEntity<GetProductsByIdsResponse> getProductsByIds(@Valid @RequestBody GetProductsByIdsRequest request);

    @GetMapping(value = BASE_URL + GET_PRODUCT_BY_ID)
    ResponseEntity<ProductDTO> getProductById(@RequestParam(value = "productId") Integer productId);
    @PostMapping(value = BASE_URL + GET_PRODUCTS_BY_CATEGORY)
    ResponseEntity<GetProductsByCategoryResponse> getProductsByCategory(@Valid @RequestBody GetProductsByCategoryRequest request);

}
