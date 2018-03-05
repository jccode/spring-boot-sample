package com.github.jccode.springbootkafkademo.jsondemo12.event

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class EventConsumer {

  private val logger: Logger = LoggerFactory.getLogger(classOf[EventConsumer])

  @KafkaListener(topics = Array(Constant.EventTopic))
  def listenEvents(record: ConsumerRecord[String, Event]): Unit = {
    val value = record.value()
    logger.info(s"Receive event: ${value.eventType}, ${value.payload}")
  }
}
