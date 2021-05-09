package com.switchvov.dubbo.hmily.consumer.controller;

import com.switchvov.dubbo.hmily.common.domain.request.AccountTransferRequest;
import com.switchvov.dubbo.hmily.common.domain.response.AccountResponse;
import com.switchvov.dubbo.hmily.common.service.AccountService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author switch
 * @since 2021/5/9
 */
@Controller
@RestController
@RequestMapping("/account")
public class AccountController {
    @DubboReference(version = "1.0.0", retries = 0)
    private AccountService userService;

    @GetMapping("/{userId}")
    public AccountResponse getByUserId(@PathVariable("userId") String userId) {
        return userService.getByUserId(userId);
    }

    @GetMapping
    public List<AccountResponse> list() {
        return userService.list();
    }

    @PostMapping("/{userId}/transfer")
    public Boolean transfer(@PathVariable("userId") String userId, @RequestBody AccountTransferRequest transferRequest) {
        transferRequest.setFromUser(userId);
        return userService.trasfer(transferRequest);
    }
}
