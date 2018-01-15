package com.github.jccode.springbootkafkademo.jsondemo1;


import com.github.jccode.springbootkafkademo.jsondemo1.config.JsonConfig;
import com.github.jccode.springbootkafkademo.jsondemo1.event.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({JsonConfig.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args).close();
    }

    @Autowired
    private EventProducer producer;

    @Bean
    public CommandLineRunner eventTestRunner(ApplicationContext ctx) {
        return args -> {
            producer.produceEvents1();
            producer.produceEvents2();
        };
    }
}
