package com.swapit.product.service;

import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.product.api.domain.request.GetProductsByCategoryRequest;
import com.swapit.product.api.domain.request.GetProductsByIdsRequest;
import com.swapit.product.api.domain.response.GetProductsByCategoryResponse;
import com.swapit.product.api.domain.response.GetProductsByIdsResponse;
import com.swapit.product.api.domain.response.GetProductsResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface GetProductsService {
    GetProductsResponse getProductsByUserId(Integer userId);
    GetProductsByIdsResponse getProductsByIds(GetProductsByIdsRequest request);
    ProductDTO getProductById(Integer productId);
    GetProductsByCategoryResponse getProductsByCategory(GetProductsByCategoryRequest request);
}
