package com.github.jccode.springcloud.integrateddemo.user.api;

import com.github.jccode.springbootsample.core.data.rest.RestResult;
import com.github.jccode.springcloud.integrateddemo.user.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(value = "user-service", fallback = UserAPIFallback.class)
public interface UserAPI {

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    RestResult<User> findByName(@RequestParam("name") String name);

    @GetMapping("/users/{id}")
    RestResult<User> find(@PathVariable("id") Integer id);
}
