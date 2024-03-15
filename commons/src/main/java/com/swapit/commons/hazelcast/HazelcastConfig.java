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

import static com.swapit.commons.cache.CacheConstants.CACHE_CONVERSATIONS_PREVIEW;


@Configuration
public class HazelcastConfig {

    @Value("${hazelcast.member.ip}")
    private String memberIp;
    @Value("${cache.conversations.preview.ttl}")
    private Integer cacheConversationsPreviewTtl;

    @Bean
    public Config hazelcastConfiguration() {
        Config config = new Config();

        // Customize the network configuration
        NetworkConfig networkConfig = config.getNetworkConfig();
        JoinConfig joinConfig = networkConfig.getJoin();
        joinConfig.getMulticastConfig().setEnabled(false); // Disable multicast

        MapConfig mapConfig = new MapConfig();
        mapConfig.setName(CACHE_CONVERSATIONS_PREVIEW);
        mapConfig.setTimeToLiveSeconds(cacheConversationsPreviewTtl);

        config.addMapConfig(mapConfig);

        // Enable and configure TCP/IP join
        TcpIpConfig tcpIpConfig = joinConfig.getTcpIpConfig();
        tcpIpConfig.setEnabled(true);
        tcpIpConfig.addMember(memberIp); // Specify members, for simplicity using localhost
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