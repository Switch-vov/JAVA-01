package com.switchvov.smq.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author switch
 * @since 2021/4/24
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private Long id;
    private Long ts;
    private String symbol;
    private Double price;
}