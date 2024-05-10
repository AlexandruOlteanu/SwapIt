package com.swapit.apiGateway.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class FrontendConfig implements WebMvcConfigurer {

    @Value("${ui.route}")
    private String uiUri;
    private static final String X_ERROR_CODE = "x_error_code";
    private static final String X_ERROR_MESSAGE = "x_error_message";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(uiUri)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders(X_ERROR_CODE, X_ERROR_MESSAGE)
                .allowCredentials(true);
    }
}
