package com.switchvov.nio.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;

public class HeaderHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        HttpHeaders headers = response.headers();
        headers.set("version", "2");
    }
}
