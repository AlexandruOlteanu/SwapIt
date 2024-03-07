package com.swapit.product.service.impl;

import com.swapit.apiGateway.service.ApiGatewayPublicService;
import com.swapit.product.service.ExternalOperationsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class ExternalOperationsServiceImpl implements ExternalOperationsService {

    private final ApiGatewayPublicService apiGatewayPublicService;

    @Override
    public Integer getUserIdByUsernameOrEmail(String username, String email) {
            return apiGatewayPublicService.getUserIdByUsernameOrEmail(username, email);
    }
}
