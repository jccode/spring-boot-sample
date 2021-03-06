package com.github.jccode.springsecuritydemo.oauthguide.resserver.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HomeController
 *
 * @author 01372461
 */
@RestController
@RequestMapping("/app")
public class HomeController {

    @RequestMapping(value = "/admin")
    public String admin() {
        return "admin!";
    }

    @RequestMapping(value = "/user")
    public String user() {
        return "user!";
    }

    @RequestMapping(value = "/guest")
    public String guest() {
        return "guest!";
    }

    @RequestMapping("/me")
    public Authentication me(Authentication auth) {
        return auth;
    }
}
