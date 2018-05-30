package com.github.jccode.springbootshiro.webdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/info")
    public String userInfo() {
        Subject subject = SecurityUtils.getSubject();
        log.info(subject.getPrincipal().toString());
        return subject.getPrincipal().toString();
    }

}
