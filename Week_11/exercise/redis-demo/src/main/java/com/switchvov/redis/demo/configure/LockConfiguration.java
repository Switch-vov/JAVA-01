package com.switchvov.redis.demo.configure;

import com.switchvov.redis.demo.atomic.RedisAtomicLong;
import com.switchvov.redis.demo.lock.CLockHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * @author switch
 * @since 2021/4/27
 */
@Configuration
public class LockConfiguration {
    @Bean
    public CLockHelper cLockHelper() {
        return new CLockHelper();
    }

    @Bean
    public RedisAtomicLong redisAtomicLong(
            RedisConnectionFactory factory
    ) {
        return new RedisAtomicLong(factory);
    }
}
