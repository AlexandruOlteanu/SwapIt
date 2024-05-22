package com.swapit.searchEngine.service;

import com.swapit.searchEngine.api.service.domain.request.AddNewProductCategoryRequest;
import com.swapit.searchEngine.api.service.domain.request.GetProductCategoryIdRequest;
import com.swapit.searchEngine.api.service.domain.response.GetCategoryTreeResponse;

public interface ProductCategorizeService {
    void addNewProductCategory(AddNewProductCategoryRequest request);
    Integer getProductCategoryId(GetProductCategoryIdRequest request);
    GetCategoryTreeResponse getCategoryTree(Integer categoryId);
}
