package com.switchvov.dubbo.hmily.provider.service;

import com.switchvov.dubbo.hmily.common.domain.request.AccountTransferRequest;
import com.switchvov.dubbo.hmily.common.domain.response.AccountResponse;
import com.switchvov.dubbo.hmily.common.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author switch
 * @since 2021/5/9
 */
@DubboService(version = "1.0.0")
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Override
    public boolean trasfer(AccountTransferRequest transferRequest) {
        log.info("request:{}", transferRequest);
        return false;
    }

    @Override
    public AccountResponse getByUserId(String userId) {
        AccountResponse response = new AccountResponse();
        response.setSource("1").setUserId(userId).setId(1L).setCny(BigDecimal.TEN).setUsd(BigDecimal.TEN);
        return response;
    }

    @Override
    public List<AccountResponse> list() {
        return Arrays.asList(getByUserId("1"), getByUserId("2"));
    }
}
