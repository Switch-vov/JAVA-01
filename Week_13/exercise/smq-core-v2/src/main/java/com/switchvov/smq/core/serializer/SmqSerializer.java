package com.switchvov.smq.core.serializer;

/**
 * 序列化器
 *
 * @author switch
 * @since 2021/4/24
 */
public interface SmqSerializer {
    /**
     * 序列化
     *
     * @param obj
     * @return
     */
    byte[] serialize(Object obj);

    /**
     * 反序列化
     *
     * @param bytes
     * @param clazz
     * @return
     */
    <T> T deserialize(byte[] bytes, Class<T> clazz);
}
