package com.swapit.searchEngine.api.service;

import com.swapit.searchEngine.api.service.domain.request.AddNewProductCategoryRequest;
import com.swapit.searchEngine.api.service.domain.request.SearchProductsRequest;
import com.swapit.searchEngine.api.service.domain.response.GetCategoryTreeResponse;
import com.swapit.searchEngine.api.service.domain.response.GetProductCategoriesResponse;
import com.swapit.searchEngine.api.service.domain.response.SearchProductsResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.swapit.commons.utils.Constants.IntegerMaxValueAsString;

@RestController
@Validated
public interface SearchEngineService {

    String MEDIA_TYPE_APPLICATION_JSON = "application/json";
    String BASE_URL = "/api/v1/swapIt/searchEngine/";
    String ADD_NEW_PRODUCT_CATEGORY = "addNewProductCategory";
    String GET_ALL_PRODUCT_CATEGORIES = "getAllProductCategories";
    String ADD_PRODUCT_IN_SEARCH_DICTIONARY = "addProductInSearchDictionary";
    String UPDATE_PRODUCT_IN_SEARCH_DICTIONARY = "updateProductInSearchDictionary";

    String SEARCH_PRODUCTS = "searchProducts";
    String GET_CATEGORY_TREE = "getCategoryTree";
    String SEARCH_PRODUCTS_BY_CATEGORY = "searchProductsByCategory";

    @PutMapping(value = BASE_URL + ADD_NEW_PRODUCT_CATEGORY, consumes = MEDIA_TYPE_APPLICATION_JSON)
    void addNewProductCategory(@Valid @RequestBody AddNewProductCategoryRequest request);

    @GetMapping(value = BASE_URL + GET_ALL_PRODUCT_CATEGORIES, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<GetProductCategoriesResponse> getAllProductCategories();

    @PutMapping(value = BASE_URL + ADD_PRODUCT_IN_SEARCH_DICTIONARY)
    void addProductInSearchDictionary(@RequestParam("productId") Integer productId) throws IOException;

    @PutMapping(value = BASE_URL + UPDATE_PRODUCT_IN_SEARCH_DICTIONARY)
    void updateProductInSearchDictionary(@RequestParam("productId") Integer productId) throws IOException;

    @PostMapping(value = BASE_URL + SEARCH_PRODUCTS, consumes = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<SearchProductsResponse> searchProducts(@Valid @RequestBody SearchProductsRequest request) throws IOException;

    @GetMapping(value = BASE_URL + GET_CATEGORY_TREE, produces = MEDIA_TYPE_APPLICATION_JSON)
    ResponseEntity<GetCategoryTreeResponse> getCategoryTree(@RequestParam(value = "categoryId") Integer categoryId);

    @GetMapping(value = BASE_URL + SEARCH_PRODUCTS_BY_CATEGORY)
    ResponseEntity<SearchProductsResponse> searchProductsByCategory(@RequestParam(value = "categoryId") Integer categoryId,
                                                                    @RequestParam(value = "chunkNumber", defaultValue = "0") Integer chunkNumber,
                                                                    @RequestParam(value = "nrElementsPerChunk", defaultValue = IntegerMaxValueAsString) Integer nrElementsPerChunk,
                                                                    @RequestParam(value = "sortCriteria", required = false) String sortCriteria);


}
