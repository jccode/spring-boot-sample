package com.github.jccode.springbootrestintegrateddemo;

import com.github.jccode.springbootrestintegrateddemo.model.User;
import com.github.jccode.springbootrestintegrateddemo.repo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
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
