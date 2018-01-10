package com.github.jccode.springcloud.integrateddemo.user.api;

import com.github.jccode.springbootsample.core.data.rest.RestResult;
import org.springframework.stereotype.Component;

import static com.github.jccode.springbootsample.core.utils.CommonUtil.fail;

@Component
public class UserAPIFallback implements UserAPI {

    @Override
    public RestResult find(String name) {
        return fail("user service is not available");
    }
}
