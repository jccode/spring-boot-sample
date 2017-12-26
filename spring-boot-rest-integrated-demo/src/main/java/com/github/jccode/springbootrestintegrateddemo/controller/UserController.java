package com.github.jccode.springbootrestintegrateddemo.controller;

import com.github.jccode.springbootrestintegrateddemo.model.User;
import com.github.jccode.springbootrestintegrateddemo.service.UserService;
import com.github.jccode.springbootsample.core.data.rest.RestResult;
import com.github.jccode.springbootsample.core.data.rest.Success;
import com.github.jccode.springbootsample.core.exception.RestException;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{name}")
    public RestResult find(@PathVariable String name) {
        Preconditions.checkNotNull(name);
        List<User> users = userService.findByName(name);
        if (users == null || users.size() == 0) {
            throw new RestException("user not exist.");
        }
        else if (users.size() > 1) {
            throw new RestException("more than one user have the same name.");
        }
        return new Success<>(users.get(0));
    }
}
