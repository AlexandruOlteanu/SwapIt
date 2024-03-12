package com.swapit.commons.hazelcast;

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.swapit.commons.hazelcast.serializers.ZonedDateTimeCompactSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnProperty(name = "application.hazelcast.enabled", havingValue = "true", matchIfMissing = true)
public class HazelcastConfig {

    @Value("${hazelcast.member.ip}")
    private String memberIp;

    @Bean
    public Config hazelcastConfiguration() {
        Config config = new Config();

        // Customize the network configuration
        NetworkConfig networkConfig = config.getNetworkConfig();
        JoinConfig joinConfig = networkConfig.getJoin();
        joinConfig.getMulticastConfig().setEnabled(false); // Disable multicast

        // Enable and configure TCP/IP join
        TcpIpConfig tcpIpConfig = joinConfig.getTcpIpConfig();
        tcpIpConfig.setEnabled(true);
        tcpIpConfig.addMember(memberIp); // Specify members, for simplicity using localhost
        CompactSerializationConfig compactSerializationConfig = config.getSerializationConfig().getCompactSerializationConfig();
        compactSerializationConfig.addSerializer(new ZonedDateTimeCompactSerializer());

        return config;
    }

    @Bean
    public HazelcastInstance hazelcastInstance(Config config) {
        return Hazelcast.newHazelcastInstance(config);
    }
}