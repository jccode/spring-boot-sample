package com.github.jccode.springsecuritydemo.rbacguide.controller;

import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @GetMapping("/list")
    public List<String> bookList() {
        return Lists.newArrayList("Spring in action", "Scala in action", "Sbt in action");
    }

    @GetMapping("/reading")
    public String reading() {
        return "Scala in action";
    }
}
