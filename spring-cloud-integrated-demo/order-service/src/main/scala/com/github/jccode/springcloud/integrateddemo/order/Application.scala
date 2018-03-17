package com.github.jccode.springcloud.integrateddemo.order

import com.github.jccode.springcloud.integrateddemo.common.config.MicroServiceConfig
import feign.Logger
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.{Bean, Configuration, Import}

@SpringBootApplication
@Import(Array(classOf[MicroServiceConfig]))
class Application {

}

object Application {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[Application], args: _*)
  }
}


// Feign config log level
//   - Don't forget change the log level in application.yml as well.
@Configuration
class FeignCustomConfig {

  @Bean
  def feignLoggerLevel: Logger.Level = Logger.Level.FULL

}