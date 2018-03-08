package com.github.jccode.springbootkafkademo.jsondemo1

import com.github.jccode.springbootkafkademo.jsondemo1.config.{CommonConfig, JsonConfig}
import com.github.jccode.springbootkafkademo.jsondemo1.event.EventProducer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.{CommandLineRunner, SpringApplication}
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.{Bean, Import}

@SpringBootApplication
@Import(Array(classOf[JsonConfig]))
class Application {

  @Autowired private val producer: EventProducer = null

  /*
  @Bean def eventTestRunner(ctx: ApplicationContext): CommandLineRunner = (args: Array[String]) => {
    def foo(args: Array[String]) = {
      producer.produceEvents1
      producer.produceEvents2
    }

    foo(args)
  }
  */

  @Bean
  def eventTestRunner(ctx: ApplicationContext) = new CommandLineRunner {
    override def run(strings: String*): Unit = {
      producer.produceEvents1()
      producer.produceEvents2()
    }
  }
}

object Application {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[Application], args: _*)
  }
}

