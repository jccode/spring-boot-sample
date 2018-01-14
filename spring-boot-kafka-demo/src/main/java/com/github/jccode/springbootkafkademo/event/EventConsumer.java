package com.github.jccode.springbootkafkademo.event;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {

    @KafkaListener(topics = Constant.EVENT_TOPIC)
    public void listenEvents(ConsumerRecord<String, Event> record) {
        Event value = record.value();
        Object payload = value.getPayload();
        System.out.println("Receive event: "+value.getType()+", "+payload);
    }
}
