package com.github.jccode.springbootkafkademo.jsondemo12.event

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class EventProducer(@Autowired private val template: KafkaTemplate[String, Event]) {

  def produceEvents1(): Unit = {
    for (i <- 0 to 10) {
      var tokens = List[String]()
      for (j <- 0 to i) {
        tokens = s"$j" :: tokens
      }
//      template.send(Constant.EventTopic, i+"", )
    }
  }
}