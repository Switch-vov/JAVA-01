package com.switchvov.spring.bean.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author switch
 * @since 2021/4/27
 */
@SpringBootApplication
@ImportResource("classpath:spring-context.xml")
public class SpringBeanDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBeanDemoApplication.class, args);
    }
}
