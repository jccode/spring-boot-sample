package com.github.jccode.configurationpropertiesdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Service
    @EnableConfigurationProperties(MyAppConfigProperties.class)
    static class DemoService implements CommandLineRunner {

        private final MyAppConfigProperties configProperties;

        @Autowired
        public DemoService(MyAppConfigProperties configProperties) {
            this.configProperties = configProperties;
        }

        @Override
        public void run(String... strings) throws Exception {
            System.out.println("Hello");
            System.out.println(configProperties);
        }
    }
}
