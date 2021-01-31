package com.switchvov.nio.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * request过滤器
 */
public interface HttpRequestFilter {
    /**
     * 对request进行过滤操作
     *
     * @param request Netty封装的Request对象
     * @param ctx     channel的上下文
     */
    void filter(FullHttpRequest request, ChannelHandlerContext ctx);
}
