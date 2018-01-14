package com.github.jccode.springcloud.integrateddemo.account.api;


import com.github.jccode.springbootsample.core.data.rest.RestResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "account-service", fallback = AccountAPIFallback.class)
public interface AccountAPI {

    @RequestMapping(value = "/users/{id}/balance", method = RequestMethod.GET)
    RestResult<Integer> getBalance(@PathVariable("id") Integer userId);

}

