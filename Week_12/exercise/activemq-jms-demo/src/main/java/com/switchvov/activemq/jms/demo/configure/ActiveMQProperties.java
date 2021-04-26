package com.switchvov.activemq.jms.demo.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author switch
 * @since 2021/4/26
 */
@Configuration
@ConfigurationProperties(prefix = "com.switchvov.activemq")
@Data
public class ActiveMQProperties {
    private String brokerUrl;
}
