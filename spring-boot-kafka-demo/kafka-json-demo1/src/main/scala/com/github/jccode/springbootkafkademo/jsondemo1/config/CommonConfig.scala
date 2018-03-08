package com.github.jccode.springbootkafkademo.jsondemo1.config

import java.util

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.{StringDeserializer, StringSerializer}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core._
import org.springframework.kafka.support.converter.StringJsonMessageConverter
import org.springframework.retry.support.RetryTemplate


class CommonConfig {

  @Autowired
  private val kafkaProperties: KafkaProperties = null

  @Bean
  def producerFactory: ProducerFactory[String, String] = {
    val props: util.Map[String, AnyRef] = kafkaProperties.buildProducerProperties()
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])
    new DefaultKafkaProducerFactory[String, String](props)
  }

  @Bean
  def kafkaTemplate: KafkaTemplate[String, String] = new KafkaTemplate[String, String](producerFactory)

  @Bean
  def consumerFactory: ConsumerFactory[String, String] = {
    val props = kafkaProperties.buildConsumerProperties
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer])
    new DefaultKafkaConsumerFactory[String, String](props)
  }

  @Bean
  def kafkaListenerContainerFactory: ConcurrentKafkaListenerContainerFactory[String, String] = {
    val factory = new ConcurrentKafkaListenerContainerFactory[String, String]
    factory.setConsumerFactory(consumerFactory)
    factory
  }

  @Bean
  def jsonKafkaListenerContainerFactory: ConcurrentKafkaListenerContainerFactory[String, String] = {
    val factory = new ConcurrentKafkaListenerContainerFactory[String, String]
    factory.setConsumerFactory(consumerFactory)
    factory.setMessageConverter(new StringJsonMessageConverter)
    factory
  }

  @Bean
  def retryKafkaListenerContainerFactory: ConcurrentKafkaListenerContainerFactory[String, String] = {
    val factory = new ConcurrentKafkaListenerContainerFactory[String, String]
    factory.setConsumerFactory(consumerFactory)
    factory.setRetryTemplate(new RetryTemplate)
    factory.setRecordFilterStrategy((consumerRecord: ConsumerRecord[String, String]) => consumerRecord.value == "bar")
    factory
  }

}
