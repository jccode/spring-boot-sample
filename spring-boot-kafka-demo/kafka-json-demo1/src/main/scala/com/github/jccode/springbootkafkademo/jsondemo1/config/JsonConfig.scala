package com.github.jccode.springbootkafkademo.jsondemo1.config

import com.github.jccode.springbootkafkademo.jsondemo1.event.Event
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core._
import org.springframework.kafka.support.serializer.{JsonDeserializer, JsonSerializer}

/**
  * JsonConfig
  *
  * @author 01372461
  */
class JsonConfig {

  @Autowired
  private val kafkaProperties: KafkaProperties = null

  @Bean
  def producerFactory: ProducerFactory[String, Event] = {
    val props = kafkaProperties.buildProducerProperties
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[JsonSerializer[Event]])
    new DefaultKafkaProducerFactory[String, Event](props)
  }

  @Bean
  def kafkaTemplate: KafkaTemplate[String, Event] = new KafkaTemplate[String, Event](producerFactory)

  @Bean
  def consumerFactory: ConsumerFactory[String, Event] = {
    val props = kafkaProperties.buildConsumerProperties
    val jsonDeserializer = new JsonDeserializer(classOf[Event])
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, jsonDeserializer)
    new DefaultKafkaConsumerFactory[String, Event](props, new StringDeserializer, jsonDeserializer)
  }

  @Bean def kafkaListenerContainerFactory: ConcurrentKafkaListenerContainerFactory[String, Event] = {
    val factory = new ConcurrentKafkaListenerContainerFactory[String, Event]
    factory.setConsumerFactory(consumerFactory)
    factory
  }
}
