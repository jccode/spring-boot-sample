package com.github.jccode.springcloud.busdemo.client2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HomeController
 *
 * @author 01372461
 */
@RefreshScope
@RestController
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Value("${app.foo: default foo}")
    private String foo;

    @GetMapping("/echo")
    public String echo() {
        log.info("foo: " + foo);
        return foo;
    }

}
