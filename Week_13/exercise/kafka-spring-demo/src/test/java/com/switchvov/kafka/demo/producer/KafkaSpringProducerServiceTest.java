package com.switchvov.kafka.demo.producer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;

/**
 * @author switch
 * @since 2021/4/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaSpringProducerServiceTest {
    @Autowired
    private KafkaSpringProducerService producerService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sendTestMessage() throws ExecutionException, InterruptedException {
        producerService.sendTestMessage();
    }
}