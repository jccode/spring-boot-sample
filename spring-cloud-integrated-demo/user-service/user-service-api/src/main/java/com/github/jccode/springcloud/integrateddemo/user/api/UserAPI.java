package com.github.jccode.springcloud.integrateddemo.user.api;

import com.github.jccode.springbootsample.core.data.rest.RestResult;
import com.github.jccode.springcloud.integrateddemo.user.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "user-service", fallback = UserAPIFallback.class)
public interface UserAPI {

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    RestResult<User> find(@PathVariable("name") String name);
}
