package com.github.jccode.springboot.mdcdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Application
 *
 * Ref: https://www.baeldung.com/mdc-in-log4j-2-logback
 *
 * @author 01372461
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner transferRunner(ApplicationContext ctx) {
        return args -> {
            ExecutorService executor = Executors.newFixedThreadPool(3);
            TransactionFactory transactionFactory = new TransactionFactory();
            for (int i = 0; i < 10; i++) {
                executor.submit(new Slf4jRunnable(transactionFactory.newInstance()));
            }
            executor.shutdown();
        };
    }
}
