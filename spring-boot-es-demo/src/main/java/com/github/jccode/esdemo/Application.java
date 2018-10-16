package com.github.jccode.esdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * SampleApp
 *
 * @author 01372461
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Autowired
    private HighLevelApiDemo highLevelApiDemo;

    @Autowired
    private LowLevelApiDemo lowLevelApiDemo;

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Hi baby !");
            highLevelApiDemo.demo();
            System.out.println("--------------------");
            lowLevelApiDemo.demo();
        };
    }
}
