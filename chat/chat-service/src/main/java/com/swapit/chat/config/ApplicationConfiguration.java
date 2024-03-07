package com.swapit.chat.config;

import com.pusher.rest.Pusher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("applicationConfiguration")
public class ApplicationConfiguration {

    @Value("${pusherApi.appId}")
    private String appId;
    @Value("${pusherApi.publicKey}")
    private String publicKey;
    @Value("${pusherApi.secretKey}")
    private String secretKey;
    @Value("${pusherApi.cluster}")
    private String cluster;
    @Value("${pusherApi.encrypted}")
    private boolean encrypted;

    @Bean(name = "pusherBean")
    public Pusher getPusher() {
        Pusher pusherApi = new Pusher(appId, publicKey, secretKey);
        pusherApi.setCluster(cluster);
        pusherApi.setEncrypted(encrypted);
        return pusherApi;
    }
}
