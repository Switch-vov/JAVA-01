package com.switchvov.kafka.demo.consumer;

import com.alibaba.fastjson.JSON;
import com.switchvov.kafka.demo.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author switch
 * @since 2021/4/23
 */
@Slf4j
public class KafkaConsumerDemo {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("group.id", "group1");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singleton("demo-source"));

        while (true) {
            ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(10));
            poll.forEach(o -> {
                Order order = JSON.parseObject(o.value(), Order.class);
                log.info("order = {}", order);
            });
        }
    }
}
