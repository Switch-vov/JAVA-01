package com.switchvov.activemq.jms.demo.producer;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author switch
 * @since 2021/4/26
 */
@Component
public class ActiveJmsProducer {
    private final ActiveMQConnection activeMQConnection;

    public ActiveJmsProducer(
            ActiveMQConnection activeMQConnection
    ) {
        this.activeMQConnection = activeMQConnection;
    }

    public void sendTopicMessage() throws JMSException {
        Session session = activeMQConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = new ActiveMQTopic("test.topic");
        sendMessage(session, destination);
    }

    public void sendQueueMessage() throws JMSException {
        Session session = activeMQConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = new ActiveMQQueue("test.queue");
        // 创建消费者
        sendMessage(session, destination);
    }

    private void sendMessage(Session session, Destination destination) throws JMSException {
        // 创建消费者
        MessageProducer producer = session.createProducer(destination);
        int index = 0;
        while (index++ < 100) {
            TextMessage message = session.createTextMessage(index + " message.");
            producer.send(message);
        }
    }
}
