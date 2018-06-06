package com.github.jccode.springsecuritydemo.rbacguide.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/self")
    public Authentication self(Authentication authentication) {
        return authentication;
    }

}
