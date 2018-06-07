package com.github.jccode.springsecuritydemo.rbacguide.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/has-role")
    public boolean hasRole(@RequestParam String role, HttpServletRequest request) {
        return request.isUserInRole(role);
    }

    @GetMapping("/has-permission")
    public boolean hasAuthority(@RequestParam String perm, Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.stream().anyMatch(x -> x.getAuthority().equals(perm));
    }
}
