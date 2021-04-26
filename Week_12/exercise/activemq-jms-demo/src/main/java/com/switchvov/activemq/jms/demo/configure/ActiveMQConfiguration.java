package com.switchvov.activemq.jms.demo.configure;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.JMSException;

/**
 * @author switch
 * @since 2021/4/26
 */
@Configuration
public class ActiveMQConfiguration {
    private final ActiveMQProperties activeMQProperties;

    public ActiveMQConfiguration(
            ActiveMQProperties activeMQProperties
    ) {
        this.activeMQProperties = activeMQProperties;
    }


    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        return new ActiveMQConnectionFactory(activeMQProperties.getBrokerUrl());
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public ActiveMQConnection activeMQConnection() throws JMSException {
        return (ActiveMQConnection) activeMQConnectionFactory().createConnection();
    }

}
