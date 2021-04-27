package com.switchvov.spring.bean.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author switch
 * @since 2021/4/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Qualifier("annotationUserService")
    @Autowired
    private UserService annotationUserService;

    @Qualifier("beanUserService")
    @Autowired
    private UserService beanUserService;

    @Qualifier("xmlUserService")
    @Autowired
    private UserService xmlUserService;

    @Qualifier("xml2UserService")
    @Autowired
    private UserService xml2UserService;

    @Test
    public void printUser() {
        annotationUserService.printUser();
        beanUserService.printUser();
        xmlUserService.printUser();
        xml2UserService.printUser();
    }
}