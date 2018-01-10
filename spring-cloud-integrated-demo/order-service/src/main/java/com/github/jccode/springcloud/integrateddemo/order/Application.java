package com.github.jccode.springcloud.integrateddemo.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.github.jccode.springcloud.integrateddemo.*.api")
@MapperScan("com.github.jccode.springcloud.integrateddemo.*.repo")
@ComponentScan({"com.github.jccode.springcloud.integrateddemo.*.api", "com.github.jccode.springcloud.integrateddemo.order"})
@Import(com.github.jccode.springbootsample.core.config.WebConfig.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
