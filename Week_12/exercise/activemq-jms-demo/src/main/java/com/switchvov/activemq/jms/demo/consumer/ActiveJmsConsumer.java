package com.switchvov.activemq.jms.demo.consumer;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author switch
 * @since 2021/4/26
 */
@Component
public class ActiveJmsConsumer implements InitializingBean {
    private final ActiveMQConnection activeMQConnection;

    public ActiveJmsConsumer(
            ActiveMQConnection activeMQConnection
    ) {
        this.activeMQConnection = activeMQConnection;
    }

    private void consumeTopic() throws JMSException {
        // 创建消费者
        Session session = activeMQConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = new ActiveMQTopic("test.topic");
        MessageConsumer consumer = session.createConsumer(destination);
        final AtomicInteger count = new AtomicInteger(0);
        MessageListener listener = message -> {
            try {
                // 打印所有的消息内容
                System.out.println(count.incrementAndGet() + " => receive from " + destination + ": " + message);

            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        // 绑定消息监听器
        consumer.setMessageListener(listener);
    }

    private void consumeQueue() throws JMSException {
        // 创建消费者
        Session session = activeMQConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = new ActiveMQQueue("test.queue");
        MessageConsumer consumer = session.createConsumer(destination);
        final AtomicInteger count = new AtomicInteger(0);
        MessageListener listener = message -> {
            try {
                // 打印所有的消息内容
                System.out.println(count.incrementAndGet() + " => receive from " + destination + ": " + message);

            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        // 绑定消息监听器
        consumer.setMessageListener(listener);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        consumeTopic();
        consumeQueue();
    }
}
