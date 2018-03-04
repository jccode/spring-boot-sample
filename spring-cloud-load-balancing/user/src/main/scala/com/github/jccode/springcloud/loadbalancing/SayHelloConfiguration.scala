package com.github.jccode.springcloud.loadbalancing

import com.netflix.client.config.IClientConfig
import com.netflix.loadbalancer.{AvailabilityFilteringRule, IPing, IRule, PingUrl}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean

class SayHelloConfiguration(@Autowired private val ribbonClientConfig: IClientConfig) {

  @Bean
  def ribbonPing(): IPing = new PingUrl

  @Bean
  def ribbonRule(config: IClientConfig): IRule = new AvailabilityFilteringRule

}
