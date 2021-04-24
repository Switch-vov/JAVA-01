package com.switchvov.smq.core;

/**
 * @author switch
 * @since 2021/4/24
 */
public class SmqProducer {

    private SmqBroker broker;

    public SmqProducer(SmqBroker broker) {
        this.broker = broker;
    }

    public <T> boolean send(String topic, SmqMessage<T> message) {
        SmqTopic smqTopic = this.broker.findKmq(topic);
        if (null == smqTopic) {
            throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
        }
        return smqTopic.send(message);
    }
}
