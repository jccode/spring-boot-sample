package com.github.jccode.springbootextendtestmatcher.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.jccode.springbootsample.core.data.rest.RestResult;
import com.github.jccode.springbootsample.core.utils.JsonUtil;
import com.google.common.collect.ImmutableMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Map;

@RestController
public class HelloController {

    @RequestMapping("/ping")
    public RestResult<String> extract() throws JsonProcessingException {
        Map<String, String> ret = ImmutableMap.<String, String>builder().put("result", "pong").build();
        return RestResult.success(base64(JsonUtil.toJson(ret)));
    }

    private String base64(String s) {
        return Base64.getEncoder().encodeToString(s.getBytes());
    }

}
