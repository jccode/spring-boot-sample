package com.github.jccode.springcloud.routingfiltering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class BookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

    @RequestMapping("available")
    public String available() {
        return "Spring in Action";
    }

    @RequestMapping("checked-out")
    public String checkedOut() {
        return "Spring Boot in Action";
    }
}
