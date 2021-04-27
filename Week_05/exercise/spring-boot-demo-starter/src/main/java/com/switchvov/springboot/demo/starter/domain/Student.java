package com.switchvov.springboot.demo.starter.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Slf4j
public class Student implements Serializable, BeanNameAware, ApplicationContextAware {
    private int id;
    private String name;
    private String beanName;
    private ApplicationContext applicationContext;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void init() {
        log.info("hello...........");
    }

    public void print() {
        log.info("beanName:{}", this.beanName);
        log.info("   context.getBeanDefinitionNames() ===>> {}", String.join(",", applicationContext.getBeanDefinitionNames()));
    }
}
