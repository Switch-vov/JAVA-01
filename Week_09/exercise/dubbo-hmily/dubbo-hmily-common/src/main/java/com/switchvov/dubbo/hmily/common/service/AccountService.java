package com.switchvov.dubbo.hmily.common.service;

import com.switchvov.dubbo.hmily.common.domain.request.AccountTransferRequest;
import com.switchvov.dubbo.hmily.common.domain.response.AccountResponse;
import org.dromara.hmily.annotation.Hmily;

import java.util.List;

/**
 * 用户Service
 *
 * @author switch
 * @since 2021/5/9
 */
public interface AccountService {
    /**
     * 转账
     *
     * @param transferRequest 转账Request
     * @return 是否转账成功
     */
    @Hmily
    boolean trasfer(AccountTransferRequest transferRequest);

    /**
     * 获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    AccountResponse getByUserId(String userId);

    /**
     * 获取用户列表
     *
     * @return 用户列表
     */
    List<AccountResponse> list();
}
