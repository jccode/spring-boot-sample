package com.github.jccode.springcloud.integrateddemo.account.controller

import com.github.jccode.springcloud.integrateddemo.account.service.AccountService
import org.hamcrest.Matchers.is
import org.junit.{Before, Test}
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.scalatest.Matchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.{content, jsonPath, status}

@SpringBootTest
@RunWith(classOf[SpringRunner])
@AutoConfigureMockMvc
@ActiveProfiles(Array("test"))
class AccountControllerTest extends Matchers {

  @Autowired
  private val mvc: MockMvc = null

  @MockBean
  private val accountService: AccountService = null

  private val UID = 1
  private val UID_BALANCE = 10

  @Before
  def setup(): Unit = {
    given(accountService.getBalance(UID)).willReturn(Some(UID_BALANCE): Option[Integer])
  }

  @Test
  @throws[Exception]
  def getBalance(): Unit = {
    mvc.perform(get("/users/" + UID + "/balance"))
      .andExpect(status.isOk)
      .andExpect(content.contentType(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(jsonPath("isError", is(false)))
      .andExpect(jsonPath("payload", is(UID_BALANCE)))
  }
}
