package com.github.jccode.configurationpropertiesdemo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.{CommandLineRunner, SpringApplication}
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Service

@SpringBootApplication
class Application {

}

object Application {
  def main(args: Array[String]): Unit = {
    SpringApplication run classOf[Application]
  }
}

@Service
@EnableConfigurationProperties(Array(classOf[MyAppConfigProperties]))
class DemoService @Autowired() (val configProperties: MyAppConfigProperties) extends CommandLineRunner {

  override def run(args: String*): Unit = {
    println("Hello")
    println(configProperties)
  }
}
