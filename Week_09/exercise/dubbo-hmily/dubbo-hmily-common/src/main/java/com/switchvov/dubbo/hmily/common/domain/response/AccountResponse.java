package com.switchvov.dubbo.hmily.common.domain.response;

import com.switchvov.dubbo.hmily.common.domain.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author switch
 * @since 2021/5/9
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse extends Account implements Serializable {
    /**
     * 来源
     */
    private String source;
}
