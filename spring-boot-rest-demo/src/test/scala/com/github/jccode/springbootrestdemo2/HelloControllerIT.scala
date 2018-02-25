package com.github.jccode.springbootrestdemo2

import java.net.URL

import org.junit.{Before, Test}
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner
import org.hamcrest.MatcherAssert._
import org.hamcrest.CoreMatchers._

@RunWith(classOf[SpringRunner])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerIT {

  @LocalServerPort
  val port: Int = 0

  @Autowired
  val template: TestRestTemplate = null

  var base: URL = null

  @Before
  def setUp(): Unit = {
    base = new URL(s"http://localhost:$port/")
  }

  @Test
  def getHello(): Unit = {
    val response = template.getForEntity(base.toString, classOf[String])
    assertThat(response.getBody, equalTo("Greetings from Spring Boot!"))
  }
}
