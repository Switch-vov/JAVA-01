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
