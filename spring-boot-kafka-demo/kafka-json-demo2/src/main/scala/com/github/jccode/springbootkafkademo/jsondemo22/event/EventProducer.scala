package com.github.jccode.springbootkafkademo.jsondemo22.event

import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Component
import org.springframework.util.concurrent.ListenableFuture

import scala.collection.mutable.ListBuffer

@Component
class EventProducer {

  @Autowired
  private val publisher: Publisher = null

  private val log: Logger = LoggerFactory.getLogger(classOf[EventProducer])

  def produceEvents1() = {
    for (i <- 1 to 10) {
      var tokens = ListBuffer[String]()
      for (j <- 1 to i) {
        tokens += j.toString
      }
      val tokenEvent: Event[ListBuffer[String]] = Event("tokens", tokens)
      val value: ListenableFuture[SendResult[String, _]] = publisher.send(Constant.EventTopic, i.toString, tokenEvent)

    }
  }

}
