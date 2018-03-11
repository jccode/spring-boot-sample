package com.github.jccode.springcloud.integrateddemo.user

import com.github.jccode.springbootsample.core.config.WebConfig
import com.github.jccode.springcloud.integrateddemo.user.model.User
import com.github.jccode.springcloud.integrateddemo.user.service.UserService
import org.mybatis.spring.annotation.MapperScan
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.{CommandLineRunner, SpringApplication}
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.{Bean, Import}

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(Array("com.github.jccode.springcloud.integrateddemo.user.repo"))
@Import(Array(classOf[WebConfig]))
class Application {

  @Autowired private val userService: UserService = null

  @Bean def commandLineRunner: CommandLineRunner = (args: Array[String]) => {
    def foo(args: Array[String]) = {
      val user: User = userService.find(1)
      System.out.println(user)
      println("Hello")
    }

    foo(args)
  }
}

object Application {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[Application], args: _*)
  }
}
