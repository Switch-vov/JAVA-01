package com.switchvov.activemq.jms.demo.producer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.JMSException;

import static org.junit.Assert.*;

/**
 * @author switch
 * @since 2021/4/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActiveJmsProducerTest {
    @Autowired
    private ActiveJmsProducer activeJmsProducer;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sendTopicMessage() throws JMSException {
        activeJmsProducer.sendTopicMessage();
    }

    @Test
    public void sendQueueMessage() throws JMSException {
        activeJmsProducer.sendQueueMessage();
    }
}