package com.github.jccode.springcloud.servicediscovery

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@EnableEurekaServer
@SpringBootApplication
class EurekaServiceApplication {

}

object EurekaServiceApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[EurekaServiceApplication], args:_*)
  }
}


