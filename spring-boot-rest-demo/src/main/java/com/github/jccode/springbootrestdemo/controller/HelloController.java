package com.github.jccode.springbootrestdemo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jccode.springbootrestdemo.common.data.RestResult;
import com.github.jccode.springbootrestdemo.common.data.Success;
import org.assertj.core.util.Maps;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Map;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/ping")
    public RestResult<Map<String, String>> extract() throws JsonProcessingException {
        Map<String, String> ret = Maps.<String, String>newHashMap("result", "pong");
        String s = toJson(ret);
        return new Success<>(base64(s));
    }

    private String base64(String s) {
        return Base64.getEncoder().encodeToString(s.getBytes());
    }


    private String toJson(Map<String, String> ret) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(ret);
    }
}
