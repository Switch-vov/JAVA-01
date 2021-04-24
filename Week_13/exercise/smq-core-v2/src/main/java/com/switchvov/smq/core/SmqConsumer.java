package com.switchvov.smq.core;

/**
 * @author switch
 * @since 2021/4/24
 */
public class SmqConsumer<T> {

    private final SmqBroker broker;

    private SmqTopic smqTopic;

    private String groupId;

    public SmqConsumer(SmqBroker broker, String groupId) {
        this.broker = broker;
        this.groupId = groupId;
    }

    public void subscribe(String topic) {
        this.smqTopic = this.broker.findKmq(topic);
        if (null == smqTopic) {
            throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
        }
    }

    public SmqMessage<T> poll(int timeout) throws InterruptedException {
        return (SmqMessage<T>) smqTopic.poll(groupId, timeout);
    }

    public boolean ask(SmqMessage<T> message) {
        return smqTopic.ask(groupId, message.getOffset());
    }
}
