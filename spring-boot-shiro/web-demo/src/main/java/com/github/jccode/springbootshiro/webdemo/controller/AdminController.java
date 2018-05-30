package com.github.jccode.springbootshiro.webdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String dashboard() {
        return "admin-dashboard";
    }

    @RequestMapping("/sth")
    public String sth() {
        return "admin-sth";
    }

}
