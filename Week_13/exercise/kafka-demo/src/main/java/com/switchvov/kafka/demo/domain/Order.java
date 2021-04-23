package com.switchvov.kafka.demo.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


/**
 * @author switch
 * @since 2021/4/23
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Order {
    private Integer id;
    private BigDecimal amount;
    private Integer type;

}
