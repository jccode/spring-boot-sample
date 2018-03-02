package com.github.jccode.springbootrestdemo

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders._
import org.springframework.test.web.servlet.result.MockMvcResultMatchers._
import org.hamcrest.CoreMatchers._

@RunWith(classOf[SpringRunner])
@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

  @Autowired
  val mvc: MockMvc = null

  @Test
  def getHello(): Unit = {
    mvc.perform(get("/").accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk)
      .andExpect(content().string(equalTo("Greetings from Spring Boot!")))
  }

}
