package com.github.jccode.springbootrestintegrateddemo.controller;

import com.github.jccode.springbootrestintegrateddemo.form.UserForm;
import com.github.jccode.springbootrestintegrateddemo.model.User;
import com.github.jccode.springbootrestintegrateddemo.service.UserService;
import com.github.jccode.springbootsample.core.data.rest.Failed;
import com.github.jccode.springbootsample.core.data.rest.RestResult;
import com.github.jccode.springbootsample.core.data.rest.Success;
import com.github.jccode.springbootsample.core.exception.RestException;
import com.google.common.base.Preconditions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping("/register")
    public RestResult register(@Valid UserForm userForm, BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            List<String> errors = fieldErrors.stream().map(e -> e.getField() + " " + e.getDefaultMessage()).collect(Collectors.toList());
            return new Failed(errors);
        }
        else {
            User user = new User();
            BeanUtils.copyProperties(userForm, user);
            userService.save(user);
            return new Success(user);
        }
    }
}
