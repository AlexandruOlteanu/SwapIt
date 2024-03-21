package com.swapit.product.service;

import com.swapit.product.api.domain.response.GetProductsResponse;
import com.swapit.product.api.domain.response.SearchProductData;

public interface GetProductsService {
    GetProductsResponse getAllProductsByUserId(Integer userId);
    SearchProductData searchProductData(Integer productId);
}
