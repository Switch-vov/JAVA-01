package com.switchvov.nio.learning.exercise;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

/**
 * 写一段代码，使用 HttpClient 或 OkHttp 访问 http://localhost:8801 ，代码提交到 GitHub
 *
 * @author switch
 * @since 2021/1/22
 */
public class Client {
    public static void main(String[] args) throws IOException {
        // 创建一个client
        OkHttpClient client = new OkHttpClient.Builder().build();
        // 创建一个request
        Request request = new Request.Builder()
                .url("http://localhost:8801")
                .build();
        // 同步执行请求
        Response resp = client.newCall(request).execute();
        // 输出请求接口
        System.out.println(Objects.requireNonNull(resp.body()).string());
    }
}
