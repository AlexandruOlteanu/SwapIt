package com.swapit.searchEngine.api.service;

import com.swapit.searchEngine.api.service.domain.request.AddNewProductCategoryRequest;
import com.swapit.searchEngine.api.service.domain.request.SearchProductsRequest;
import com.swapit.searchEngine.api.service.domain.response.GetProductCategoriesResponse;
import com.swapit.searchEngine.api.service.domain.response.SearchProductsResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Validated
public interface SearchEngineService {

    String MEDIA_TYPE_APPLICATION_JSON = "application/json";
    String BASE_URL = "/api/v1/swapIt/searchEngine/";
    String ADD_NEW_PRODUCT_CATEGORY = "addNewProductCategory";
    String GET_ALL_PRODUCT_CATEGORIES = "getAllProductCategories";
    String INDEX_PRODUCT = "indexProduct";
    String SEARCH_PRODUCTS = "searchProducts";

    @PutMapping(value = BASE_URL + ADD_NEW_PRODUCT_CATEGORY, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void addNewProductCategory(@Valid @RequestBody AddNewProductCategoryRequest request);

    @GetMapping(value = BASE_URL + GET_ALL_PRODUCT_CATEGORIES, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<GetProductCategoriesResponse> getAllProductCategories();

    @PutMapping(value = BASE_URL + INDEX_PRODUCT)
    void indexProduct(@RequestParam("productId") Integer productId) throws IOException;

    @PostMapping(value = BASE_URL + SEARCH_PRODUCTS, consumes = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<SearchProductsResponse> searchProducts(@Valid @RequestBody SearchProductsRequest request) throws IOException;
}
