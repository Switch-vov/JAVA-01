package com.switchvov.dubbo.hmily.common.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author switch
 * @since 2021/5/9
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransferRequest implements Serializable {
    /**
     * 转账用户ID
     */
    private String fromUser;

    /**
     * 被转账用户ID
     */
    private String toUser;

    /**
     * 金额(美元)
     */
    private BigDecimal amount;
}
