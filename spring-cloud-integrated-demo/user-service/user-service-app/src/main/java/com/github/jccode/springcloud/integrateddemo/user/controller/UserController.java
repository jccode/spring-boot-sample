package com.github.jccode.springcloud.integrateddemo.user.controller;

import com.github.jccode.springbootsample.core.data.rest.RestResult;
import com.github.jccode.springcloud.integrateddemo.user.api.UserAPI;
import com.github.jccode.springcloud.integrateddemo.user.model.User;
import com.github.jccode.springcloud.integrateddemo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.github.jccode.springbootsample.core.utils.CommonUtil.fail;
import static com.github.jccode.springbootsample.core.utils.CommonUtil.success;

@RestController
public class UserController implements UserAPI {

    @Autowired
    private UserService userService;

    @Override
    public RestResult<User> find(@PathVariable String name) {
        List<User> users = userService.findByName(name);
        if (users == null || users.isEmpty()) {
            return fail("user not found");
        }
        else if (users.size() > 1) {
            return fail("more than one user found");
        } else {
            return success(users.get(0));
        }
    }
}
