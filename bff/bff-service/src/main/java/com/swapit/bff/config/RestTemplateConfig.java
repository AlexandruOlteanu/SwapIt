package com.swapit.bff.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration("externalCallsConfiguration")
public class RestTemplateConfig {

    @Bean(name = "externalCallRestTemplate")
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
