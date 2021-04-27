package com.switchvov.spring.bean.demo.configuration;

import com.switchvov.spring.bean.demo.domain.User;
import com.switchvov.spring.bean.demo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author switch
 * @since 2021/4/27
 */
@Configuration
public class UserConfiguration {
    @Bean("beanUserService")
    public UserService beanUserService() {
        return new UserService(new User(3L, "sw"));
    }
}
