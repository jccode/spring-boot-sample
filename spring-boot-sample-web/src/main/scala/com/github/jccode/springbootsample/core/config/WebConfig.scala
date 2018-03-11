package com.github.jccode.springbootsample.core.config

import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter

@Configuration
@ComponentScan(Array("com.github.jccode.springbootsample.core.web"))
class WebConfig {

  @Bean
  def mappingJackson2HttpMessageConverter(): MappingJackson2HttpMessageConverter = {
    import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
    import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
    val jsonConverter = new MappingJackson2HttpMessageConverter
    val objectMapper = new ObjectMapper with ScalaObjectMapper
    objectMapper.registerModule(DefaultScalaModule)
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    jsonConverter.setObjectMapper(objectMapper)
    jsonConverter
  }

}
