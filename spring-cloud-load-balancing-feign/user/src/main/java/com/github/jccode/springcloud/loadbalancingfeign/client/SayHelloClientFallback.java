package com.github.jccode.springcloud.loadbalancingfeign.client;

import org.springframework.stereotype.Component;

@Component
public class SayHelloClientFallback implements SayHelloClient {

    @Override
    public String greet() {
        return "fallback message";
    }

}