package com.switchvov.mysql.insert.demo;

import com.google.common.collect.Lists;
import com.switchvov.mysql.insert.demo.domain.Order;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author switch
 * @since 2021/4/28
 */
@Slf4j
public class OrderPrepareOperation implements Closeable {
    private final Connection conn;
    private final List<Order> orders;
    private final List<Order> littleOrders;

    public OrderPrepareOperation() throws SQLException, IOException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=utf8", "root", "root");
        this.orders = new ArrayList<>(1000000);
        try (Stream<String> stream = Files.lines(Paths.get("/Users/switch/IdeaProjects/JAVA-01/Week_07/exercise/mysql-insert-demo/src/main/resources/data/orders.txt"))) {
            List<Order> fromOrders = stream.map(orderStr -> {
                String[] orderItem = orderStr.split(",");
                return new Order().setOrderId(orderItem[0]).setUserId(Long.valueOf(orderItem[1]))
                        .setSkuNumber(orderItem[2]).setTotal(new BigDecimal(orderItem[3]));
            }).collect(Collectors.toList());
            orders.addAll(fromOrders);
        }
        littleOrders = Lists.partition(orders, 10000).get(0);
    }

    /**
     * CREATE TABLE `order_prepare_statement` (
     *   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
     *   `order_id` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '订单ID',
     *   `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
     *   `sku_number` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'sku编码',
     *   `total` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
     *   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     *   `create_by` int(11) NOT NULL DEFAULT '0' COMMENT '创建人',
     *   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     *   `update_by` int(11) NOT NULL DEFAULT '0' COMMENT '更新人',
     *   PRIMARY KEY (`id`) USING BTREE,
     *   KEY `idx_order_id` (`order_id`) USING BTREE,
     *   KEY `idx_user_id` (`user_id`) USING BTREE,
     *   KEY `idx_sku_number` (`sku_number`) USING BTREE
     * ) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单表(简化)';
     * @return
     */
    @SneakyThrows
    public boolean save() {
        Objects.requireNonNull(orders);
        if (orders.isEmpty()) {
            return false;
        }
        String sql = "insert into order_prepare_statement(order_id, user_id, sku_number, total) values(?,?,?,?)";
        for (Order order : littleOrders) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, order.getOrderId());
                ps.setLong(2, order.getUserId());
                ps.setString(3, order.getSkuNumber());
                ps.setBigDecimal(4, order.getTotal());
                ps.execute();
            }
        }
        return true;
    }

    /**
     * CREATE TABLE `order_prepare_statement_batch` (
     *   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
     *   `order_id` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '订单ID',
     *   `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
     *   `sku_number` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'sku编码',
     *   `total` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
     *   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     *   `create_by` int(11) NOT NULL DEFAULT '0' COMMENT '创建人',
     *   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     *   `update_by` int(11) NOT NULL DEFAULT '0' COMMENT '更新人',
     *   PRIMARY KEY (`id`) USING BTREE,
     *   KEY `idx_order_id` (`order_id`) USING BTREE,
     *   KEY `idx_user_id` (`user_id`) USING BTREE,
     *   KEY `idx_sku_number` (`sku_number`) USING BTREE
     * ) ENGINE=InnoDB AUTO_INCREMENT=1000001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单表(简化)';
     * @return
     */
    @SneakyThrows
    public boolean saveBatch() {
        Objects.requireNonNull(orders);
        if (orders.isEmpty()) {
            return false;
        }
        String sql = "insert into order_prepare_statement_batch(order_id, user_id, sku_number, total) values(?,?,?,?)";
        List<List<Order>> orderLists = Lists.partition(orders, 10000);
        for (List<Order> orderList : orderLists) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (Order order : orderList) {
                    ps.setString(1, order.getOrderId());
                    ps.setLong(2, order.getUserId());
                    ps.setString(3, order.getSkuNumber());
                    ps.setBigDecimal(4, order.getTotal());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
        }
        return true;
    }

    /**
     * CREATE TABLE `order_prepare_statement_multiple_value` (
     *   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
     *   `order_id` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '订单ID',
     *   `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
     *   `sku_number` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'sku编码',
     *   `total` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
     *   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     *   `create_by` int(11) NOT NULL DEFAULT '0' COMMENT '创建人',
     *   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     *   `update_by` int(11) NOT NULL DEFAULT '0' COMMENT '更新人',
     *   PRIMARY KEY (`id`) USING BTREE,
     *   KEY `idx_order_id` (`order_id`) USING BTREE,
     *   KEY `idx_user_id` (`user_id`) USING BTREE,
     *   KEY `idx_sku_number` (`sku_number`) USING BTREE
     * ) ENGINE=InnoDB AUTO_INCREMENT=1000001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单表(简化)';
     * @return
     */
    @SneakyThrows
    public boolean saveMultipleValue() {
        Objects.requireNonNull(orders);
        if (orders.isEmpty()) {
            return false;
        }
        List<List<Order>> orderLists = Lists.partition(orders, 10000);
        for (List<Order> orderList : orderLists) {
            StringBuilder builder = new StringBuilder("insert into order_prepare_statement_multiple_value(order_id, user_id, sku_number, total) values");
            for (Order order : orderList) {
                builder.append("(?,?,?,?),");
            }
            builder.deleteCharAt(builder.length() - 1);
            String sql = builder.toString();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (int i = 0; i < orderList.size(); i++) {
                    ps.setString(i * 4 + 1, orderList.get(i).getOrderId());
                    ps.setLong(i * 4 + 2, orderList.get(i).getUserId());
                    ps.setString(i * 4 + 3, orderList.get(i).getSkuNumber());
                    ps.setBigDecimal(i * 4 + 4, orderList.get(i).getTotal());
                }
                ps.execute();
            }
        }
        return true;
    }

    /**
     * CREATE TABLE `order_prepare_statement_multiple_value_not_index` (
     *   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
     *   `order_id` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '订单ID',
     *   `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
     *   `sku_number` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'sku编码',
     *   `total` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
     *   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     *   `create_by` int(11) NOT NULL DEFAULT '0' COMMENT '创建人',
     *   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     *   `update_by` int(11) NOT NULL DEFAULT '0' COMMENT '更新人',
     *   PRIMARY KEY (`id`) USING BTREE
     * ) ENGINE=InnoDB AUTO_INCREMENT=1000001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单表(简化)';
     * @return
     */
    @SneakyThrows
    public boolean saveMultipleValueNotIndex() {
        Objects.requireNonNull(orders);
        if (orders.isEmpty()) {
            return false;
        }
        List<List<Order>> orderLists = Lists.partition(orders, 10000);
        for (List<Order> orderList : orderLists) {
            StringBuilder builder = new StringBuilder("insert into order_prepare_statement_multiple_value_not_index(order_id, user_id, sku_number, total) values");
            for (Order order : orderList) {
                builder.append("(?,?,?,?),");
            }
            builder.deleteCharAt(builder.length() - 1);
            String sql = builder.toString();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (int i = 0; i < orderList.size(); i++) {
                    ps.setString(i * 4 + 1, orderList.get(i).getOrderId());
                    ps.setLong(i * 4 + 2, orderList.get(i).getUserId());
                    ps.setString(i * 4 + 3, orderList.get(i).getSkuNumber());
                    ps.setBigDecimal(i * 4 + 4, orderList.get(i).getTotal());
                }
                ps.execute();
            }
        }
        return true;
    }

    /**
     * CREATE TABLE `order_prepare_statement_multiple_value_not_index_not_time` (
     *   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
     *   `order_id` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '订单ID',
     *   `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
     *   `sku_number` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'sku编码',
     *   `total` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
     *   PRIMARY KEY (`id`) USING BTREE
     * ) ENGINE=InnoDB AUTO_INCREMENT=1000001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单表(简化)';
     * @return
     */
    @SneakyThrows
    public boolean saveMultipleValueNotIndexNotTime() {
        Objects.requireNonNull(orders);
        if (orders.isEmpty()) {
            return false;
        }
        List<List<Order>> orderLists = Lists.partition(orders, 10000);
        for (List<Order> orderList : orderLists) {
            StringBuilder builder = new StringBuilder("insert into order_prepare_statement_multiple_value_not_index_not_time(order_id, user_id, sku_number, total) values");
            for (Order order : orderList) {
                builder.append("(?,?,?,?),");
            }
            builder.deleteCharAt(builder.length() - 1);
            String sql = builder.toString();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (int i = 0; i < orderList.size(); i++) {
                    ps.setString(i * 4 + 1, orderList.get(i).getOrderId());
                    ps.setLong(i * 4 + 2, orderList.get(i).getUserId());
                    ps.setString(i * 4 + 3, orderList.get(i).getSkuNumber());
                    ps.setBigDecimal(i * 4 + 4, orderList.get(i).getTotal());
                }
                ps.execute();
            }
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
