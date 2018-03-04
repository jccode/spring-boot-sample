package com.github.jccode.springcloud.centralizedconfig

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.web.bind.annotation.{RequestMapping, RestController}

@SpringBootApplication
class ConfigClientApplication {

}

object ConfigClientApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[ConfigClientApplication], args: _*)
  }
}


@RestController
@RefreshScope
class MessageRestController(@Value("${message: Hello default}") message: String) {

  @RequestMapping(Array("/message"))
  def getMessage(): String = message

}

