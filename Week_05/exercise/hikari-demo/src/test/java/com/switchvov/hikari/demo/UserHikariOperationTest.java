package com.switchvov.hikari.demo;

import com.switchvov.hikari.demo.domain.User;
import com.switchvov.hikari.demo.domain.UserHikariOperation;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author switch
 * @since 2021/4/28
 */
@Slf4j
public class UserHikariOperationTest {
    private UserHikariOperation userHikariOperation;

    @Before
    public void setUp() throws Exception {
        userHikariOperation = new UserHikariOperation();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getById() {
        log.info("user:{}", userHikariOperation.getById(1L));
        log.info("user:{}", userHikariOperation.getById(100000L));
    }

    @Test
    public void save() {
        User user = new User().setName("w").setGender(1).setAge(20);
        log.info("user:save:{}", userHikariOperation.save(user));
        User user2 = new User().setName("i").setGender(1).setAge(100).setId(1000L);
        log.info("user:save:{}", userHikariOperation.save(user2));
        User selectUser = userHikariOperation.getById(1000L);
        log.info("user:select:user:{}", selectUser);
        log.info("user:delete:{}", userHikariOperation.deleteById(1000L));
    }

    @Test
    public void deleteById() {
        log.info("user:delete:{}", userHikariOperation.deleteById(3L));
    }

    @Test
    public void updateById() {
        User user = userHikariOperation.getById(1L);
        log.info("user:update:before:{}", user);
        user.setAge(200);
        log.info("user:update:{}", userHikariOperation.updateById(user));
        log.info("user:update:after:{}", userHikariOperation.getById(1L));
    }

    @Test
    public void saveBatch() {
        List<User> users = IntStream.range(0, 100)
                .mapToObj(index -> new User().setName("s" + index).setGender(1).setAge(index))
                .collect(Collectors.toList());
        userHikariOperation.saveBatch(users);
    }

    @Test
    public void saveBatch2() {
        List<User> users = IntStream.range(0, 100)
                .mapToObj(index -> new User().setName("s" + index).setGender(1).setAge(index))
                .collect(Collectors.toList());
        userHikariOperation.saveBatch2(users);
    }
}