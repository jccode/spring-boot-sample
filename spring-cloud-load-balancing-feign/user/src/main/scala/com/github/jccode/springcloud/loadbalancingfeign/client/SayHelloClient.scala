package com.github.jccode.springcloud.loadbalancingfeign.client

import org.springframework.cloud.netflix.feign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping

@FeignClient(name = "say-hello", fallback = classOf[SayHelloClientFallback])
trait SayHelloClient {

  @RequestMapping(Array("/greeting"))
  def greet: String
  
}
