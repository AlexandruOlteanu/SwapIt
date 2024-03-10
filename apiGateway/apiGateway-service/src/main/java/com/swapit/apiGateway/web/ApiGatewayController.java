package com.swapit.apiGateway.web;

import com.swapit.apiGateway.api.dto.response.ProductDTO;
import com.swapit.apiGateway.api.service.ApiGatewayService;
import com.swapit.commons.service.UrlGeneratorService;
import com.swapit.commons.service.impl.UrlGeneratorServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiGatewayController implements ApiGatewayService {

    @Qualifier("externalCallRestTemplate")
    private final RestTemplate restTemplate;
    private final UrlGeneratorService urlGeneratorService;
    private static final String USERNAME_PARAM = "username";
    private static final String EMAIL_PARAM = "email";
    private static final String USERNAME_ID_PARAM = "userId";

    @Override
    public ResponseEntity<Integer> getUserIdByUsernameOrEmail(String username, String email) {

        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.GET_USER_ID_BY_USERNAME_EMAIL);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(USERNAME_PARAM, Optional.ofNullable(username))
                .queryParamIfPresent(EMAIL_PARAM, Optional.ofNullable(email));
        log.info(uriBuilder.toUriString());
        try {
            return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, Integer.class);
        } catch (Exception e) {
            log.error("Exception in getting the User Id {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public ResponseEntity<List<ProductDTO>> getAllProductsByUserId(Integer userId) {
        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.GET_ALL_PRODUCTS_BY_USER_ID);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(USERNAME_ID_PARAM, Optional.ofNullable(userId));
        log.info(uriBuilder.toUriString());
        try {
            ParameterizedTypeReference<List<ProductDTO>> responseType = new ParameterizedTypeReference<>() {};
            return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, responseType);
        } catch (Exception e) {
            log.error("Exception in getting products for user {}", e.getMessage(), e);
            throw e;
        }
    }
}
