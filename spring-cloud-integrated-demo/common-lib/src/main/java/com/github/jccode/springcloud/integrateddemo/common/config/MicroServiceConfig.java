package com.github.jccode.springcloud.integrateddemo.common.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableDiscoveryClient
@EnableFeignClients("com.github.jccode.springcloud.integrateddemo.*.api")
@EnableCircuitBreaker
@MapperScan("com.github.jccode.springcloud.integrateddemo.*.repo")
@ComponentScan({"com.github.jccode.springcloud.integrateddemo.*.api"})
@Import(com.github.jccode.springbootsample.core.config.WebConfig.class)
public class MicroServiceConfig {
}
