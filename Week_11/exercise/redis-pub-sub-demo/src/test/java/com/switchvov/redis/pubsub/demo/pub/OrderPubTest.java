package com.switchvov.redis.pubsub.demo.pub;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author switch
 * @since 2021/4/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderPubTest {
    @Autowired
    private OrderPub orderPub;

    @Test
    public void pubOrder() {
        orderPub.pubOrder();
    }
}