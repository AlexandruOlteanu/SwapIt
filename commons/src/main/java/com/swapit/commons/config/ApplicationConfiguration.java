package com.swapit.commons.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Configuration("commonsConfiguration")
public class ApplicationConfiguration {
    private static final String ALGORITHM = "AES";

    @Value("${application.encrypt.secret.key}")
    private String secretKey;
    @Bean
    public SecretKeySpec getSecretKeySpec() {
        byte[] keyBytes = Arrays.copyOf(secretKey.getBytes(StandardCharsets.UTF_8), 32);
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }
}
