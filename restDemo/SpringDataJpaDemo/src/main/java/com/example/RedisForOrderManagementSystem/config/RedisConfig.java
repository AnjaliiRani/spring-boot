package com.example.RedisForOrderManagementSystem.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class RedisConfig {


    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration defaultConfig =
                RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofMinutes(10));

        Map<String, RedisCacheConfiguration> cacheConfiguration = new HashMap<>();

        cacheConfiguration.put("product", defaultConfig.entryTtl(Duration.ofHours(1)));
        cacheConfiguration.put("users", defaultConfig.entryTtl(Duration.ofMinutes(10)));
        cacheConfiguration.put("order", defaultConfig.entryTtl(Duration.ofMinutes(2)));

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(defaultConfig)
                .withInitialCacheConfigurations(cacheConfiguration)
                .build();




    }

}
