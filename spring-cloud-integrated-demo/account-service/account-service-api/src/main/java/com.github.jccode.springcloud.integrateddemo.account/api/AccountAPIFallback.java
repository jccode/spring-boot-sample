package com.github.jccode.springcloud.integrateddemo.account.api;

import com.github.jccode.springbootsample.core.data.rest.RestResult;
import org.springframework.stereotype.Component;

import static com.github.jccode.springbootsample.core.utils.CommonUtil.fail;

@Component
class AccountAPIFallback implements AccountAPI {

    @Override
    public RestResult<Integer> getBalance(Integer userId) {
        return fail("Get user balance fail.", 0);
    }
}