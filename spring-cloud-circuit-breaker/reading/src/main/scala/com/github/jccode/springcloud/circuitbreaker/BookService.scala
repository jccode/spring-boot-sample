package com.github.jccode.springcloud.circuitbreaker

import java.net.URI

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class BookService(private val restTemplate: RestTemplate) {

  @HystrixCommand(fallbackMethod = "reliable")
  def readingList(): String = restTemplate.getForObject(URI.create("http://localhost:7090/recommended"), classOf[String])

  def reliable(): String = "Cloud Native Java (O'Reilly)"

}
