package com.swapit.product.api.service;

import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.product.api.domain.request.*;
import com.swapit.product.api.domain.response.GetProductsByCategoryResponse;
import com.swapit.product.api.domain.response.GetProductsByIdsResponse;
import com.swapit.product.api.domain.response.GetProductsLikeStatusResponse;
import com.swapit.product.api.domain.response.GetProductsResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.swapit.commons.utils.Constants.IntegerMaxValueAsString;

@RestController
@Validated
public interface ProductService {

    String MEDIA_TYPE_APPLICATION_JSON = "application/json";
    String BASE_URL = "/api/v1/swapIt/product/";
    String CREATE_PRODUCT = "createProduct";
    String UPDATE_PRODUCT = "updateProduct";
    String DELETE_PRODUCT = "deleteProduct";
    String DELETE_PRODUCT_ADMIN = "deleteProductAdmin";
    String GET_PRODUCTS_BY_USER = "getProductsByUser";
    String GET_RECOMMENDED_PRODUCTS = "getRecommendedProducts";
    String GET_LIKED_PRODUCTS_BY_USER = "getLikedProductsByUser";
    String GET_PRODUCTS_BY_IDS = "getProductsByIds";
    String GET_PRODUCT_BY_ID = "getProductById";
    String GET_PRODUCTS_BY_CATEGORY = "getProductsByCategory";
    String CHANGE_PRODUCT_LIKE_STATUS = "changeProductLikeStatus";
    String GET_PRODUCTS_LIKE_STATUS = "getProductsLikeStatus";


    @PutMapping(value = BASE_URL + CREATE_PRODUCT, consumes = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<Integer> createProduct(@RequestParam(value = "userId") Integer userId, @Valid @RequestBody CreateProductRequest request);

    @PutMapping(value = BASE_URL + UPDATE_PRODUCT, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void updateProduct(@RequestParam(value = "userId") Integer userId, @Valid @RequestBody UpdateProductRequest request);

    @DeleteMapping(value = BASE_URL + DELETE_PRODUCT)
    void deleteProduct(@RequestParam(value = "userId") Integer userId, @RequestParam(value = "productId") Integer productId);

    @DeleteMapping(value = BASE_URL + DELETE_PRODUCT_ADMIN)
    void deleteProductAdmin(@RequestParam(value = "productId") Integer productId);

    @PutMapping(value = BASE_URL + CHANGE_PRODUCT_LIKE_STATUS)
    void changeProductLikeStatus(@RequestParam(value = "userId") Integer userId, @RequestParam(value = "productId") Integer productId);

    @PostMapping(value = BASE_URL + GET_PRODUCTS_LIKE_STATUS)
    ResponseEntity<GetProductsLikeStatusResponse> getProductsLikeStatus(@RequestParam(value = "userId") Integer userId, @Valid @RequestBody GetProductsLikeStatusRequest request);

    @GetMapping(value = BASE_URL + GET_PRODUCTS_BY_USER)
    ResponseEntity<GetProductsResponse> getProductsByUser(@RequestParam(value = "userId") Integer userId,
                                                          @RequestParam(value = "chunkNumber", defaultValue = "0") Integer chunkNumber,
                                                          @RequestParam(value = "nrElementsPerChunk", defaultValue = IntegerMaxValueAsString) Integer nrElementsPerChunk,
                                                          @RequestParam(value = "sortCriteria", required = false) String sortCriteria);

    @GetMapping(value = BASE_URL + GET_RECOMMENDED_PRODUCTS)
    ResponseEntity<GetProductsResponse> getRecommendedProducts(@RequestParam(value = "chunkNumber", defaultValue = "0") Integer chunkNumber,
                                                               @RequestParam(value = "nrElementsPerChunk", defaultValue = IntegerMaxValueAsString) Integer nrElementsPerChunk,
                                                               @RequestParam(value = "sortCriteria", required = false) String sortCriteria);

    @GetMapping(value = BASE_URL + GET_LIKED_PRODUCTS_BY_USER)
    ResponseEntity<GetProductsResponse> getLikedProductsByUser(@RequestParam(value = "userId") Integer userId,
                                                               @RequestParam(value = "chunkNumber", defaultValue = "0") Integer chunkNumber,
                                                               @RequestParam(value = "nrElementsPerChunk", defaultValue = IntegerMaxValueAsString) Integer nrElementsPerChunk,
                                                               @RequestParam(value = "sortCriteria", required = false) String sortCriteria);


    @PostMapping(value = BASE_URL + GET_PRODUCTS_BY_IDS)
    ResponseEntity<GetProductsByIdsResponse> getProductsByIds(@Valid @RequestBody GetProductsByIdsRequest request);

    @GetMapping(value = BASE_URL + GET_PRODUCT_BY_ID)
    ResponseEntity<ProductDTO> getProductById(@RequestParam(value = "productId") Integer productId);

    @PostMapping(value = BASE_URL + GET_PRODUCTS_BY_CATEGORY)
    ResponseEntity<GetProductsByCategoryResponse> getProductsByCategory(@RequestParam(value = "chunkNumber", defaultValue = "0") Integer chunkNumber,
                                                                        @RequestParam(value = "nrElementsPerChunk", defaultValue = IntegerMaxValueAsString) Integer nrElementsPerChunk,
                                                                        @RequestParam(value = "sortCriteria", required = false) String sortCriteria,
                                                                        @Valid @RequestBody GetProductsByCategoryRequest request);

}
