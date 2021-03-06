# 第四周

**Week04 作业题目（周三）：**

**1.（选做）** 把示例代码，运行一遍，思考课上相关的问题。也可以做一些比较。

**2.（必做）** 思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这个方法的返回值后，退出主线程? 写出你的方法，越多越好，提交到 GitHub。

一个简单的代码参考: https://github.com/kimmking/JavaCourseCodes/tree/main/03concurrency/0301/src/main/java/java0/conc0303/Homework03.java

```java
package com.switchvov.concurrent.exercise;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这个方法的返回值后，退出主线程? 写出你的方法，越多越好
 *
 * @author switch
 * @since 2021/2/7
 */
public class AsyncResult {
    public static void main(String[] args) {
        execute(Executors.newSingleThreadExecutor(), "Executors newSingleThreadExecutor");
        execute(Executors.newFixedThreadPool(1), "Executors newFixedThreadPool");
        execute(Executors.newCachedThreadPool(), "Executors newCachedThreadPool");
        execute(Executors.newScheduledThreadPool(1), "Executors newScheduledThreadPool");
        execute(Executors.newSingleThreadScheduledExecutor(), "Executors newSingleThreadScheduledExecutor");
        execute(AsyncResult::sum, "Callable");
        execute(new ThreadPoolExecutor(1, 1, 60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>()), "new ThreadPoolExecutor");
        execute(new ScheduledThreadPoolExecutor(1), "new ScheduledThreadPoolExecutor");
        execute(ForkJoinPool.commonPool(), "ForkJoinPool commonPool");
        exec(() -> ForkJoinPool.commonPool().invoke(ForkJoinTask.adapt(AsyncResult::sum)), "ForkJoinPool invoke");
        exec(() -> CompletableFuture.supplyAsync(AsyncResult::sum).join(), "CompletableFuture supplyAsync");
    }

    private static <T> void execute(Callable<T> callable, String name) {
        exec(() -> {
            FutureTask<T> futureTask = new FutureTask<>(callable);
            new Thread(futureTask).start();
            try {
                return futureTask.get();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }, name);
    }

    private static void execute(ExecutorService executor, String name) {
        exec(() -> {
            try {
                return executor.submit(AsyncResult::sum).get();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }, name);
        executor.shutdown();
    }

    private static <T> void exec(Supplier<T> supplier, String executorName) {
        long start = System.currentTimeMillis();
        T result = supplier.get();
        System.out.println(executorName + " 异步计算结果为：" + result);
        System.out.println(executorName + " 使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }
}
```

**Week04 作业题目（周日）：**

**1.（选做）** 列举常用的并发操作 API 和工具类，简单分析其使用场景和优缺点。

**2.（选做）** 请思考: 什么是并发? 什么是高并发? 实现高并发高可用系统需考虑哪些因素，对于这些你是怎么理解的?

**3.（选做）** 请思考: 还有哪些跟并发类似 / 有关的场景和问题，有哪些可以借鉴的解决办法。

**4.（必做）** 把多线程和并发相关知识梳理一遍，画一个脑图，截图上传到 GitHub 上。 可选工具:xmind，百度脑图，wps，MindManage，或其他。
![](exercise/多线程&并发.jpg)

