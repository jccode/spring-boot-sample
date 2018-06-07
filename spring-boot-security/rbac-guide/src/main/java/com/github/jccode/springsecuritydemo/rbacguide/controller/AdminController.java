package com.github.jccode.springsecuritydemo.rbacguide.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AdminController
 *
 * @author 01372461
 */
@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('admin')")
public class AdminController {

    @GetMapping
    public String page() {
        return "admin page!";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin dashboard!";
    }

}
