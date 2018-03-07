package com.github.jccode.springbootkafkademo.jsondemo22.event

import java.util

import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Component
import org.springframework.util.concurrent.{FailureCallback, ListenableFuture, SuccessCallback}

import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer

@Component
class EventProducer(@Autowired private val publisher: Publisher) {

  private val log: Logger = LoggerFactory.getLogger(classOf[EventProducer])

  def produceEvents1() = {
    for (i <- 1 to 10) {
      var tokens = ListBuffer[String]()
      for (j <- 1 to i) {
        tokens += j.toString
      }
      val tokenEvent: Event[util.List[String]] = Event("tokens", tokens.asJava)
      val value: ListenableFuture[SendResult[String, _]] = publisher.send(Constant.EventTopic, i.toString, tokenEvent)
      val successCallback: SuccessCallback[SendResult[String, _]] = (r: SendResult[String, _]) => {
        log.info(s"Producer send success. ${r.getProducerRecord} | ${r.getRecordMetadata}")
      }
      val failureCallback: FailureCallback = (e: Throwable) => {
        log.info(s"Producer send failed. ${e.getMessage}")
      }
      value.addCallback(successCallback, failureCallback)
    }
  }

  def produceEvents2() = {
    for (i <- 1 to 10) {
      val passwords = Map(s"user_$i" -> s"pass${(97 + i).toChar}")
      val passEvent = Event("user_passwords", passwords.asJava)
      publisher.send(Constant.EventTopic, i.toString, passEvent)
    }
  }

}
