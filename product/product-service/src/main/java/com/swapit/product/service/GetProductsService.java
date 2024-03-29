package com.swapit.product.service;

import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.product.api.domain.request.GetProductsByCategoryRequest;
import com.swapit.product.api.domain.request.GetProductsByIdsRequest;
import com.swapit.product.api.domain.response.GetProductsByCategoryResponse;
import com.swapit.product.api.domain.response.GetProductsByIdsResponse;
import com.swapit.product.api.domain.response.GetProductsResponse;

public interface GetProductsService {
    GetProductsResponse getProductsByUser(Integer userId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria);
    GetProductsResponse getLikedProductsByUser(Integer userId, Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria);
    GetProductsByIdsResponse getProductsByIds(GetProductsByIdsRequest request);
    ProductDTO getProductById(Integer productId);
    GetProductsByCategoryResponse getProductsByCategory(GetProductsByCategoryRequest request);
    GetProductsResponse getRecommendedProducts(Integer chunkNumber, Integer nrElementsPerChunk, String sortCriteria);
}
