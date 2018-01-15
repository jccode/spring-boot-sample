package com.github.jccode.springbootkafkademo.jsondemo2.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class Publisher {

    @Autowired
    private KafkaTemplate<String, ?> template;

    public void send(Message<?> message) {
        template.send(message);
    }

    public <T> void send(String topic, T t) {
        Message<T> message = MessageBuilder.withPayload(t).setHeader(KafkaHeaders.TOPIC, topic).build();
        send(message);
    }

    public <T> void send(String topic, String key, T t) {
        Message<T> message = MessageBuilder.withPayload(t).setHeader(KafkaHeaders.TOPIC, topic).setHeader(KafkaHeaders.MESSAGE_KEY, key).build();
        send(message);
    }
}
