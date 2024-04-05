package com.swapit.commons.hazelcast;

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import com.swapit.commons.hazelcast.serializers.ZonedDateTimeCompactSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class HazelcastConfig {

    @Value("${hazelcast.member.ip}")
    private String memberIp;
    @Value("${hazelcast.instance.name}")
    private String hazelcastInstanceName;

    @Bean
    public Config hazelcastConfiguration() {
        Config config = new Config();

        config.setInstanceName(hazelcastInstanceName);

        NetworkConfig networkConfig = config.getNetworkConfig();
        JoinConfig joinConfig = networkConfig.getJoin();
        joinConfig.getMulticastConfig().setEnabled(false);

        TcpIpConfig tcpIpConfig = joinConfig.getTcpIpConfig();
        tcpIpConfig.setEnabled(true);
        tcpIpConfig.addMember(memberIp);
        CompactSerializationConfig compactSerializationConfig = config.getSerializationConfig().getCompactSerializationConfig();
        compactSerializationConfig.addSerializer(new ZonedDateTimeCompactSerializer());

        return config;
    }

    @Bean
    public CacheManager getCacheManager() {
        return new HazelcastCacheManager(hazelcastInstance(hazelcastConfiguration()));
    }

    @Bean
    public HazelcastInstance hazelcastInstance(Config config) {
        return Hazelcast.newHazelcastInstance(config);
    }
}