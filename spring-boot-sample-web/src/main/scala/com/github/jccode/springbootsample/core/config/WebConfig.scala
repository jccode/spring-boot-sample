package com.github.jccode.springbootsample.core.config

import org.springframework.context.annotation.{ComponentScan, Configuration}

@Configuration
@ComponentScan(Array("com.github.jccode.springbootsample.core.web"))
class WebConfig {

}
