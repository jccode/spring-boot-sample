package com.github.jccode.springbootkafkademo.jsondemo1.event;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {

    private static final Logger logger = LoggerFactory.getLogger(EventConsumer.class);

    @KafkaListener(topics = Constant.EVENT_TOPIC)
    public void listenEvents(ConsumerRecord<String, Event> record) {
        Event value = record.value();
        Object payload = value.getPayload();
        logger.info(String.format("Receive event: %s, %s", value.getType(), payload));
    }
}
