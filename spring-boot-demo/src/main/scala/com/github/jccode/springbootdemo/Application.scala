package com.github.jccode.springbootdemo

import org.springframework.boot.{CommandLineRunner, SpringApplication}
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import Implicits._
import org.slf4j.LoggerFactory

@SpringBootApplication
class Application {
}

object Application {

  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[Application], args: _*)
//    SpringApplication run classOf[Application]
    System.in.read()
  }

  @Bean
  def commandLineRunner(ctx: ApplicationContext): CommandLineRunner =
    new CommandLineRunner {
      override def run(args: String*): Unit = {
        println("Let's inspect the beans provided by Spring Boot:")
        ctx.getBeanDefinitionNames.sorted.foreach(println)
      }
    }

  @Bean
  def commandLineRunner2(ctx: ApplicationContext): CommandLineRunner = (_: Seq[String]) => {
    println("You need to import the Implicits to convert a lambda function to CommandLineRunner, if you'd like to type less words.")
  }

}
