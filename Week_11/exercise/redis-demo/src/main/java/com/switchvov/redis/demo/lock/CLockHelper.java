package com.switchvov.redis.demo.lock;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁 helper类
 *
 * @author switch
 * @since 2021/4/27
 */
@Slf4j
public class CLockHelper implements ApplicationContextAware {
    private static RedissonClient client;

    public static RLock getLock(String lockKey) {
        return client.getLock(lockKey);
    }

    public static boolean tryLock(RLock lock, int waitTime, int leaseTime, TimeUnit unit) {
        try {
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            return false;
        }
    }

    public static void unlock(RLock lock) {
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        client = applicationContext.getBean(RedissonClient.class);
    }
}
