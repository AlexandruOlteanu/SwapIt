package com.swapit.user.service;

import com.swapit.product.api.domain.response.GetProductsResponse;

public interface ExternalOperationsService {

    GetProductsResponse getAllProductsByUserId(Integer userId);
}
