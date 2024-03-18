package com.swapit.searchEngine.api.service;

import com.swapit.searchEngine.api.service.domain.request.AddNewProductCategoryRequest;
import com.swapit.searchEngine.api.service.domain.request.AddNewProductSubcategoryRequest;
import com.swapit.searchEngine.api.service.domain.response.GetProductCategoriesResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public interface SearchEngineService {

    String MEDIA_TYPE_APPLICATION_JSON = "application/json";
    String BASE_URL = "/api/v1/swapIt/searchEngine/";
    String ADD_NEW_PRODUCT_CATEGORY = "addNewProductCategory";
    String ADD_NEW_PRODUCT_SUBCATEGORY = "addNewProductSubcategory";
    String GET_ALL_PRODUCT_CATEGORIES = "getAllProductCategories";

    @PutMapping(value = BASE_URL + ADD_NEW_PRODUCT_CATEGORY)
    void addNewProductCategory(@Valid @RequestBody AddNewProductCategoryRequest request);

    @PutMapping(value = BASE_URL + ADD_NEW_PRODUCT_SUBCATEGORY)
    void addNewProductSubcategory(@Valid @RequestBody AddNewProductSubcategoryRequest request);

    @GetMapping(value = BASE_URL + GET_ALL_PRODUCT_CATEGORIES)
    ResponseEntity<GetProductCategoriesResponse> getAllProductCategories();
}
