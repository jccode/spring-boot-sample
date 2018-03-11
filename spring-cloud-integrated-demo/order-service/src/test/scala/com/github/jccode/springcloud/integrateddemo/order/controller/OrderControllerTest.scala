package com.github.jccode.springcloud.integrateddemo.order.controller

import com.github.jccode.springcloud.integrateddemo.order.service.OrderService
import org.hamcrest.CoreMatchers.is
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.{content, jsonPath, status}

@SpringBootTest
@RunWith(classOf[SpringRunner])
@AutoConfigureMockMvc
@ActiveProfiles(Array("test"))
class OrderControllerTest {

  @Autowired
  private val mvc: MockMvc = null

  @MockBean
  private val orderService: OrderService = null

  @Test
  @throws[Exception]
  def reserveOrder(): Unit = { // TODO Bo be implemented
    mvc.perform(post("/reserve")
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .param("userId", "10")
      .param("items[0].productId", "1")
      .param("items[0].amount", "0")
      .param("items[1].productId", "2")
      .param("items[1].amount", "2")
    )
      .andExpect(status.isOk)
      .andDo(print)
      .andExpect(content.contentType(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(jsonPath("isError", is(true)))
  }
}
