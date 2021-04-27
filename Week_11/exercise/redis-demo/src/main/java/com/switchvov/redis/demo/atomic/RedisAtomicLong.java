package com.switchvov.redis.demo.atomic;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Objects;

/**
 * @author switch
 * @since 2021/4/27
 */
public class RedisAtomicLong {
    private final StringRedisTemplate template;

    public RedisAtomicLong(
            RedisConnectionFactory factory
    ) {
        // 创建一个template
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(factory);
        template.afterPropertiesSet();
        this.template = template;
    }

    public Long inc(String key, long delta) {
        BoundValueOperations<String, String> valueOps = template.boundValueOps(key);
        return valueOps.increment(delta);
    }

    public Long dec(String key, long delta) {
        return inc(key, -delta);
    }

    public Long get(String key) {
        BoundValueOperations<String, String> valueOps = template.boundValueOps(key);
        return Long.valueOf(Objects.requireNonNull(valueOps.get()));
    }
}
