package com.github.jccode.springbootrestintegrateddemo.controller

import com.github.jccode.springbootrestintegrateddemo.form.UserForm
import com.github.jccode.springbootrestintegrateddemo.model.User
import com.github.jccode.springbootrestintegrateddemo.service.UserService
import org.assertj.core.util.Lists
import org.hamcrest.Matchers.{is, iterableWithSize}
import org.junit.{Before, Test}
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.{get, post}
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.{content, jsonPath, status}

@RunWith(classOf[SpringRunner])
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

  @Autowired
  private val mvc: MockMvc = null

  @MockBean
  private val userService: UserService = null

  private var testUser: User = null

  private val TEST_USER_NAME = "test_user"
  private val TEST_NOT_EXIST_USER = "empty_user"

  @Before
  def setup(): Unit = {
    testUser = new User
    testUser.setName(TEST_USER_NAME)
    testUser.setAge(18)
    given(userService.findByName(TEST_USER_NAME)).willReturn(List(testUser))
    given(userService.findByName(TEST_NOT_EXIST_USER)).willReturn(List.empty)
  }

  @Test
  @throws[Exception]
  def findAnExistUser(): Unit = {
    mvc.perform(get("/user/" + TEST_USER_NAME))
      .andExpect(status.isOk)
      .andExpect(content.contentType(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(jsonPath("isError", is(false)))
      .andExpect(jsonPath("payload.name", is(TEST_USER_NAME)))
  }

  @Test
  @throws[Exception]
  def findAnNotExistUser(): Unit = {
    mvc.perform(get("/user/" + TEST_NOT_EXIST_USER))
      .andExpect(status.isOk)
      .andExpect(content.contentType(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(jsonPath("isError", is(true)))
  }

  @Test
  @throws[Exception]
  def registerFailed(): Unit = {
    mvc.perform(post("/user/register"))
      .andExpect(status.isOk)
      .andExpect(content.contentType(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(jsonPath("isError", is(true)))
      .andExpect(jsonPath("error.data", iterableWithSize(3)))
  }

  @Test
  @throws[Exception]
  def registerSuccess(): Unit = {
    val name = "testname"
    val userForm: UserForm = UserForm(name, "111111", 20)
//    userForm.setName(name)
//    userForm.setAge(20)
//    userForm.setPassword("111111")
    mvc.perform(post("/user/register").contentType(MediaType.APPLICATION_FORM_URLENCODED)
      .param("name", name)
      .param("password", "111111")
      .param("age", "20"))
      .andExpect(status.isOk).andExpect(content.contentType(MediaType.APPLICATION_JSON_UTF8))
      .andExpect(jsonPath("isError", is(false)))
      .andExpect(jsonPath("payload.name", is(name)))
      .andExpect(jsonPath("payload.age", is(20)))
  }
}
