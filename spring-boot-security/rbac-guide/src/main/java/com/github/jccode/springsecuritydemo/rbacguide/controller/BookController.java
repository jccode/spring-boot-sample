package com.github.jccode.springsecuritydemo.rbacguide.controller;

import com.google.common.collect.Lists;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @PreAuthorize("hasAuthority('book:read')")
    @GetMapping("/list")
    public List<String> bookList() {
        return Lists.newArrayList("Spring in action", "Scala in action", "Sbt in action");
    }

    @PreAuthorize("hasAuthority('book:read')")
    @GetMapping("/reading")
    public String reading() {
        return "Scala in action";
    }
}
