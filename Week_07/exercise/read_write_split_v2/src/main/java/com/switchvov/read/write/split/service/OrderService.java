package com.switchvov.read.write.split.service;

import com.switchvov.read.write.split.domain.Order;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author switch
 * @since 2021/5/2
 */
@Service
@Slf4j
public class OrderService {
    private final DataSource dataSource;

    public OrderService(
            DataSource dataSource
    ) {
        this.dataSource = dataSource;
    }

    @SneakyThrows
    public boolean save(Order order) {
        try (Connection con = dataSource.getConnection()) {
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
        try (Connection con = dataSource.getConnection()) {
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
