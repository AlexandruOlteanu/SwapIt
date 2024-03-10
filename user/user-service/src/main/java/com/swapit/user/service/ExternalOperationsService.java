package com.swapit.user.service;

import com.swapit.apiGateway.api.dto.response.ProductDTO;

import java.util.List;

public interface ExternalOperationsService {

    public List<ProductDTO> getAllProductsByUserId(Integer userId);
}
