package com.switchvov.redis.demo.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁 helper类 测试类
 *
 * @author switch
 * @since 2021/4/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CLockHelperTest {
    @Test
    public void getLock() {
        String lockName = "LOCK:NAME";
        RLock lock = CLockHelper.getLock(lockName);
        boolean locked = CLockHelper.tryLock(lock, 5, 5, TimeUnit.SECONDS);
        if (locked) {
            log.info("获取锁, key:{}", lockName);
        }
        CLockHelper.unlock(lock);
    }
}