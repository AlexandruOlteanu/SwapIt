package com.swapit.product.api.service;

import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.product.api.domain.request.*;
import com.swapit.product.api.domain.response.GetProductsByCategoryResponse;
import com.swapit.product.api.domain.response.GetProductsByIdsResponse;
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
    String GET_PRODUCTS_BY_USER = "getProductsByUser";
    String GET_RECOMMENDED_PRODUCTS = "getRecommendedProducts";
    String GET_LIKED_PRODUCTS_BY_USER = "getLikedProductsByUser";
    String GET_PRODUCTS_BY_IDS = "getProductsByIds";
    String GET_PRODUCT_BY_ID = "getProductById";
    String GET_PRODUCTS_BY_CATEGORY = "getProductsByCategory";
    String CHANGE_PRODUCT_LIKE_STATUS = "changeProductLikeStatus";
    String GET_PRODUCT_LIKE_STATUS = "getProductLikeStatus";


    @PutMapping(value = BASE_URL + CREATE_PRODUCT, consumes = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<Integer> createProduct(@Valid @RequestBody CreateProductRequest request);

    @PutMapping(value = BASE_URL + UPDATE_PRODUCT, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void updateProduct(@Valid @RequestBody UpdateProductRequest request);

    @PostMapping(value = BASE_URL + CHANGE_PRODUCT_LIKE_STATUS, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void changeProductLikeStatus(@Valid @RequestBody ChangeProductLikeStatusRequest request);

    @GetMapping(value = BASE_URL + GET_PRODUCT_LIKE_STATUS)
    ResponseEntity<String> getProductLikeStatus(@RequestParam(value = "userId") Integer userId, @RequestParam(value = "productId") Integer productId);

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
    ResponseEntity<GetProductsByCategoryResponse> getProductsByCategory(@Valid @RequestBody GetProductsByCategoryRequest request);

}
