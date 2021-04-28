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
