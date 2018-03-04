package com.github.jccode.springcloud.loadbalancingfeign

import org.slf4j.{Logger, LoggerFactory}
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.{RequestMapping, RestController}


@RestController
@SpringBootApplication
class SayHelloApplication {
  private val log: Logger = LoggerFactory.getLogger(classOf[SayHelloApplication])

  @RequestMapping(Array("/"))
  def home: String = {
    log.info("Access /")
    "Hi !"
  }

  @RequestMapping(Array("/greeting"))
  def greet: String = {
    log.info("Access /greeting")
    val greetings = List("Hi there", "Greetings", "Salutations")
    val r = scala.util.Random
    greetings(r.nextInt(greetings.size))
  }
}

object SayHelloApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[SayHelloApplication], args: _*)
  }
}
