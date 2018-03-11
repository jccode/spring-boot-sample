package com.github.jccode.springcloud.integrateddemo.user.controller

import com.github.jccode.springcloud.integrateddemo.user.model.User
import com.github.jccode.springcloud.integrateddemo.user.service.UserService
import org.hamcrest.Matchers.is
import org.junit.runner.RunWith
import org.junit.{Before, Test}
import org.mockito.BDDMockito.given
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
class UserControllerTest {

  @Autowired
  private val mvc: MockMvc = null

  @MockBean
  private val userService: UserService = null

  private var testUser: User = null

  private val TEST_UNAME = "uname"
  private val TEST_NOT_EXIST_NAME = "empty-user"
  private val ID_EXIST = 1
  private val ID_NOT_EXIST = 100

  @Before
  def setup(): Unit = {
    testUser = new User
    testUser.setName(TEST_UNAME)
    testUser.setMobile("18100001111")
    testUser.setPassword("pass")
    testUser.setSalt("salt")
    given(userService.findByName(TEST_UNAME)).willReturn(List(testUser))
    given(userService.findByName(TEST_NOT_EXIST_NAME)).willReturn(List.empty)
    given(userService.find(ID_EXIST)).willReturn(testUser)
    given(userService.find(ID_NOT_EXIST)).willReturn(null)
  }

  @Test
  @throws[Exception]
  def findUserByNameSuccess(): Unit = {
    mvc.perform(get("/users?name=" + TEST_UNAME))
      .andExpect(status.isOk)
      .andExpect(content.contentType(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(jsonPath("isError", is(false)))
      .andExpect(jsonPath("payload.name", is(TEST_UNAME)))
  }

  @Test
  @throws[Exception]
  def findUserByNameFailed(): Unit = {
    mvc.perform(get("/users?name=" + TEST_NOT_EXIST_NAME))
      .andExpect(status.isOk)
      .andExpect(content.contentType(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(jsonPath("isError", is(true)))
  }

  @Test
  @throws[Exception]
  def findUserSuccess(): Unit = {
    mvc.perform(get("/users/" + ID_EXIST))
      .andExpect(status.isOk)
      .andExpect(content.contentType(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(jsonPath("isError", is(false)))
      .andExpect(jsonPath("payload.name", is(TEST_UNAME)))
  }

  @Test
  @throws[Exception]
  def findUserFailed(): Unit = {
    mvc.perform(get("/users/" + ID_NOT_EXIST))
      .andExpect(status.isOk)
      .andExpect(content.contentType(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(jsonPath("isError", is(true)))
  }
}
