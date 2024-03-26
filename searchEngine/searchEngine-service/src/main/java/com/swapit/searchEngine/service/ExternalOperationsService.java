package com.swapit.searchEngine.service;

import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.product.api.domain.request.GetProductsByCategoryRequest;
import com.swapit.product.api.domain.request.GetProductsByIdsRequest;
import com.swapit.product.api.domain.response.GetProductsByCategoryResponse;
import com.swapit.product.api.domain.response.GetProductsByIdsResponse;

public interface ExternalOperationsService {
    ProductDTO getProductById(Integer productId);
    GetProductsByIdsResponse getProductsByIds(GetProductsByIdsRequest request);
    GetProductsByCategoryResponse getProductsByCategory(GetProductsByCategoryRequest request);
}
