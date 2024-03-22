package com.swapit.searchEngine.service;

import com.swapit.searchEngine.api.service.domain.request.AddNewProductCategoryRequest;
import com.swapit.searchEngine.api.service.domain.response.GetCategoryTreeResponse;
import com.swapit.searchEngine.api.service.domain.response.GetProductCategoriesResponse;

public interface ProductCategorizeService {
    void addNewProductCategory(AddNewProductCategoryRequest request);
    GetProductCategoriesResponse getAllProductCategories();
    GetCategoryTreeResponse getCategoryTree(Integer categoryId);
}
