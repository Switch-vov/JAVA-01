package io.kimmking.rpcfx.demo.provider.netty;

import io.kimmking.rpcfx.server.RpcfxInvoker;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author switch
 * @since 2021/5/9
 */
public class DemoServerInitializer extends ChannelInitializer<SocketChannel> {
    private final RpcfxInvoker rpcfxInvoker;

    public DemoServerInitializer(RpcfxInvoker rpcfxInvoker) {
        this.rpcfxInvoker = rpcfxInvoker;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 向管道加入处理器
        ChannelPipeline pipeline = ch.pipeline();

        // 1. HttpServerCodec 是netty 提供的处理http的 编-解码器
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        // 2.聚合 http header and http content
        pipeline.addLast("httpObjectAggregator", new HttpObjectAggregator(10 * 1024 * 1024));
        // 3. 增加一个自定义的handler
        pipeline.addLast("httpServerHandler", new DemoHttpServerHandler(rpcfxInvoker));
    }
}