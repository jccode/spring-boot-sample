package com.github.jccode.springbootkafkademo.jsondemo22

import com.github.jccode.springbootkafkademo.jsondemo22.event.EventProducer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.{CommandLineRunner, SpringApplication}
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.{ConsumerFactory, KafkaTemplate, ProducerFactory}
import org.springframework.kafka.support.converter.StringJsonMessageConverter

@SpringBootApplication
class Application {

  @Bean
  def kafkaTemplate(producerFactory: ProducerFactory[String, String]): KafkaTemplate[String, String] = {
    val template = new KafkaTemplate[String, String](producerFactory)
    template.setMessageConverter(new StringJsonMessageConverter())
    template.setDefaultTopic("SPRING_MESSAGE_TOPIC")
    template
  }

  @Bean
  def jsonKafkaListenerContainerFactory(consumerFactory: ConsumerFactory[String, String]) = {
    val factory = new ConcurrentKafkaListenerContainerFactory[String, String]
    factory.setConsumerFactory(consumerFactory)
    factory.setMessageConverter(new StringJsonMessageConverter())
    factory
  }

  @Autowired
  val producer: EventProducer = null

  def commandLineRunner() = new CommandLineRunner {
    override def run(strings: String*): Unit = {
      producer.produceEvents1()
      producer.produceEvents2()
    }
  }
}

object Application {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[Application], args: _*)
  }
}
