package com.swapit.user.service.impl;

import com.swapit.apiGateway.api.dto.response.ProductDTO;
import com.swapit.apiGateway.service.ApiGatewayPublicService;
import com.swapit.user.service.ExternalOperationsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ExternalOperationsServiceImpl implements ExternalOperationsService {

    private final ApiGatewayPublicService apiGatewayPublicService;

    @Override
    public List<ProductDTO> getAllProductsByUserId(Integer userId) {
            return apiGatewayPublicService.getAllProductsByUserId(userId).getBody();
    }
}
