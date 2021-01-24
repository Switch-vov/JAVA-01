package com.switchvov.nio.learning.other.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import static io.netty.channel.ChannelOption.*;

/**
 * @author switch
 * @since 2021/1/24
 */
public class NettyHttpServer {
    public static void main(String[] args) throws InterruptedException {
        int port = 8088;
        NioEventLoopGroup masterGroup = new NioEventLoopGroup(2);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(16);

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.option(SO_BACKLOG, 128)
                    .option(TCP_NODELAY, true)
                    .option(SO_KEEPALIVE, true)
                    .option(SO_REUSEADDR, true)
                    .option(SO_RCVBUF, 32 * 1024)
                    .option(SO_SNDBUF, 32 * 1024)
                    .option(EpollChannelOption.SO_REUSEPORT, true)
                    .childOption(SO_KEEPALIVE, true)
                    .option(ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            bootstrap.group(masterGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new HttpInitializer());

            Channel channel = bootstrap.bind(port).sync().channel();
            System.out.println("开启netty http服务器，监听地址和端口为 http://127.0.0.1:" + port + '/');
            channel.closeFuture().sync();
        } finally {
            masterGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
