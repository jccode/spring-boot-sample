package com.github.jccode.springsecuritydemo.zuuldemo.backend2.controller;

import com.github.jccode.springsecuritydemo.zuuldemo.backend2.client.BackendClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * HomeController
 *
 * @author 01372461
 */
@RestController
@RequestMapping("/app")
public class HomeController {

    @Autowired
    private BackendClient client;

    @GetMapping("/me")
    public Authentication me(Authentication auth) {
        return auth;
    }

    @GetMapping("/book-list")
    public List<String> bookList() {
        return client.bookList();
    }
}
