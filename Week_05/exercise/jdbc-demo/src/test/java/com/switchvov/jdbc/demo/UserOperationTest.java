package com.switchvov.jdbc.demo;

import com.switchvov.jdbc.demo.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author switch
 * @since 2021/4/28
 */
@Slf4j
public class UserOperationTest {
    private UserOperation userOperation;

    @Before
    public void setUp() throws Exception {
        userOperation = new UserOperation();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getById() {
        log.info("user:{}", userOperation.getById(1L));
        log.info("user:{}", userOperation.getById(100000L));
    }

    @Test
    public void save() {
        User user = new User().setName("s").setGender(1).setAge(20);
        log.info("user:save:{}", userOperation.save(user));
    }

    @Test
    public void delete() {
        log.info("user:delete:{}", userOperation.deleteById(2L));
    }

    @Test
    public void update() {
        User user = userOperation.getById(1L);
        log.info("user:update:before:{}", user);
        user.setAge(100);
        log.info("user:update:{}", userOperation.updateById(user));
        log.info("user:update:after:{}", userOperation.getById(1L));
    }
}