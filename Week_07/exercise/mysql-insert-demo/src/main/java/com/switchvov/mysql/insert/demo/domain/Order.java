package com.switchvov.mysql.insert.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author switch
 * @since 2021/4/28
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String orderId;
    private Long userId;
    private String skuNumber;
    private BigDecimal total;
}
