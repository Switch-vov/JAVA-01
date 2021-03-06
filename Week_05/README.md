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


SQL：
```sql
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `gender` tinyint(4) NOT NULL DEFAULT '1' COMMENT '性别:1:男;2:女',
  `age` int(11) NOT NULL DEFAULT '0' COMMENT '年龄',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

1. 使用 JDBC 原生接口，实现数据库的增删改查操作。

项目地址：[jdbc-demo](exercise/jdbc-demo)

关键类：

```java
package com.switchvov.jdbc.demo;

import com.switchvov.jdbc.demo.domain.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

/**
 * @author switch
 * @since 2021/4/28
 */
@Slf4j
public class UserOperation implements Closeable {
    private final Connection conn;

    public UserOperation() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "root");
    }

    @SneakyThrows
    public User getById(Long id) {
        User user = null;
        try (ResultSet resultSet = conn.createStatement().executeQuery("select * from user where id = " + id)) {
            if (resultSet.next()) {
                user = new User()
                        .setId(resultSet.getLong("id"))
                        .setName(resultSet.getString("name"))
                        .setGender(resultSet.getInt("gender"))
                        .setAge(resultSet.getInt("age"));
            }
        }
        return user;
    }

    @SneakyThrows
    public boolean save(User user) {
        Objects.requireNonNull(user);
        try (Statement statement = conn.createStatement()) {
            return statement.execute("insert into user(name, gender, age) values('" + user.getName()
                    + "'," + user.getGender() + "," + user.getAge() + ")");
        }
    }

    @SneakyThrows
    public boolean deleteById(Long id) {
        try (Statement statement = conn.createStatement()) {
            return statement.executeUpdate("delete from user where id = " + id) == 1;
        }
    }

    @SneakyThrows
    public boolean updateById(User user) {
        Objects.requireNonNull(user);
        try (Statement statement = conn.createStatement()) {
            return statement.executeUpdate("update user set name='" + user.getName()
                    + "', gender=" + user.getGender() + ", age=" + user.getAge()
                    + " where id=" + user.getId()) == 1;
        }
    }

    @SneakyThrows
    @Override
    public void close() {
        if (Objects.nonNull(conn)) {
            conn.close();
        }
    }
}
```

2. 使用事务，PrepareStatement 方式，批处理方式，改进上述操作。

项目地址：[jdbc-demo](exercise/jdbc-demo)

关键类：

```java
package com.switchvov.jdbc.demo;

import com.switchvov.jdbc.demo.domain.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Objects;

/**
 * @author switch
 * @since 2021/4/28
 */
@Slf4j
public class UserPrepareOperation implements Closeable {
    private final Connection conn;

    public UserPrepareOperation() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "root");
    }

    @SneakyThrows
    public User getById(Long id) {
        User user = null;
        try (PreparedStatement ps = conn.prepareStatement("select * from user where id = ?")) {
            ps.setLong(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    user = new User()
                            .setId(resultSet.getLong("id"))
                            .setName(resultSet.getString("name"))
                            .setGender(resultSet.getInt("gender"))
                            .setAge(resultSet.getInt("age"));
                }
            }
        }
        return user;
    }

    @SneakyThrows
    public boolean save(User user) {
        Objects.requireNonNull(user);
        String sql = "insert into user(name, gender, age) values(?,?,?)";
        if (Objects.nonNull(user.getId())) {
            sql = "insert into user(name, gender, age, id) values(?,?,?,?)";
        }
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            if (Objects.nonNull(user.getId())) {
                ps.setLong(4, user.getId());
            }
            ps.setString(1, user.getName());
            ps.setInt(2, user.getGender());
            ps.setInt(3, user.getAge());
            return ps.execute();
        }
    }

    @SneakyThrows
    public boolean deleteById(Long id) {
        try (PreparedStatement ps = conn.prepareStatement("delete from user where id = ?")) {
            ps.setLong(1, id);
            return ps.executeUpdate() == 1;
        }
    }

    @SneakyThrows
    public boolean updateById(User user) {
        Objects.requireNonNull(user);
        try (PreparedStatement ps = conn.prepareStatement("update user set name=?,gender=?,age=? where id=?")) {
            ps.setString(1, user.getName());
            ps.setInt(2, user.getGender());
            ps.setInt(3, user.getAge());
            ps.setLong(4, user.getId());
            return ps.executeUpdate() == 1;
        }
    }

    @SneakyThrows
    public boolean saveBatch(Collection<User> users) {
        if (CollectionUtils.isEmpty(users)) {
            return false;
        }
        try {
            conn.setAutoCommit(false);
            for (User user : users) {
                save(user);
            }
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            conn.setAutoCommit(true);
        }
        return true;
    }

    @SneakyThrows
    public boolean saveBatch2(Collection<User> users) {
        if (CollectionUtils.isEmpty(users)) {
            return false;
        }
        try {
            conn.setAutoCommit(false);
            String sql = "insert into user(name, gender, age) values(?,?,?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (User user : users) {
                    ps.setString(1, user.getName());
                    ps.setInt(2, user.getGender());
                    ps.setInt(3, user.getAge());
                    ps.addBatch();
                }
                ps.executeBatch();
                conn.commit();
            }
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            conn.setAutoCommit(true);
        }
        return true;
    }

    @SneakyThrows
    @Override
    public void close() {
        if (Objects.nonNull(conn)) {
            conn.close();
        }
    }
}
```

3. 配置 Hikari 连接池，改进上述操作。提交代码到 Github。

项目地址：[hikari-demo](exercise/hikari-demo)

关键类：

```java
package com.switchvov.hikari.demo.domain;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Objects;

