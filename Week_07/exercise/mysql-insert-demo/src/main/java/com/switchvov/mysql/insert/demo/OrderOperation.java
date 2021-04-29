package com.switchvov.mysql.insert.demo;

import com.google.common.collect.Lists;
import com.switchvov.mysql.insert.demo.domain.Order;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author switch
 * @since 2021/4/28
 */
@Slf4j
public class OrderOperation implements Closeable {
    private final Connection conn;
    private List<Order> orders;

    @SneakyThrows
    public OrderOperation() {
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
        // 实际时间需要*100
        this.orders = Lists.partition(orders, 10000).get(0);
    }

    @SneakyThrows
    public boolean save() {
        Objects.requireNonNull(orders);
        if (orders.isEmpty()) {
            return false;
        }
        for (Order order : orders) {
            try (Statement statement = conn.createStatement()) {
                statement.execute("insert into order_statement(order_id, user_id, sku_number, total) values('"
                        + order.getOrderId() + "'," + order.getUserId() + ",'" + order.getSkuNumber() + "'," + order.getTotal() + ")");
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
