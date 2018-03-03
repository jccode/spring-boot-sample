package com.github.jccode.springbootrestintegrateddemo

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
@MapperScan(Array("com.github.jccode.springbootrestintegrateddemo.repo"))
class Application {

}

object Application {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[Application], args: _*)
  }
}

