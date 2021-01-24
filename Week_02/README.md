## 第二周

**Week02 作业题目（周三）：**

**1.（选做）** 使用 GCLogAnalysis.java 自己演练一遍串行/并行/CMS/G1的案例。

**2.（选做）** 使用压测工具（wrk 或 sb），演练 gateway-server-0.0.1-SNAPSHOT.jar 示例。

**3.（选做）** 如果自己本地有可以运行的项目，可以按照 2 的方式进行演练。

**4.（必做）** 根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 和堆内存的总结，提交到 GitHub。

|垃圾收集器| GC算法| 总结|
|:----|:----|:----|
|Serial GC|young 区 serial收集器(标记-复制)<br/> old 区 Serial Old收集器(标记-整理)| 单线程执行，执行时STW，业务暂停运行。<br/>在出现多核cpu后，不要在生产环境使用 |
|Parallel GC|young 区 parallel Scavenge收集器(标记 复制)<br/> old 区 Parallel Old算法(标记-整理)| 多线程执行，执行时STW。<br/>吞吐量大、可以用在时延要求不高的系统中。 |
| CMS GC | young区 parNew算法(标记-复制)<br/> old 区 CMS收集器(标记-清除)| 多线程执行，CMS 6个阶段 STW时间较短。<br/>适合内存不大(不超过8G)，但是追求低延时的系统 |
| G1 GC | 将内存分为多个region，region有各自的分代属性，在young gc(标记-复制)，在mixed gc(标记-复制 标记-整理)| 因为GC时间是可控的，适用于内存大，但是最求低延时的系统  |
| ZGC | 除了初始标记阶段是STW外，其余阶段基本都能并发执行。关键技术为：着色指针和读屏障| 因为基本所有阶段都能并发，所以延迟极低，故在任何的内存大小，超低延时系统都适用 |

总结：考虑好系统对“延时”、“吞吐量”的要求以及机器内存大小，就能使用上表做出选择了。

**Week02 作业题目（周日）：**

**1.（选做）** 运行课上的例子，以及 Netty 的例子，分析相关现象。

在使用`wrk -c40 -d30s -t10 http://localhost:880x`进行压测后

### 单线程处理 socket

吞吐量低，eden区增长缓慢，资源使用率极低。

### 每个请求一个线程

吞吐量高，频繁进行`minor gc`且`young`区的`eden`、`s1`、`s2`内存呈脉冲状。内存占用忽高忽低。

### 固定大小线程池处理

吞吐量较高，`young`区的`eden`内存呈山峰状、`s1`、`s2`内存呈梯形，内存使用平稳。

### Netty

吞吐量极高，响应时间平均为`us`级，`young`区的`eden`内存呈山峰状、`s1`、`s2`内存呈梯形，内存使用平稳。


**2.（必做）** 写一段代码，使用 HttpClient 或 OkHttp 访问 http://localhost:8801 ，代码提交到 GitHub

```java
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
```