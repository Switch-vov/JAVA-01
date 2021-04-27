package com.switchvov.redis.pubsub.demo.sub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author switch
 * @since 2021/4/27
 */
@Component
@Slf4j
public class OrderSub implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("message:{},channel:{},pattern:{}",
                new String(message.getBody()),
                new String(message.getChannel()),
                new String(pattern)
        );
    }
}
