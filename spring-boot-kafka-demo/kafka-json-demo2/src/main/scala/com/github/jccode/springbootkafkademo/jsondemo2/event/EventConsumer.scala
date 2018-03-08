package com.github.jccode.springbootkafkademo.jsondemo2.event

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.{Header, Payload}
import org.springframework.stereotype.Component

@Component
class EventConsumer {

  private val logger = LoggerFactory.getLogger(classOf[EventConsumer])

  @KafkaListener(topics = Array(Constant.EventTopic), containerFactory = "jsonKafkaListenerContainerFactory")
  def listenEvents(@Payload value: Event[_], @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) key: String): Unit = {
    logger.info(s"[R] key: $key, type: ${value.eventType}, ${value.payload}")
  }
}
