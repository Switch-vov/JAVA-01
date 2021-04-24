package com.switchvov.smq.core;

/**
 * @author switch
 * @since 2021/4/24
 */
public final class SmqTopic {
    private String topic;

    private int capacity;

    private SmqQueue queue;

    public SmqTopic(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
        this.queue = new SmqQueue(capacity);
    }

    public boolean send(SmqMessage<?> message) {
        return queue.offer(message);
    }

    public SmqMessage<?> poll(String groupId, int timeout) throws InterruptedException {
        return queue.poll(groupId, timeout);
    }

    public boolean ask(String groupId, int offset) {
        return queue.ask(groupId, offset);
    }
}
