package com.switchvov.springboot.demo.starter.demo;

import com.switchvov.springboot.demo.starter.domain.ISchool;
import com.switchvov.springboot.demo.starter.domain.School;
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
public class SpringBootDemoTests {
    @Autowired
    private ISchool school;

    @Test
    public void testDemo() {
        school.ding();
    }
}
