package com.github.jccode.springbootrestdemo

import com.github.jccode.springbootrestdemo.model.User
import com.github.jccode.springbootrestdemo.service.UserService
import org.junit.{Before, Test}
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.hamcrest.Matchers._
import org.mockito.BDDMockito.given
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers._

@RunWith(classOf[SpringRunner])
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

  @Autowired
  val mvc: MockMvc = null

  @MockBean
  val userService: UserService = null

  val TEST_USER_NAME: String = "Tom"
  val TEST_NOT_EXIST_USER: String = "EmptyUser"
  var tom: User = _

  @Before
  def setup: Unit = {
    tom = User(1, TEST_USER_NAME, "tompass", 20)
    given(userService.find(TEST_USER_NAME)).willReturn(Some(tom))
    given(userService.find(TEST_NOT_EXIST_USER)).willReturn(None)
  }

  @Test
  def findAnExistUser: Unit = {
    mvc.perform(get(s"/user/${TEST_USER_NAME}"))
      .andExpect(status().isOk)
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(jsonPath("error", is(false)))
      .andExpect(jsonPath("payload.name", is(TEST_USER_NAME)))
  }

  @Test
  def findAnNotExistUser: Unit = {
    mvc.perform(get(s"/user/${TEST_NOT_EXIST_USER}"))
      .andExpect(status().isOk)
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(jsonPath("error", is(true)))
      .andDo(MockMvcResultHandlers.print())
  }

}
