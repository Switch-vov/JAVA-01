package com.switchvov.redis.pubsub.demo.configure;

import com.switchvov.redis.pubsub.demo.sub.OrderSub;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.Collections;

/**
 * @author switch
 * @since 2021/4/27
 */
@Configuration
public class RedisConfiguration {
    @Bean
    public MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new OrderSub());
    }

    @Bean
    public RedisMessageListenerContainer customRedisContainer(
            final RedisConnectionFactory factory,
            final RedisProperties redisProperties
    ) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.addMessageListener(messageListener(), Collections.singleton(new ChannelTopic(redisProperties.getTopic())));
        return container;
    }
}
