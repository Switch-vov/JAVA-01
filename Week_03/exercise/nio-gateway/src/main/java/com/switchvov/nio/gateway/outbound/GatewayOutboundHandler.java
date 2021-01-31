package com.switchvov.nio.gateway.outbound;

import com.switchvov.nio.gateway.filter.HttpRequestFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * 网关Outbound Handler
 *
 * @author switch
 * @since 2021/1/31
 */
public interface GatewayOutboundHandler {
    /**
     * 处理方法
     *
     * @param fullRequest
     * @param ctx
     * @param filter
     */
    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, HttpRequestFilter filter);
}
