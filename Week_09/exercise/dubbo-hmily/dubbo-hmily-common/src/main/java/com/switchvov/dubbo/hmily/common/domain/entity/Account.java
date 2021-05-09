package com.switchvov.dubbo.hmily.common.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * account, account_freeze 共用一个 entity
 *
 * @author switch
 * @since 2021/5/9
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {
    private static BigDecimal rate = BigDecimal.valueOf(7L);

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 人民币
     */
    private BigDecimal cny;

    /**
     * 美元
     */
    private BigDecimal usd;

    public BigDecimal cnyToUsd(BigDecimal cny) {
        Objects.requireNonNull(cny);
        return cny.divide(rate, 2, RoundingMode.HALF_UP);
    }

    public BigDecimal usdToCny(BigDecimal usd) {
        Objects.requireNonNull(usd);
        return usd.multiply(rate);
    }
}
