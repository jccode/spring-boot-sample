package com.github.jccode.springcloud.loadbalancingfeign.client

import org.springframework.stereotype.Component

@Component
class SayHelloClientFallback extends SayHelloClient {
  override def greet(): String = "fallback message"
}
