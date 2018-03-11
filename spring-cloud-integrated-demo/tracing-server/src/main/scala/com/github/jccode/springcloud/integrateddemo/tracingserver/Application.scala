package com.github.jccode.springcloud.integrateddemo.tracingserver

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import zipkin.server.EnableZipkinServer

@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer
class Application {

}

object Application {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[Application], args: _*)
  }
}
