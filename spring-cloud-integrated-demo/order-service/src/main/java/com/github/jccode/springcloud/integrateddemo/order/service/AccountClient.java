package com.github.jccode.springcloud.integrateddemo.order.service;

import com.github.jccode.springbootsample.core.data.rest.RestResult;
import com.github.jccode.springcloud.integrateddemo.account.api.AccountAPI;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

import static com.github.jccode.springbootsample.core.utils.CommonUtil.fail;

@FeignClient(value = "account-service", fallback = AccountAPIFallback.class)
public interface AccountClient extends AccountAPI {
}

@Component
class AccountAPIFallback implements AccountClient {

    @Override
    public RestResult<Integer> getBalance(Integer userId) {
        System.out.println("get balance fallback 111. ");
        return fail(0);
    }
}