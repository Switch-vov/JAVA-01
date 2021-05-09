package io.kimmking.rpcfx.demo.provider.netty;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.server.RpcfxInvoker;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;

/**
 * @author switch
 * @since 2021/5/9
 */
@Slf4j
public class DemoHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    private final RpcfxInvoker rpcfxInvoker;

    public DemoHttpServerHandler(RpcfxInvoker rpcfxInvoker) {
        this.rpcfxInvoker = rpcfxInvoker;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        log.info("对应的channel={},pipeline={},通过pipeline获取channel={}", ctx.channel(), ctx.pipeline(), ctx.pipeline().channel());
        log.info("当前ctx的handler={}", ctx.handler());

        // 判断 msg 是不是 HttpRequest 请求
        if (msg instanceof FullHttpRequest) {
            log.info("ctx 类型={}", ctx.getClass());
            log.info("pipeline hashcode={},DemoHttpServerHandler hashcode={}", ctx.pipeline().hashCode(), this.hashCode());
            log.info("msg 类型={}", msg.getClass());
            log.info("客户端地址:{}", ctx.channel().remoteAddress());
            FullHttpRequest httpRequest = (FullHttpRequest) msg;
            // 获取uri, 过滤指定的资源
            URI uri = new URI(httpRequest.uri());
            if ("/favicon.ico".equals(uri.getPath())) {
                log.info("请求了 favicon.ico, 不做响应");
                return;
            }

            // 构建 RpcfxRequest 请求对象
            String requestBody = httpRequest.content().toString(CharsetUtil.UTF_8);
            RpcfxRequest rpcfxRequest = JSON.parseObject(requestBody, RpcfxRequest.class);

            // 调用实际方法
            RpcfxResponse rpcfxResponse = rpcfxInvoker.invoke(rpcfxRequest);

            //构造 http 响应
            String responseBody = JSON.toJSONString(rpcfxResponse);
            ByteBuf content = Unpooled.copiedBuffer(responseBody, CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json; charset=utf-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            ctx.writeAndFlush(response);
        }
    }
}