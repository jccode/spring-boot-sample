package com.github.jccode.springcloud.routingfiltering

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.{RequestMapping, RestController}

@RestController
@SpringBootApplication
class BookApplication {

  @RequestMapping(Array("available")) def available = "Spring in Action"

  @RequestMapping(Array("checked-out")) def checkedOut = "Spring Boot in Action"

}

object BookApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication run classOf[BookApplication]
  }
}
