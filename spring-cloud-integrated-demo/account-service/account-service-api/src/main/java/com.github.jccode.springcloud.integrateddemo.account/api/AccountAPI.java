package com.github.jccode.springcloud.integrateddemo.account.api;


import com.github.jccode.springbootsample.core.data.rest.RestResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.github.jccode.springbootsample.core.utils.CommonUtil.fail;

@FeignClient(value = "account", fallback = AccountAPIFallback.class)
public interface AccountAPI {

    @GetMapping("/users/{id}/balance")
    RestResult<Integer> getBalance(@PathVariable("id") Integer userId);

}

class AccountAPIFallback implements AccountAPI {

    @Override
    public RestResult<Integer> getBalance(Integer userId) {
        System.out.println("get balance fallback. ");
        return fail(0);
    }
}