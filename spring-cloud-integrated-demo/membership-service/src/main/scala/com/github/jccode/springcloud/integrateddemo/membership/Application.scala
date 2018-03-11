package com.github.jccode.springcloud.integrateddemo.membership

import com.github.jccode.springcloud.integrateddemo.common.config.MicroServiceConfig
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(Array(classOf[MicroServiceConfig]))
class Application {

}

object Application {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[Application], args: _*)
  }
}
