package com.switchvov.read.write.split.service;

import com.switchvov.read.write.split.common.context.ReadOnly;
import com.switchvov.read.write.split.domain.Order;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author switch
 * @since 2021/5/2
 */
@Service
@Slf4j
public class OrderService {
    private final HikariDataSource master;
    private final List<HikariDataSource> slaves;
    private final AbstractRoutingDataSource routingDataSource;

    public OrderService(
            @Qualifier("masterHikariDataSource") @Autowired HikariDataSource master,
            @Qualifier("slaveHikariDataSources") @Autowired List<HikariDataSource> slaves,
            @Qualifier("routingDataSource") @Autowired AbstractRoutingDataSource routingDataSource
    ) {
        this.master = master;
        this.slaves = slaves;
        this.routingDataSource = routingDataSource;
    }

    @SneakyThrows
    public boolean save(Order order) {
        return save(order, master);
    }

    @SneakyThrows
    public boolean routingSave(Order order) {
        return save(order, routingDataSource);
    }

    private boolean save(Order order, DataSource dataSource) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            log.info("Connection为:{}", con.getMetaData().getURL());
            String sql = "INSERT INTO `order`(order_id,user_id,sku_number,total) values(?,?,?,?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, order.getOrderId());
                ps.setLong(2, order.getUserId());
                ps.setString(3, order.getSkuNumber());
                ps.setBigDecimal(4, order.getTotal());
                return ps.executeUpdate() == 1;
            }
        }
    }

    @SneakyThrows
    public Order getByOrderId(String orderId) {
        int index = ThreadLocalRandom.current().nextInt(0, slaves.size());
        HikariDataSource slave = slaves.get(index);
        return getOrderById(orderId, slave);
    }

    @SneakyThrows
    @ReadOnly
    public Order routingGetByOrderId(String orderId) {
        return getOrderById(orderId, routingDataSource);
    }

    private Order getOrderById(String orderId, DataSource dataSource) throws SQLException {
        try (Connection con = dataSource.getConnection()) {
            log.info("Connection为:{}", con.getMetaData().getURL());
            String sql = "SELECT * FROM `order` WHERE order_id = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, orderId);
                try (ResultSet results = ps.executeQuery()) {
                    if (results.next()) {
                        return new Order()
                                .setOrderId(results.getString("order_id"))
                                .setUserId(results.getLong("user_id"))
                                .setSkuNumber(results.getString("sku_number"))
                                .setTotal(results.getBigDecimal("total"));
                    }
                }
            }
        }
        return null;
    }
}
