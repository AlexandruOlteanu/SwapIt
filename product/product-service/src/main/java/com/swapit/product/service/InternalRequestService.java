package com.swapit.product.service;

import com.swapit.apiGateway.api.dto.response.ProductDTO;

import java.util.List;

public interface InternalRequestService {
    List<ProductDTO> getAllProductsByUserId(Integer userId);
}
