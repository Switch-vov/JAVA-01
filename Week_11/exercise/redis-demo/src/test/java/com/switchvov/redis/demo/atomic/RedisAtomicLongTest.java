package com.switchvov.redis.demo.atomic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author switch
 * @since 2021/4/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisAtomicLongTest {
    @Autowired
    private RedisAtomicLong redisAtomicLong;

    private String key = "PRODUCT:P1";

    @Test
    public void aInc() {
        Long inc = 100L;
        Long inventory = redisAtomicLong.inc(key, inc);
        assertEquals(inc, inventory);
        log.info("当前库存为:{}", inventory);
    }

    @Test
    public void bDec() {
        Long dec = 100L;
        for (int i = 0; i < dec; i++) {
            Long inventory = redisAtomicLong.dec(key, 1);
            log.info("当前库存为:{}", inventory);
        }
        assertEquals(0L, redisAtomicLong.get(key).longValue());
    }
}