package com.swapit.apiGateway.web;

import com.swapit.apiGateway.api.service.ApiGatewayService;
import com.swapit.commons.service.UrlGeneratorService;
import com.swapit.commons.service.impl.UrlGeneratorServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiGatewayController implements ApiGatewayService {

    @Qualifier("externalCallRestTemplate")
    private final RestTemplate restTemplate;
    private static final String USERNAME_PARAM = "username";
    private static final String EMAIL_PARAM = "email";
    private final UrlGeneratorService urlGeneratorService;

    @Override
    public Integer getUserIdByUsernameOrEmail(String username, String email) {

        String url = urlGeneratorService.getServiceURL(UrlGeneratorServiceImpl.UrlIdentifier.GET_USER_ID_BY_USERNAME_EMAIL);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(url))
                .queryParamIfPresent(USERNAME_PARAM, Optional.ofNullable(username))
                .queryParamIfPresent(EMAIL_PARAM, Optional.ofNullable(email));
        log.info(uriBuilder.toUriString());
        try {
            return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null, Integer.class).getBody();
        } catch (Exception e) {
            log.error("Exception in getting the User Id {}", e.getMessage(), e);
            throw e;
        }
    }
}
