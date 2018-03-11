package com.github.jccode.springcloud.integrateddemo.common.config

import com.github.jccode.springbootsample.core.config.WebConfig
import org.mybatis.spring.annotation.MapperScan
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.feign.EnableFeignClients
import org.springframework.context.annotation.{ComponentScan, Import}

@EnableDiscoveryClient
@EnableFeignClients(Array("com.github.jccode.springcloud.integrateddemo.*.api"))
@EnableCircuitBreaker
@MapperScan(Array("com.github.jccode.springcloud.integrateddemo.**.*.repo"))
@ComponentScan(Array("com.github.jccode.springcloud.integrateddemo.*.api"))
@Import(Array(classOf[WebConfig]))
class MicroServiceConfig {

}
