package com.switchvov.redis.pubsub.demo.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author switch
 * @since 2021/4/27
 */
@Configuration
@ConfigurationProperties(prefix = "com.switchvov.redis")
@Data
public class RedisProperties {
    private String topic;
}
