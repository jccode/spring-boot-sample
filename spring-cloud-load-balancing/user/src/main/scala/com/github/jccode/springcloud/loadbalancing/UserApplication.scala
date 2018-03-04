package com.github.jccode.springcloud.loadbalancing

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.netflix.ribbon.RibbonClient
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.{RequestMapping, RequestParam, RestController}
import org.springframework.web.client.RestTemplate


@RibbonClient(name = "say-hello", configuration = Array(classOf[SayHelloConfiguration]))
@RestController
@SpringBootApplication
class UserApplication {

  @Autowired private val restTemplate: RestTemplate = null

  @LoadBalanced
  @Bean
  def rest(): RestTemplate = new RestTemplate

  @RequestMapping(Array("hi"))
  def hi(@RequestParam(name = "name", defaultValue = "Artaban") name: String): String = {
    val greeting = restTemplate.getForObject("http://say-hello:8090/greeting", classOf[String])
    s"$greeting, $name"
  }
}

object UserApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[UserApplication], args: _*)
  }
}
