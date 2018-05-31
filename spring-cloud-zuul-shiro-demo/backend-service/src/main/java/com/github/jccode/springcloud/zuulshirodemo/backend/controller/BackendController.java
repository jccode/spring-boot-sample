package com.github.jccode.springcloud.zuulshirodemo.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackendController {

    @GetMapping("/admin")
    public String admin() {
        return "Hello admin!";
    }

    @GetMapping("/user")
    public String user() {
        return "Hello user!";
    }

    @GetMapping("/guest")
    public String guest() {
        return "Hello guest!";
    }
}
