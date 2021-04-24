package com.switchvov.smq.demo;

import com.switchvov.smq.core.SmqBroker;
import com.switchvov.smq.core.SmqConsumer;
import com.switchvov.smq.core.SmqMessage;
import com.switchvov.smq.core.SmqProducer;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author switch
 * @since 2021/4/24
 */
@Slf4j
public class SmqDemo {

    @SneakyThrows
    public static void main(String[] args) {

        String topic = "kk.test";
        SmqBroker broker = new SmqBroker();
        broker.createTopic(topic);
        final boolean[] flag = new boolean[1];
        flag[0] = true;
        new Thread(() -> consumer("CONSUMER_TEST", topic, broker, flag)).start();
        new Thread(() -> consumer("CONSUMER_TEST_2", topic, broker, flag)).start();

        SmqProducer producer = broker.createProducer();
        for (int i = 0; i < 1000; i++) {
            Order order = new Order(1000L + i, System.currentTimeMillis(), "USD2CNY", 6.51d);
            producer.send(topic, new SmqMessage<>(null, order));
        }
        Thread.sleep(500);
        System.out.println("点击任何键，发送一条消息；点击q或e，退出程序。");
        while (true) {
            char message = (char) System.in.read();
            if (message > 20) {
                log.info("发送消息:{}", message);
                producer.send(topic, new SmqMessage<>(null, new Order(100000L + message, System.currentTimeMillis(), "USD2CNY", 6.52d)));
            }

            if (message == 'q' || message == 'e') break;
        }
        flag[0] = false;
    }

    private static void consumer(String groupId, String topic, SmqBroker broker, boolean[] flag) {
        SmqConsumer<Order> consumer = broker.createConsumer(groupId);
        consumer.subscribe(topic);
        while (flag[0]) {
            SmqMessage<Order> message = null;
            try {
                message = consumer.poll(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (null != message) {
                log.info("消费者GID:{}, TOPIC:{}, 消息:{}", groupId, topic, message);
                consumer.ask(message);
            }
        }
        System.out.println("程序退出。");
    }
}
