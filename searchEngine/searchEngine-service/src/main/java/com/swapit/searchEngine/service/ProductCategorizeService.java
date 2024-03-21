package com.swapit.searchEngine.service;

import com.swapit.searchEngine.api.service.domain.request.AddNewProductCategoryRequest;
import com.swapit.searchEngine.api.service.domain.response.GetProductCategoriesResponse;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;

public interface ProductCategorizeService {
    void addNewProductCategory(AddNewProductCategoryRequest request);
    GetProductCategoriesResponse getAllProductCategories();
}
