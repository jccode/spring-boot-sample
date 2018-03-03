package com.github.jccode.springbootrestintegrateddemo.service

import org.junit.Test
import org.junit.runner.RunWith
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.scalatest.Matchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner

@RunWith(classOf[SpringRunner])
@SpringBootTest
@MybatisTest
@Import(Array(classOf[UserService]))
class UserMapperTest extends Matchers {

  @Autowired
  val userService: UserService = null

  @Test
  def findUser(): Unit = {
    val user = userService.find(1)
    user.getName should be("hello")
    user.getAge should be(18)
  }
}
