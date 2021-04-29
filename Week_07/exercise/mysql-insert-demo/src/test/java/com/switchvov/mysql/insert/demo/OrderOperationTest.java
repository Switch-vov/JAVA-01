package com.switchvov.mysql.insert.demo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @author switch
 * @since 2021/4/29
 */
@Slf4j
public class OrderOperationTest {

    private OrderOperation orderOperation;

    @Before
    public void setUp() throws Exception {
        orderOperation = new OrderOperation();
    }

    @After
    public void tearDown() throws Exception {
        orderOperation.close();
    }

    @SneakyThrows
    @Test
    public void save() {
        StopWatch stopWatch = new StopWatch("save");
        stopWatch.start();
        orderOperation.save();
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        log.info("time:{}", stopWatch.getTotalTimeSeconds());
    }
}