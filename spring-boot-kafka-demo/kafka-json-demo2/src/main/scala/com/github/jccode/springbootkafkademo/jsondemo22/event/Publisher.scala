package com.github.jccode.springbootkafkademo.jsondemo22.event

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.{KafkaHeaders, SendResult}
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import org.springframework.util.concurrent.ListenableFuture

@Component
class Publisher {

  @Autowired
  private val template: KafkaTemplate[String, _] = null

  def send(message: Message[_]): ListenableFuture[SendResult[String, _]] = template.send(message)

  def send[T](topic: String, t: T): ListenableFuture[SendResult[String, _]] = {
    val message: Message[T] = MessageBuilder.withPayload(t).setHeader(KafkaHeaders.TOPIC, topic).build()
    send(message)
  }

  def send[T](topic: String, key: String, t: T): ListenableFuture[SendResult[String, _]] = {
    val message: Message[T] = MessageBuilder.withPayload(t).setHeader(KafkaHeaders.TOPIC, topic).setHeader(KafkaHeaders.MESSAGE_KEY, key).build()
    send(message)
  }

}
