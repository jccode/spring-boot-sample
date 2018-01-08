package com.github.jccode.springcloud.integrateddemo.account.controller;

import com.github.jccode.springbootsample.core.data.rest.RestResult;
import com.github.jccode.springcloud.integrateddemo.account.api.AccountAPI;
import com.github.jccode.springcloud.integrateddemo.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

import static com.github.jccode.springbootsample.core.utils.CommonUtil.fault;
import static com.github.jccode.springbootsample.core.utils.CommonUtil.success;

@RestController
public class AccountController implements AccountAPI {

    @Autowired
    private AccountService accountService;

    @Value("${server.port}")
    private int serverPort;

    @Override
    public RestResult<Integer> getBalance(@PathVariable("id") Integer userId) {
        System.out.println("Requesting on " + serverPort + ", userId: " + userId);

        Optional<Integer> balance = accountService.getBalance(userId);
        if (!balance.isPresent()) {
            fault("user not found");
        }
        return success(balance.get());
    }
}
