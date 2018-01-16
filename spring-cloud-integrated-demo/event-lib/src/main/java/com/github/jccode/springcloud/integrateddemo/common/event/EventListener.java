package com.github.jccode.springcloud.integrateddemo.common.event;


import com.github.jccode.springbootsample.core.utils.JsonUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener {

    private static final Logger logger = LoggerFactory.getLogger(EventListener.class);

    @KafkaListener(topics = EventConst.TOPIC, containerFactory = "jsonKafkaListenerContainerFactory")
    public void listen(ConsumerRecord<String, String> cr) {
        logger.info("[R] " + cr.toString());
        Event event = JsonUtil.fromJson(cr.value(), Event.class);
        logger.info("Event: " + event.toString());
        // TODO 根据 event.type 分发

    }
}
