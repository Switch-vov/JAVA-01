package com.switchvov.nio.gateway.outbound.okhttp;

import com.switchvov.nio.gateway.filter.HeaderHttpResponseFilter;
import com.switchvov.nio.gateway.filter.HttpRequestFilter;
import com.switchvov.nio.gateway.filter.HttpResponseFilter;
import com.switchvov.nio.gateway.outbound.GatewayOutboundHandler;
import com.switchvov.nio.gateway.outbound.httpclient4.NamedThreadFactory;
import com.switchvov.nio.gateway.router.HttpEndpointRouter;
import com.switchvov.nio.gateway.router.RandomHttpEndpointRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import kotlin.Pair;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.http.protocol.HTTP;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static org.apache.http.HttpHeaders.CONNECTION;

/**
 * Okhttp Outbound Handler
 *
 * @author switch
 * @since 2021/1/31
 */
@Slf4j
public class OkhttpOutboundHandler implements GatewayOutboundHandler {
    private final OkHttpClient okHttpClient;
    private final ExecutorService proxyService;
    private final List<String> backendUrls;

    HttpResponseFilter filter = new HeaderHttpResponseFilter();
    HttpEndpointRouter router = new RandomHttpEndpointRouter();

    public OkhttpOutboundHandler(List<String> backends) {
        this.backendUrls = backends.stream()
                .map(backend -> backend.endsWith("/") ? backend.substring(0, backend.length() - 1) : backend)
                .collect(Collectors.toList());

        int cores = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        int queueSize = 2048;
        proxyService = new ThreadPoolExecutor(
                cores,
                cores,
                keepAliveTime,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        // 创建一个client
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.SECONDS)
                .readTimeout(1, TimeUnit.SECONDS)
                .build();
        Dispatcher dispatcher = okHttpClient.dispatcher();
        dispatcher.setMaxRequestsPerHost(8);
    }


    @Override
    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, HttpRequestFilter filter) {
        String backendUrl = router.route(this.backendUrls);
        final String url = backendUrl + fullRequest.uri();
        filter.filter(fullRequest, ctx);
        proxyService.submit(() -> fetchGet(fullRequest, ctx, url));
    }

    private void fetchGet(final FullHttpRequest inbound, final ChannelHandlerContext ctx, final String url) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                try {
                    handleResponse(inbound, ctx, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void handleResponse(FullHttpRequest fullRequest, ChannelHandlerContext ctx, Response endpointResponse) throws Exception {
        FullHttpResponse response = null;
        try {
            ResponseBody responseBody = endpointResponse.body();
            byte[] body = new byte[0];
            if (Objects.nonNull(responseBody)) {
                body = responseBody.bytes();
            }
            log.info(new String(body));
            log.info(String.valueOf(body.length));

            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));

            filter.filter(response);

            for (Pair<? extends String, ? extends String> header : endpointResponse.headers()) {
                response.headers().set(header.getFirst(), header.getSecond());
                log.info("{} => {}", header.getFirst(), header.getSecond());
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            exceptionCaught(ctx, e);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    Objects.requireNonNull(response).headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
            ctx.flush();
        }

    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


}
