package com.github.jccode.springbootkafkademo.simpledemo1;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args).close();
    }



    private static final String TOPIC = "TEST_TOPIC";

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    private CountDownLatch latch = new CountDownLatch(3);

    @Autowired
    private KafkaTemplate<String, String> template;

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context) {
        return args -> {
            template.send(TOPIC, "hello");
            template.send(TOPIC, "world");
            template.send(TOPIC, "kitty");

            latch.await(60, TimeUnit.SECONDS);
            logger.info("All received!");
        };
    }

    @KafkaListener(topics = TOPIC)
    public void listen(ConsumerRecord<?, ?> cr) {
        logger.info(cr.toString());
        latch.countDown();
    }
}
