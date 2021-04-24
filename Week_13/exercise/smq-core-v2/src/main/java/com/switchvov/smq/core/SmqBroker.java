package com.switchvov.smq.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Broker+Connection
 *
 * @author switch
 * @since 2021/4/24
 */
public final class SmqBroker {

    public static final int CAPACITY = 1000000;

    private final Map<String, SmqTopic> kmqMap = new ConcurrentHashMap<>(64);

    public void createTopic(String name) {
        kmqMap.putIfAbsent(name, new SmqTopic(name, CAPACITY));
    }

    public SmqTopic findKmq(String topic) {
        return this.kmqMap.get(topic);
    }

    public SmqProducer createProducer() {
        return new SmqProducer(this);
    }

    public <T> SmqConsumer<T> createConsumer(String groupId) {
        return new SmqConsumer<>(this, groupId);
    }

}
