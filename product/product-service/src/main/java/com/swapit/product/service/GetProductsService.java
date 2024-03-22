package com.swapit.product.service;

import com.swapit.product.api.domain.request.GetProductsByIdsRequest;
import com.swapit.product.api.domain.response.GetProductsByIdsResponse;
import com.swapit.product.api.domain.response.GetProductsResponse;
import com.swapit.product.api.domain.response.SearchProductData;

public interface GetProductsService {
    GetProductsResponse getProductsByUserId(Integer userId);
    GetProductsByIdsResponse getProductsByIds(GetProductsByIdsRequest request);
    SearchProductData searchProductData(Integer productId);
}
