package com.github.jccode.springbootkafkademo.jsondemo2.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EventProducer {

    @Autowired
    private Publisher publisher;

    public void produceEvents1() {
        for (int i = 0; i < 10; i++) {
            List<String> tokens = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                tokens.add(String.valueOf(j));
            }
            Event<List<String>> tokenEvent = new Event<>("tokens", tokens);
            publisher.send(Constant.EVENT_TOPIC, String.valueOf(i), tokenEvent);
        }
    }

    public void produceEvents2() {
        for (int i = 0; i < 10; i++) {
            Map<String, String> passwords = new HashMap<>();
            passwords.put("user_"+i, "pass"+(char)(97+i));
            Event<Map<String, String>> passEvent = new Event<>("user_passwords", passwords);
            publisher.send(Constant.EVENT_TOPIC, String.valueOf(i), passEvent);
        }
    }
}
