package com.swapit.searchEngine.service.impl;

import com.swapit.product.api.domain.dto.ProductDTO;
import com.swapit.product.api.domain.request.GetProductsByCategoryRequest;
import com.swapit.product.api.domain.request.GetProductsByIdsRequest;
import com.swapit.product.api.domain.response.GetProductsByCategoryResponse;
import com.swapit.product.api.domain.response.GetProductsByIdsResponse;
import com.swapit.product.service.ProductPublicService;
import com.swapit.searchEngine.service.ExternalOperationsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExternalOperationsServiceImpl implements ExternalOperationsService {
    private final ProductPublicService productPublicService;
    @Override
    public ProductDTO getProductById(Integer productId) {
        return productPublicService.getProductById(productId).getBody();
    }

    @Override
    public GetProductsByIdsResponse getProductsByIds(GetProductsByIdsRequest request) {
        return productPublicService.getProductsByIds(request).getBody();
    }

    @Override
    public GetProductsByCategoryResponse getProductsByCategory(GetProductsByCategoryRequest request) {
        return productPublicService.getProductsByCategory(request).getBody();
    }
}
