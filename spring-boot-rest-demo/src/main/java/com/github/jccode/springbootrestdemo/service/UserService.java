package com.github.jccode.springbootrestdemo.service;

import com.github.jccode.springbootrestdemo.model.User;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    private static Map<String, User> userMap = ImmutableMap.<String, User>builder()
            .put("hello", new User("hello", "world", 10))
            .put("tom", new User("tom", "cat", 12))
            .build();

    public User find(String name) {
        return userMap.get(name);
    }
}
