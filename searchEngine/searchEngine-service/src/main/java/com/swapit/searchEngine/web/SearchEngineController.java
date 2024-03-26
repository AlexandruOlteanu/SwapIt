package com.swapit.searchEngine.web;

import com.swapit.searchEngine.api.service.SearchEngineService;
import com.swapit.searchEngine.api.service.domain.request.AddNewProductCategoryRequest;
import com.swapit.searchEngine.api.service.domain.request.SearchProductsRequest;
import com.swapit.searchEngine.api.service.domain.response.GetCategoryTreeResponse;
import com.swapit.searchEngine.api.service.domain.response.GetProductCategoriesResponse;
import com.swapit.searchEngine.api.service.domain.response.SearchProductsResponse;
import com.swapit.searchEngine.service.ProductCategorizeService;
import com.swapit.searchEngine.service.SearchIndexService;
import com.swapit.searchEngine.service.SearchProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SearchEngineController implements SearchEngineService {

    private final ProductCategorizeService productCategorizeService;
    private final SearchIndexService searchIndexService;
    private final SearchProductsService searchProductsService;

    @Override
    public void addNewProductCategory(AddNewProductCategoryRequest request) {
        productCategorizeService.addNewProductCategory(request);
    }

    @Override
    public ResponseEntity<GetProductCategoriesResponse> getAllProductCategories() {
        return ResponseEntity.ok(productCategorizeService.getAllProductCategories());
    }

    @Override
    public void indexProduct(Integer productId) throws IOException {
        searchIndexService.indexProduct(productId);
    }

    @Override
    public ResponseEntity<SearchProductsResponse> searchProducts(SearchProductsRequest request) throws IOException {
        return ResponseEntity.ok(searchProductsService.searchProducts(request));
    }

    @Override
    public ResponseEntity<GetCategoryTreeResponse> getCategoryTree(Integer categoryId) {
        return ResponseEntity.ok(productCategorizeService.getCategoryTree(categoryId));
    }

    @Override
    public ResponseEntity<SearchProductsResponse> searchProductsByCategory(Integer categoryId) {
        return ResponseEntity.ok(searchProductsService.searchProductsByCategory(categoryId));
    }
}
