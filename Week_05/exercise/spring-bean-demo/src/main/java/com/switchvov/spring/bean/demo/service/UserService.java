package com.switchvov.spring.bean.demo.service;

import com.switchvov.spring.bean.demo.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author switch
 * @since 2021/4/27
 */
@Service("annotationUserService")
@Slf4j
public class UserService {
    private User user;

    public UserService() {

    }

    public UserService(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void printUser() {
        log.info("UserService:{}, User:{}", this, user);
    }
}
