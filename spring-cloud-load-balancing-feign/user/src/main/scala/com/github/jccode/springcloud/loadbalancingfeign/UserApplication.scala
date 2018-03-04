package com.github.jccode.springcloud.loadbalancingfeign

import com.github.jccode.springcloud.loadbalancingfeign.client.SayHelloClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.feign.EnableFeignClients
import org.springframework.web.bind.annotation.{RequestMapping, RequestParam, RestController}

@EnableFeignClients
@RestController
@SpringBootApplication
class UserApplication(@Autowired private val client: SayHelloClient) {

  @RequestMapping(Array("/hi"))
  def hi(@RequestParam(name = "name", defaultValue = "Artaban") name: String): String = {
    val greeting = client.greet
    s"$greeting, $name"
  }
}

object UserApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication run classOf[UserApplication]
  }
}
