package com.github.jccode.springbootkafkademo.jsondemo22.event

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class Publisher {

  @Autowired
  private val template: KafkaTemplate[String, _] = null


}
