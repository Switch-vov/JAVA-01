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
        conn = DriverManager.getConnection("jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=utf8", "root", "root");
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
