package com.github.jccode.springcloud.loadbalancingfeign;

import com.github.jccode.springcloud.loadbalancingfeign.client.SayHelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableFeignClients
@RestController
@SpringBootApplication
public class UserApplication {

    @Autowired
    private SayHelloClient client;

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @RequestMapping("/hi")
    public String hi(@RequestParam(name = "name", defaultValue = "Artaban") String name) {
        String greeting = client.greet();
        return String.format("%s, %s!", greeting, name);
    }
}
