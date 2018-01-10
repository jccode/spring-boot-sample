package com.github.jccode.springcloud.integrateddemo.user;

import com.github.jccode.springcloud.integrateddemo.user.model.User;
import com.github.jccode.springcloud.integrateddemo.user.model.UserCriteria;
import com.github.jccode.springcloud.integrateddemo.user.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.github.jccode.springcloud.integrateddemo.user.repo")
@Import(com.github.jccode.springbootsample.core.config.WebConfig.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private UserService userService;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            User user = userService.find(1);
            System.out.println(user);
        };
    }
}
