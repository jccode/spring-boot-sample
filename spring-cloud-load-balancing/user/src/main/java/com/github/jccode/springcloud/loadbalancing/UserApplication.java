package com.github.jccode.springcloud.loadbalancing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RibbonClient(name = "say-hello", configuration = SayHelloConfiguration.class)
@RestController
@SpringBootApplication
public class UserApplication {

    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate rest() {
        return new RestTemplate();
    }

    @RequestMapping("/hi")
    public String hi(@RequestParam(name = "name", defaultValue = "Artaban") String name) {
        String greeting = restTemplate.getForObject("http://say-hello:8090/greeting", String.class);
        return String.format("%s, %s!", greeting, name);
    }
}
