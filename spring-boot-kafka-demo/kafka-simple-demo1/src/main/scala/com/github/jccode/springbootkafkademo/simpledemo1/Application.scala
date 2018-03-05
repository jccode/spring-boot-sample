package com.github.jccode.springbootkafkademo.simpledemo1

import java.util.concurrent.{CountDownLatch, TimeUnit}

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.{CommandLineRunner, SpringApplication}
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate

/**
  * Application
  *
  * @author 01372461
  */
@SpringBootApplication
class Application {
  private final val TOPIC = "TEST_TOPIC"
  private final val log: Logger = LoggerFactory.getLogger(classOf[Application])
  private val latch = new CountDownLatch(3)

  @Autowired val template: KafkaTemplate[String, String] = null

  @Bean
  def commandLineRunner = new CommandLineRunner {
    override def run(strings: String*): Unit = {
      template.send(TOPIC, "hello")
      template.send(TOPIC, "world")
      template.send(TOPIC, "kitty")

      latch.await(60, TimeUnit.SECONDS)
      log.info("All received!")
    }
  }

  @KafkaListener(topics = Array(TOPIC))
  def listen(cr: ConsumerRecord[_, _]): Unit = {
    log.info(cr.toString)
    latch.countDown()
  }

}

object Application {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[Application], args: _*)
  }
}
