package com.swapit.product.service;

import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.product.api.domain.response.GetProductsResponse;

import java.util.List;

public interface GetProductsService {
    GetProductsResponse getAllProductsByUserId(Integer userId);
}
