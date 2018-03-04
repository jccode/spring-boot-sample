package com.github.jccode.springcloud.servicediscovery

import scala.collection.JavaConverters._
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.ServiceInstance
import org.springframework.cloud.client.discovery.{DiscoveryClient, EnableDiscoveryClient}
import org.springframework.web.bind.annotation.{PathVariable, RequestMapping, RestController}

@EnableDiscoveryClient
@SpringBootApplication
class EurekaClientApplication {

}

object EurekaClientApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[EurekaClientApplication], args:_*)
  }
}

@RestController
class ServiceInstanceRestController @Autowired()(private val discoveryClient: DiscoveryClient) {

  @RequestMapping(Array("/service-instances/{applicationName}"))
  def serviceInstancesByApplicationName(@PathVariable applicationName: String): List[ServiceInstance] =
    discoveryClient.getInstances(applicationName).asScala.toList

}
