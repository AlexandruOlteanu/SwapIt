package com.swapit.user.service.impl;

import com.swapit.product.api.domain.response.GetProductsResponse;
import com.swapit.product.service.ProductPublicService;
import com.swapit.user.service.ExternalOperationsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class ExternalOperationsServiceImpl implements ExternalOperationsService {

    private final ProductPublicService productPublicService;

    @Override
    public GetProductsResponse getAllProductsByUserId(Integer userId) {
            return productPublicService.getProductsByUserId(userId).getBody();
    }
}
