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
        conn = DriverManager.getConnection("jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=utf8", "root", "root");
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
