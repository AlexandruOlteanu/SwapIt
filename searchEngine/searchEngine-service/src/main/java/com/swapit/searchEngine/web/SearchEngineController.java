package com.swapit.searchEngine.web;

import com.swapit.searchEngine.api.service.SearchEngineService;
import com.swapit.searchEngine.api.service.domain.request.AddNewProductCategoryRequest;
import com.swapit.searchEngine.api.service.domain.response.GetProductCategoriesResponse;
import com.swapit.searchEngine.service.ProductCategorizeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SearchEngineController implements SearchEngineService {

    private final ProductCategorizeService productCategorizeService;

    @Override
    public void addNewProductCategory(AddNewProductCategoryRequest request) {
        productCategorizeService.addNewProductCategory(request);
    }

    @Override
    public ResponseEntity<GetProductCategoriesResponse> getAllProductCategories() {
        return ResponseEntity.ok(productCategorizeService.getAllProductCategories());
    }
}
