package com.switchvov.dubbo.hmily.common.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

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
}
