package com.github.jccode.springbootkafkademo.jsondemo2.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class EventConsumer {

    private static final Logger logger = LoggerFactory.getLogger(EventConsumer.class);

    @KafkaListener(topics = Constant.EVENT_TOPIC, containerFactory = "jsonKafkaListenerContainerFactory")
    public void listenEvents(@Payload Event<?> value, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        Object payload = value.getPayload();
        logger.info(String.format("[R] key: %s, type: %s, %s", key, value.getType(), payload));
    }

}
