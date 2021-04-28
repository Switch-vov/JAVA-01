package com.switchvov.jdbc.demo;

import com.switchvov.jdbc.demo.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * @author switch
 * @since 2021/4/28
 */
@Slf4j
public class UserPrepareOperationTest {
    private UserPrepareOperation userPrepareOperation;

    @Before
    public void setUp() throws Exception {
        userPrepareOperation = new UserPrepareOperation();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getById() {
        log.info("user:{}", userPrepareOperation.getById(1L));
        log.info("user:{}", userPrepareOperation.getById(100000L));
    }

    @Test
    public void save() {
        User user = new User().setName("w").setGender(1).setAge(20);
        log.info("user:save:{}", userPrepareOperation.save(user));
        User user2 = new User().setName("i").setGender(1).setAge(100).setId(1000L);
        log.info("user:save:{}", userPrepareOperation.save(user2));
        User selectUser = userPrepareOperation.getById(1000L);
        log.info("user:select:user:{}", selectUser);
        log.info("user:delete:{}", userPrepareOperation.deleteById(1000L));
    }

    @Test
    public void deleteById() {
        log.info("user:delete:{}", userPrepareOperation.deleteById(3L));
    }

    @Test
    public void updateById() {
        User user = userPrepareOperation.getById(1L);
        log.info("user:update:before:{}", user);
        user.setAge(200);
        log.info("user:update:{}", userPrepareOperation.updateById(user));
        log.info("user:update:after:{}", userPrepareOperation.getById(1L));
    }

    @Test
    public void saveBatch() {
        List<User> users = IntStream.range(0, 100)
                .mapToObj(index -> new User().setName("s" + index).setGender(1).setAge(index))
                .collect(Collectors.toList());
        userPrepareOperation.saveBatch(users);
    }

    @Test
    public void saveBatch2() {
        List<User> users = IntStream.range(0, 100)
                .mapToObj(index -> new User().setName("s" + index).setGender(1).setAge(index))
                .collect(Collectors.toList());
        userPrepareOperation.saveBatch2(users);
    }
}