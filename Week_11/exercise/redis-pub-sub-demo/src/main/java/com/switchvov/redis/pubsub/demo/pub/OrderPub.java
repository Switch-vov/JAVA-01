package com.switchvov.redis.pubsub.demo.pub;

import com.alibaba.fastjson.JSON;
import com.switchvov.redis.pubsub.demo.configure.RedisProperties;
import com.switchvov.redis.pubsub.demo.domain.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author switch
 * @since 2021/4/27
 */
@Component
public class OrderPub {
    private final StringRedisTemplate template;
    private final RedisProperties redisProperties;

    public OrderPub(
            StringRedisTemplate template,
            RedisProperties redisProperties
    ) {
        this.template = template;
        this.redisProperties = redisProperties;
    }

    public void pubOrder() {
        for (int i = 0; i < 100000; i++) {
            Order order = new Order().setId(i).setType(i).setAmount(BigDecimal.valueOf(i));
            template.convertAndSend(redisProperties.getTopic(), JSON.toJSONString(order));
        }
    }
}
