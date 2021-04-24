package com.switchvov.smq.core.serializer;

import com.alibaba.fastjson.JSON;

/**
 * @author switch
 * @since 2021/4/24
 */
public class SmqFastjsonSerializer implements SmqSerializer {
    @Override
    public byte[] serialize(Object obj) {
        return JSON.toJSONBytes(obj);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
