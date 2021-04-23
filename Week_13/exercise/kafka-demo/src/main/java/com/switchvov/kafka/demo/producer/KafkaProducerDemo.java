package com.switchvov.kafka.demo.producer;

import com.alibaba.fastjson.JSON;
import com.switchvov.kafka.demo.domain.Order;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.math.BigDecimal;
import java.util.Properties;

/**
 * @author switch
 * @since 2021/4/23
 */
public class KafkaProducerDemo {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("bootstrap.servers", "localhost:9092");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        for (int i = 0; i < 10; i++) {
            Order order = new Order().setId(i).setAmount(BigDecimal.ONE).setType(1);
            ProducerRecord<String, String> record = new ProducerRecord<>("demo-source", JSON.toJSONString(order));
            producer.send(record);
        }
        producer.close();
    }
}
