package com.switchvov.nio.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * response过滤器
 */
public interface HttpResponseFilter {
    /**
     * 对response进行过滤操作
     *
     * @param response Netty封装的Response对象
     */
    void filter(FullHttpResponse response);
}
