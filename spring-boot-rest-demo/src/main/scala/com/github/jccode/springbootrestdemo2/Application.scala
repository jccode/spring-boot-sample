package com.github.jccode.springbootrestdemo2


import org.springframework.boot.{CommandLineRunner, SpringApplication}
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext

@SpringBootApplication
class Application {
}

object Application {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[Application], args: _*)
  }

  def commandLineRunner(applicationContext: ApplicationContext): CommandLineRunner = new CommandLineRunner {
    override def run(args: String*): Unit = {
      applicationContext.getBeanDefinitionNames.sorted.foreach(println)
    }
  }
}

