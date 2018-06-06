package com.github.jccode.springsecuritydemo.rbacguide.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/guest")
    public String guest() {
        return "guest";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
