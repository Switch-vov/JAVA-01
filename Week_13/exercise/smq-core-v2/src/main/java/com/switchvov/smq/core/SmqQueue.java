package com.switchvov.smq.core;

import com.switchvov.smq.core.serializer.SmqFastjsonSerializer;
import com.switchvov.smq.core.serializer.SmqSerializer;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author switch
 * @since 2021/4/24
 */
@Slf4j
public class SmqQueue {
    /**
     * offsetMap,key:消费GID,value:offset
     */
    private final Map<String, Integer> offsetMap;

    /**
     * 消息数组
     */
    private final SmqBytesMessage[] queue;

    /**
     * 容量
     */
    private final int capacity;

    /**
     * 写入位置
     */
    private int writeOffset;

    /**
     * 序列化器
     */
    private final SmqSerializer serializer;

    public SmqQueue(int capacity) {
        this(capacity, new SmqFastjsonSerializer());
    }

    public SmqQueue(int capacity, SmqSerializer serializer) {
        this.capacity = capacity;
        this.offsetMap = new ConcurrentHashMap<>();
        this.queue = new SmqBytesMessage[capacity];
        this.writeOffset = 0;
        this.serializer = serializer;
    }

    public synchronized boolean offer(SmqMessage<?> message) {
        if (writeOffset >= capacity) {
            log.warn("容量已满");
            return false;
        }
        SmqBytesMessage bytesMessage = new SmqBytesMessage(
                message.getHeaders(),
                serializer.serialize(message.getBody()),
                writeOffset,
                message.getClazz()
        );
        queue[writeOffset++] = bytesMessage;
        return true;
    }

    public SmqMessage<?> poll(String groupId) throws InterruptedException {
        return poll(groupId, 0);
    }

    public SmqMessage<?> poll(String groupId, int timeout) throws InterruptedException {
        return poll(groupId, timeout, TimeUnit.MILLISECONDS);
    }

    public SmqMessage<?> poll(String groupId, int timeout, TimeUnit timeUnit) throws InterruptedException {
        timeUnit.sleep(timeout);
        Integer offset = Optional.ofNullable(offsetMap.putIfAbsent(groupId, 0)).orElse(0);
        SmqBytesMessage bytesMessage = queue[offset];
        if (Objects.isNull(bytesMessage)) {
            return null;
        }
        return new SmqMessage<>(
                bytesMessage.getHeaders(),
                serializer.deserialize(bytesMessage.getBody(), bytesMessage.getClazz()),
                bytesMessage.getOffset()
        );
    }

    public boolean ask(String groupId, int offset) {
        Integer consumerOffset = offsetMap.get(groupId);
        if (Objects.isNull(consumerOffset)) {
            return false;
        }
        if (offset > capacity) {
            return false;
        }
        if (consumerOffset != offset) {
            return false;
        }
        offsetMap.put(groupId, offset + 1);
        return true;
    }

    private static class SmqBytesMessage extends SmqMessage<byte[]> {
        public SmqBytesMessage(Map<String, Object> headers, byte[] body, int offset, Class<?> clazz) {
            super(headers, body, offset);
            setClazz(clazz);
        }
    }
}
