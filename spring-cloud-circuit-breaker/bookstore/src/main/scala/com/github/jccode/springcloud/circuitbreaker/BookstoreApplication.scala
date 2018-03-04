package com.github.jccode.springcloud.circuitbreaker

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.{RequestMapping, RestController}


@RestController
@SpringBootApplication
class BookstoreApplication {

  @RequestMapping(Array("/recommended"))
  def readingList = "Spring in Action (Manning), Cloud Native Java (O'Reilly), Learning Spring Boot (Packt)"

}

object BookstoreApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[BookstoreApplication], args: _*)
  }
}
