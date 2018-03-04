package com.github.jccode.springcloud.routingfiltering

import com.github.jccode.springcloud.routingfiltering.filters.pre.SimpleFilter
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.context.annotation.Bean


@EnableZuulProxy
@SpringBootApplication
class GatewayApplication {

  @Bean def simpleFilter = new SimpleFilter
}

object GatewayApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication run classOf[GatewayApplication]
  }
}
