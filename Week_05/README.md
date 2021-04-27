# 第五周

**周三作业：**

**1.（选做）** 使 Java 里的动态代理，实现一个简单的 AOP。

**2.（必做）** 写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）, 提交到 GitHub。

项目地址：[spring-bean-demo](exercise/spring-bean-demo)

基于XML
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
 	http://www.springframework.org/schema/beans/spring-beans-4.3.xsd">

    <!--1.使用构造注入方式装配User实例 -->
    <bean id="user" class="com.switchvov.spring.bean.demo.domain.User" primary="true">
        <constructor-arg index="0" value="1"/>
        <constructor-arg index="1" value="switch"/>
    </bean>

    <!--2.使用设值注入方式装配User实例 -->
    <bean id="userS" class="com.switchvov.spring.bean.demo.domain.User">
        <property name="id" value="2"/>
        <property name="name" value="s"/>
    </bean>

    <bean id="xmlUserService" class="com.switchvov.spring.bean.demo.service.UserService">
        <property name="user" ref="userS"/>
    </bean>

    <!--3.使用自动装配-->
    <bean id="xml2UserService" class="com.switchvov.spring.bean.demo.service.UserService" autowire="byName"/>
</beans>
```

基于注解

```java
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
```

```java
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
```


**3.（选做）** 实现一个 Spring XML 自定义配置，配置一组 Bean，例如：Student/Klass/School。

**4.（选做，会添加到高手附加题）**

- **4.1 （挑战）** 讲网关的 frontend/backend/filter/router 线程池都改造成 Spring 配置方式；
- **4.2 （挑战）** 基于 AOP 改造 Netty 网关，filter 和 router 使用 AOP 方式实现；
- **4.3 （中级挑战）** 基于前述改造，将网关请求前后端分离，中级使用 JMS 传递消息；
- **4.4 （中级挑战）** 尝试使用 ByteBuddy 实现一个简单的基于类的 AOP；
- **4.5 （超级挑战）** 尝试使用 ByteBuddy 与 Instrument 实现一个简单 JavaAgent 实现无侵入下的 AOP。

**周日作业：**

**1.（选做）** 总结一下，单例的各种写法，比较它们的优劣。

**2.（选做）** maven/spring 的 profile 机制，都有什么用法？

**3.（选做）** 总结 Hibernate 与 MyBatis 的各方面异同点。

**4.（必做）** 给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。

项目地址：[spring-bean-demo-starter](exercise/spring-boot-demo-starter)

关键类：

```java
package com.switchvov.springboot.demo.starter.autoconfigure;

import com.switchvov.springboot.demo.starter.domain.ISchool;
import com.switchvov.springboot.demo.starter.domain.Klass;
import com.switchvov.springboot.demo.starter.domain.School;
import com.switchvov.springboot.demo.starter.domain.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author switch
 * @since 2021/4/27
 */
@Configuration
public class SchoolAutoConfiguration {
    @Bean
    public ISchool school() {
        return new School();
    }

    @Bean
    public Klass klass() {
        return new Klass();
    }

    @Bean("student100")
    public Student student100() {
        return new Student(1, "switch");
    }

    @Bean
    public Student student() {
        return new Student(2, "s");
    }
}
```

```properties
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.switchvov.springboot.demo.starter.autoconfigure.SchoolAutoConfiguration
```


项目地址：[spring-bean-demo-starter-demo](exercise/spring-boot-demo-starter-demo)

关键类：

```java
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
```

**5.（选做）** 学习 MyBatis-generator 的用法和原理，学会自定义 TypeHandler 处理复杂类型。

**6.（必做）** 研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：

1. 使用 JDBC 原生接口，实现数据库的增删改查操作。
2. 使用事务，PrepareStatement 方式，批处理方式，改进上述操作。
3. 配置 Hikari 连接池，改进上述操作。提交代码到 Github。

**附加题（可以后面上完数据库的课再考虑做）：**

**(挑战)** 基于 AOP 和自定义注解，实现 @MyCache(60) 对于指定方法返回值缓存 60 秒。

**(挑战)** 自定义实现一个数据库连接池，并整合 Hibernate/Mybatis/Spring/SpringBoot。

**(挑战)** 基于 MyBatis 实现一个简单的分库分表 + 读写分离 + 分布式 ID 生成方案。