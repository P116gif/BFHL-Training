package com.example.booking.infrastructure.persistence;

import java.time.Duration;
import java.util.UUID;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.example.booking.interfaces.Login.RefreshTokens;

@Component
public class RedisTokenStore implements RefreshTokens{
    
    private final StringRedisTemplate redisTemplate;

    public RedisTokenStore(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void store(UUID userId, String refreshToken) {
        redisTemplate.opsForValue().set(
            "refresh:" + userId,
            refreshToken,
            Duration.ofDays(7)
        );
    }

    @Override
    public boolean isValid(UUID userId, String refreshToken) {
        String storedToken = redisTemplate.opsForValue().get("refresh:" + userId);
        return refreshToken.equals(storedToken);
    }

    @Override
    public void revoke(UUID userId) {
        redisTemplate.delete("refresh:" + userId);
    }
}
