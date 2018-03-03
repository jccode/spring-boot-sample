package com.github.jccode.springbootrestintegrateddemo

import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter


@SpringBootApplication
@MapperScan(Array("com.github.jccode.springbootrestintegrateddemo.repo"))
class Application {

  @Bean
  def mappingJackson2HttpMessageConverter(): MappingJackson2HttpMessageConverter = {
    import com.fasterxml.jackson.databind.DeserializationFeature
    import com.fasterxml.jackson.databind.ObjectMapper
    import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
    val jsonConverter = new MappingJackson2HttpMessageConverter
    val objectMapper = new ObjectMapper with ScalaObjectMapper
    objectMapper.registerModule(DefaultScalaModule)
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    jsonConverter.setObjectMapper(objectMapper)
    jsonConverter
  }

}

object Application {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[Application], args: _*)
  }
}

