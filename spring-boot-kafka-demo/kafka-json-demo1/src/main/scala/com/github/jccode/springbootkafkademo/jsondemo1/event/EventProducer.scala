package com.github.jccode.springbootkafkademo.jsondemo1.event

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer

@Component
class EventProducer(@Autowired private val template: KafkaTemplate[String, Event]) {

  def produceEvents1(): Unit = {
    for (i <- 0 to 10) {
      var tokens = ListBuffer[String]()
      for (j <- 0 to i) {
        tokens += s"$j"
      }
      // 传入kafka的时候,需要转成java的集合类. scala的集合序列化,反序列化有点问题.
      template.send(Constant.EventTopic, i+"", Event("tokens", tokens.asJava))
    }
  }

  def produceEvents2(): Unit = {
    for (i <- 0 to 10) {
      val passwords = Map[String, String](s"user_$i" -> s"pass${(97+i).toChar}")
      template.send(Constant.EventTopic, i.toString, Event("user_passwords", passwords.asJava))
    }
  }
}
