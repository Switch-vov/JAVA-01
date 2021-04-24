package com.switchvov.smq.core;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * @author switch
 * @since 2021/4/24
 */
@AllArgsConstructor
@Data
public class SmqMessage<T> {
    private Map<String, Object> headers;
    private T body;
    private int offset;
    private Class<?> clazz;

    public SmqMessage(Map<String, Object> headers, T body) {
        this.headers = headers;
        this.body = body;
        this.offset = 0;
        this.clazz = body.getClass();
    }

    public SmqMessage(Map<String, Object> headers, T body, int offset) {
        this.headers = headers;
        this.body = body;
        this.offset = offset;
    }
}
