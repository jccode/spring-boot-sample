package com.github.jccode.springbootrestdemo.controller;

import com.github.jccode.springbootrestdemo.common.data.RestResult;
import com.github.jccode.springbootrestdemo.common.data.Success;
import com.github.jccode.springbootrestdemo.exception.RestException;
import com.github.jccode.springbootrestdemo.model.User;
import com.github.jccode.springbootrestdemo.service.UserService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public RestResult<User> find(@PathVariable String name) {
        Preconditions.checkNotNull(name);
        User user = userService.find(name);
        if (user == null) {
            // throw exception
            throw new RestException("user not exist");
        }
        return new Success(user);
    }
}
