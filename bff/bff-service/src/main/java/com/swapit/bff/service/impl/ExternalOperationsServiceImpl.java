package com.swapit.bff.service.impl;

import com.swapit.bff.service.ExternalOperationsService;
import com.swapit.bff.service.UrlGeneratorService;
import com.swapit.user.api.domain.request.LoginRequest;
import com.swapit.user.api.domain.request.RegisterRequest;
import com.swapit.user.api.domain.response.LoginResponse;
import com.swapit.user.api.domain.response.RegisterResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExternalOperationsServiceImpl implements ExternalOperationsService {

    @Qualifier("externalCallRestTemplate")
    private final RestTemplate restTemplate;
    private final UrlGeneratorService urlGeneratorService;
    @Override
    public LoginResponse login(LoginRequest request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.USER_LOGIN);
        log.info(url);
        try {
            return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(request), LoginResponse.class).getBody();
        } catch (Exception e) {
            log.error("Exception in User Login {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.USER_REGISTER);
        log.info(url);
        try {
            return restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(request), RegisterResponse.class).getBody();
        } catch (Exception e) {
            log.error("Exception in User Register {}", e.getMessage(), e);
            throw e;
        }
    }

}


