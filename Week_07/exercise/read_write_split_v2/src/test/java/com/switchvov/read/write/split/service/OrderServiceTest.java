package com.switchvov.read.write.split.service;

import com.switchvov.read.write.split.domain.Order;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author switch
 * @since 2021/5/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @SneakyThrows
    @Test
    public void saveAndGet() {
        for (int i = 0; i < 10; i++) {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            Order order = new Order()
                    .setOrderId("OID" + random.nextInt(100000000, 1000000000))
                    .setUserId(random.nextLong(100000000, 1000000000))
                    .setSkuNumber("SID" + random.nextInt(100000000, 1000000000))
                    .setTotal(new BigDecimal(String.valueOf(random.nextInt(100000000, 1000000000))));
            log.info("需要保存的order:{}", order);
            boolean saved = orderService.save(order);
            log.info("保存是否成功:{}", saved);
            // 因为主从同步有延时，故这次查询一般返回为null
            Order selectOrder1 = orderService.getByOrderId(order.getOrderId());
            log.info("从数据库中获取的order:{}", selectOrder1);
            // 1s后在查询
            TimeUnit.SECONDS.sleep(1);
            Order selectOrder2 = orderService.getByOrderId(order.getOrderId());
            log.info("从数据库中获取的order:{}", selectOrder2);
            log.info("\n\n");
        }
    }
}