/**
 * @author switch
 * @since 2021/4/28
 */
public class UserHikariOperation {
    private final DataSource dataSource;

    public UserHikariOperation() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=utf8");
        config.setUsername("root");
        config.setPassword("root");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config);
    }

    @SneakyThrows
    public User getById(Long id) {
        User user = null;
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement("select * from user where id = ?")) {
            ps.setLong(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    user = new User()
                            .setId(resultSet.getLong("id"))
                            .setName(resultSet.getString("name"))
                            .setGender(resultSet.getInt("gender"))
                            .setAge(resultSet.getInt("age"));
                }
            }
        }
        connection.close();
        return user;
    }

    @SneakyThrows
    public boolean save(User user) {
        Objects.requireNonNull(user);
        String sql = "insert into user(name, gender, age) values(?,?,?)";
        if (Objects.nonNull(user.getId())) {
            sql = "insert into user(name, gender, age, id) values(?,?,?,?)";
        }
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            if (Objects.nonNull(user.getId())) {
                ps.setLong(4, user.getId());
            }
            ps.setString(1, user.getName());
            ps.setInt(2, user.getGender());
            ps.setInt(3, user.getAge());
            boolean execute = ps.execute();
            connection.close();
            return execute;
        }
    }

    @SneakyThrows
    public boolean deleteById(Long id) {
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement("delete from user where id = ?")) {
            ps.setLong(1, id);
            boolean deleted = ps.executeUpdate() == 1;
            connection.close();
            return deleted;
        }
    }

    @SneakyThrows
    public boolean updateById(User user) {
        Objects.requireNonNull(user);
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement("update user set name=?,gender=?,age=? where id=?")) {
            ps.setString(1, user.getName());
            ps.setInt(2, user.getGender());
            ps.setInt(3, user.getAge());
            ps.setLong(4, user.getId());
            boolean updated = ps.executeUpdate() == 1;
            connection.close();
            return updated;
        }
    }

    @SneakyThrows
    public boolean saveBatch(Collection<User> users) {
        if (CollectionUtils.isEmpty(users)) {
            return false;
        }
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            for (User user : users) {
                save(user);
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
            connection.close();
        }
        return true;
    }

    @SneakyThrows
    public boolean saveBatch2(Collection<User> users) {
        if (CollectionUtils.isEmpty(users)) {
            return false;
        }
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "insert into user(name, gender, age) values(?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                for (User user : users) {
                    ps.setString(1, user.getName());
                    ps.setInt(2, user.getGender());
                    ps.setInt(3, user.getAge());
                    ps.addBatch();
                }
                ps.executeBatch();
                connection.commit();
            }
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
            connection.close();
        }
        return true;
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
```

**附加题（可以后面上完数据库的课再考虑做）：**

**(挑战)** 基于 AOP 和自定义注解，实现 @MyCache(60) 对于指定方法返回值缓存 60 秒。

**(挑战)** 自定义实现一个数据库连接池，并整合 Hibernate/Mybatis/Spring/SpringBoot。

**(挑战)** 基于 MyBatis 实现一个简单的分库分表 + 读写分离 + 分布式 ID 生成方案。