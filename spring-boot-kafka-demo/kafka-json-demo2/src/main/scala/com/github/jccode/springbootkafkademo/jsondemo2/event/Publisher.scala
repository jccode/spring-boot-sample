package com.github.jccode.springbootkafkademo.jsondemo2.event

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
  private val template: KafkaTemplate[String, String] = null  // 类型为 [String, String] 由spring注入(application.scala中定义).

  def send[T](message: Message[T]): ListenableFuture[SendResult[String, String]] = template.send(message)

  def send[T](topic: String, t: T): ListenableFuture[SendResult[String, String]] = {
    val message: Message[T] = MessageBuilder.withPayload(t).setHeader(KafkaHeaders.TOPIC, topic).build()
    send(message)
  }

  def send[T](topic: String, key: String, t: T): ListenableFuture[SendResult[String, String]] = {
    val message: Message[T] = MessageBuilder.withPayload(t).setHeader(KafkaHeaders.TOPIC, topic).setHeader(KafkaHeaders.MESSAGE_KEY, key).build()
    send(message)
  }

}
