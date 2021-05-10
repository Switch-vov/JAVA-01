package com.switchvov.dubbo.hmily.provider.service;

import com.switchvov.dubbo.hmily.common.domain.entity.Account;
import com.switchvov.dubbo.hmily.common.domain.request.AccountTransferRequest;
import com.switchvov.dubbo.hmily.common.domain.response.AccountResponse;
import com.switchvov.dubbo.hmily.common.service.AccountService;
import com.switchvov.dubbo.hmily.provider.mapper.hmilyaccounta.AccountAMapper;
import com.switchvov.dubbo.hmily.provider.mapper.hmilyaccountb.AccountBMapper;
import com.switchvov.dubbo.hmily.provider.mapper.hmilyaccountfreeze.AccountFreezeMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author switch
 * @since 2021/5/9
 */
@DubboService(version = "1.0.0")
@Slf4j
public class AccountServiceImpl implements AccountService {
    private static final BigDecimal RATE = BigDecimal.valueOf(7L);

    private final AccountAMapper accountAMapper;
    private final AccountBMapper accountBMapper;
    private final AccountFreezeMapper accountFreezeMapper;

    public AccountServiceImpl(
            AccountAMapper accountAMapper,
            AccountBMapper accountBMapper,
            AccountFreezeMapper accountFreezeMapper
    ) {
        this.accountAMapper = accountAMapper;
        this.accountBMapper = accountBMapper;
        this.accountFreezeMapper = accountFreezeMapper;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public boolean trasfer(AccountTransferRequest transferRequest) {
        log.info("request:{}", transferRequest);
        BigDecimal amount = transferRequest.getAmount();
        String fromUser = transferRequest.getFromUser();
        if (BigDecimal.ZERO.compareTo(amount) >= 0) {
            log.info("转账美元金额必须大于0");
        }
        int changeCount = accountAMapper.changeUsd(fromUser, amount.negate());
        if (changeCount <= 0) {
            log.info("用户:{}, 美元不足:{}", fromUser, amount);
            return false;
        }
        int freezeCount = accountFreezeMapper.freeze(fromUser, usdToCny(amount));
        if (freezeCount <= 0) {
            log.info("用户:{}, 冻结金额失败", fromUser);
            return false;
        }
//        throw new RuntimeException("abc");
        return true;
    }

    @Transactional(rollbackFor = Throwable.class)
    public boolean confirm(AccountTransferRequest transferRequest) {
        String fromUser = transferRequest.getFromUser();
        String toUser = transferRequest.getToUser();
        BigDecimal amount = transferRequest.getAmount();
        // TODO: 设计个交易编号会更好一些
        accountFreezeMapper.unfreeze(fromUser, usdToCny(amount));
        accountBMapper.changeUsd(toUser, amount);
        return true;
    }

    @Transactional(rollbackFor = Throwable.class)
    public boolean cancel(AccountTransferRequest transferRequest) {
        String fromUser = transferRequest.getFromUser();
        BigDecimal amount = transferRequest.getAmount();
        // TODO: 设计个交易编号会更好一些
        accountFreezeMapper.unfreeze(fromUser, usdToCny(amount));
        accountAMapper.changeUsd(fromUser, amount);
        return true;
    }

    @Override
    public AccountResponse getByUserId(String userId) {
        Account account = accountAMapper.getByUserId(userId);
        if (Objects.nonNull(account)) {
            return convert(account, "A");
        }
        account = accountBMapper.getByUserId(userId);
        if (Objects.nonNull(account)) {
            return convert(account, "B");
        }
        return null;
    }

    @Override
    public List<AccountResponse> list() {
        List<AccountResponse> responses = convertList(accountAMapper.list(), "A");
        responses.addAll(convertList(accountBMapper.list(), "B"));
        return responses;
    }

    private List<AccountResponse> convertList(List<Account> accounts, String source) {
        return accounts.stream()
                .map(account -> convert(account, source))
                .collect(Collectors.toList());
    }

    private AccountResponse convert(Account account, String source) {
        AccountResponse response = new AccountResponse();
        BeanUtils.copyProperties(account, response);
        response.setSource(source);
        return response;
    }

    private BigDecimal cnyToUsd(BigDecimal cny) {
        Objects.requireNonNull(cny);
        return cny.divide(RATE, 2, RoundingMode.HALF_UP);
    }

    private BigDecimal usdToCny(BigDecimal usd) {
        Objects.requireNonNull(usd);
        return usd.multiply(RATE);
    }
}
