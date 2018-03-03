package com.github.jccode.springbootrestintegrateddemo

import com.github.jccode.springbootrestintegrateddemo.service.UserService
import org.mybatis.spring.annotation.MapperScan
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.{CommandLineRunner, SpringApplication}
import org.springframework.context.annotation.Bean

@SpringBootApplication
@MapperScan(Array("com.github.jccode.springbootrestintegrateddemo.repo"))
class Application {

  @Autowired
  val userService: UserService = null

  @Bean
  def commandLineRunner(): CommandLineRunner = new CommandLineRunner {
    override def run(args: String*): Unit = {
      val user = userService.find(1)
      println(user)
    }
  }
}


object Application {

  def main(args: Array[String]): Unit = {
    SpringApplication run classOf[Application]
  }

}

