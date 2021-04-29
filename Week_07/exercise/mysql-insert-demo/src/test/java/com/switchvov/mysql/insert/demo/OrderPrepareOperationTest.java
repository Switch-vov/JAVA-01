package com.switchvov.mysql.insert.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StopWatch;

import static org.junit.Assert.*;

/**
 * @author switch
 * @since 2021/4/29
 */
@Slf4j
public class OrderPrepareOperationTest {
    private OrderPrepareOperation orderPrepareOperation;

    @Before
    public void setUp() throws Exception {
        orderPrepareOperation = new OrderPrepareOperation();
    }

    @After
    public void tearDown() throws Exception {
        orderPrepareOperation.close();
    }

    @Test
    public void save() {
        StopWatch stopWatch = new StopWatch("save");
        stopWatch.start();
        orderPrepareOperation.save();
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        log.info("time:{}", stopWatch.getTotalTimeSeconds());
    }

    @Test
    public void saveBatch() {
        StopWatch stopWatch = new StopWatch("saveBatch");
        stopWatch.start();
        orderPrepareOperation.saveBatch();
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        log.info("time:{}", stopWatch.getTotalTimeSeconds());
    }

    @Test
    public void saveMultipleValue() {
        StopWatch stopWatch = new StopWatch("saveMultipleValue");
        stopWatch.start();
        orderPrepareOperation.saveMultipleValue();
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        log.info("time:{}", stopWatch.getTotalTimeSeconds());
    }

    @Test
    public void saveMultipleValueNotIndex() {
        StopWatch stopWatch = new StopWatch("saveMultipleValueNotIndex");
        stopWatch.start();
        orderPrepareOperation.saveMultipleValueNotIndex();
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        log.info("time:{}", stopWatch.getTotalTimeSeconds());
    }

    @Test
    public void saveMultipleValueNotIndexNotTime() {
        StopWatch stopWatch = new StopWatch("saveMultipleValueNotIndexNotTime");
        stopWatch.start();
        orderPrepareOperation.saveMultipleValueNotIndexNotTime();
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
        log.info("time:{}", stopWatch.getTotalTimeSeconds());
    }
}