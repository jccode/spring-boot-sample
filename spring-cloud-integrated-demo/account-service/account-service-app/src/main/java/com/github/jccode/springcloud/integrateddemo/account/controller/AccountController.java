package com.github.jccode.springcloud.integrateddemo.account.controller;

import com.github.jccode.springbootsample.core.data.rest.RestResult;
import com.github.jccode.springcloud.integrateddemo.account.api.AccountAPI;
import com.github.jccode.springcloud.integrateddemo.account.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @Value("${server.port}")
    private int serverPort;

    @Override
    public RestResult<Integer> getBalance(@PathVariable("id") Integer userId) {
        log.info("Get user balance. Requesting on port: {}, userId: {}", serverPort, userId);

        Optional<Integer> balance = accountService.getBalance(userId);
        if (!balance.isPresent()) {
            fault("user not found");
        }

        return success(balance.get());
    }
}
