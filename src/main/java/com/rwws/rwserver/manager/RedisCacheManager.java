package com.rwws.rwserver.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Component
public class RedisCacheManager {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisCacheManager(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value, Duration duration) {
        redisTemplate.opsForValue().set(key, value, duration);
    }

    public <R> Optional<R> get(String key, Function<Object, R> convert) {
        if (convert == null) throw new IllegalArgumentException();
        var value = redisTemplate.opsForValue().get(key);
        if (value == null) return Optional.empty();
        return Optional.ofNullable(convert.apply(value));
    }

    public void remove(List<String> keys) {
        redisTemplate.delete(keys);
    }

    public boolean exists(String key) {
        return Optional.ofNullable(redisTemplate.hasKey(key)).orElse(Boolean.FALSE);
    }

}
