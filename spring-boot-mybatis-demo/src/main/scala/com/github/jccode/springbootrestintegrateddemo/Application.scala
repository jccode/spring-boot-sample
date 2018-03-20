package com.github.jccode.springbootrestintegrateddemo

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@MapperScan(Array("com.github.jccode.springbootrestintegrateddemo.repo"))
class Application {

//  @Autowired
//  val userService: UserService = null
//
//  @Bean
//  def commandLineRunner(): CommandLineRunner = new CommandLineRunner {
//    override def run(args: String*): Unit = {
//      val user = userService.find(1)
//      println(user)
//    }
//  }
}


object Application {

  def main(args: Array[String]): Unit = {
    SpringApplication run classOf[Application]
  }

}

