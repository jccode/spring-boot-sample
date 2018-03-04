package com.github.jccode.springcloud.centralizedconfig

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.config.server.EnableConfigServer


@EnableConfigServer
@SpringBootApplication
class ConfigServiceApplication {

}

object ConfigServiceApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[ConfigServiceApplication], args:_*)
  }
}
