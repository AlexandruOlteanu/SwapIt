package com.swapit.product.service;

import com.swapit.product.api.domain.response.GetProductsResponse;

public interface GetProductsService {
    GetProductsResponse getAllProductsByUserId(Integer userId);
}
