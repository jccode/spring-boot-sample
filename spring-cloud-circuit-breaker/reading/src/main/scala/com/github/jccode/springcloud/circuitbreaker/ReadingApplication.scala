package com.github.jccode.springcloud.circuitbreaker

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.{RequestMapping, RestController}
import org.springframework.web.client.RestTemplate

@EnableCircuitBreaker
@RestController
@SpringBootApplication
class ReadingApplication {

  @Autowired private val bookService: BookService = null

  @Bean def rest(builder: RestTemplateBuilder): RestTemplate = builder.build

  @RequestMapping(Array("/to-read")) def readingList: String = bookService.readingList
}

object ReadingApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[ReadingApplication], args: _*)
  }
}
