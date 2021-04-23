package com.switchvov.kafka.demo.consumer;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author switch
 * @since 2021/4/23
 */
@Component
@Slf4j
public class KafkaSpringConsumerListener {

    @KafkaListener(topics = "test_switch")
    public void listener(ConsumerRecord<String, String> data) {
        log.info("消费消息成功,topic:{},消息:{},record:{}",
                data.topic(),
                JSON.parseObject(data.value()),
                data
        );
    }
}
