package com.swapit.searchEngine.web;

import com.swapit.searchEngine.api.service.SearchEngineService;
import com.swapit.searchEngine.api.service.domain.request.AddNewProductCategoryRequest;
import com.swapit.searchEngine.api.service.domain.request.SearchProductsRequest;
import com.swapit.searchEngine.api.service.domain.response.GetCategoryTreeResponse;
import com.swapit.searchEngine.api.service.domain.response.GetProductCategoriesResponse;
import com.swapit.searchEngine.api.service.domain.response.SearchProductsResponse;
import com.swapit.searchEngine.service.ProductCategorizeService;
import com.swapit.searchEngine.service.SearchDictionaryService;
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
    private final SearchDictionaryService searchDictionaryService;
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
    public void addProductInSearchDictionary(Integer productId) throws IOException {
        searchDictionaryService.addProductInSearchDictionary(productId);
    }

    @Override
    public void updateProductInSearchDictionary(Integer productId) throws IOException {
        searchDictionaryService.updateProductInSearchDictionary(productId);
    }

    @Override
    public void deleteProductFromSearchDictionary(Integer productId) throws IOException {
        searchDictionaryService.deleteProductFromSearchDictionary(productId);
    }

    @Override
    public ResponseEntity<SearchProductsResponse> searchProducts(Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria, SearchProductsRequest request) throws Exception {
        return ResponseEntity.ok(searchProductsService.searchProducts(chunkNumber, nrElementsPerChunk, sortCriteria, request));
    }

    @Override
    public ResponseEntity<GetCategoryTreeResponse> getCategoryTree(Integer categoryId) {
        return ResponseEntity.ok(productCategorizeService.getCategoryTree(categoryId));
    }

    @Override
    public ResponseEntity<SearchProductsResponse> searchProductsByCategory(Integer categoryId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria) {
        return ResponseEntity.ok(searchProductsService.searchProductsByCategory(categoryId, chunkNumber, nrElementsPerChunk, sortCriteria));
    }
}
