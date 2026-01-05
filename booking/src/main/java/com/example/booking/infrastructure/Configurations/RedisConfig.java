package com.example.booking.infrastructure.Configurations;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {
/* 
    @Bean
    public RedisCacheConfiguration cacheConfiguration() {

        GenericJacksonJsonRedisSerializer serializer =
                new GenericJacksonJsonRedisSerializer();

        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60))
                .disableCachingNullValues()
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(serializer)
                );
    }
                */
}
