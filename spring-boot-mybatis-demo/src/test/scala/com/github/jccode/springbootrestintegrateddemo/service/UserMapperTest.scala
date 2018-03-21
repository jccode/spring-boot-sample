package com.github.jccode.springbootrestintegrateddemo.service

import com.github.jccode.springbootrestintegrateddemo.model.UserCriteria
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
    user.getName should be("bob")
    user.getAge should be(18)
  }

  @Test
  def findUserWithOrders(): Unit = {
    val criteria = new UserCriteria
    criteria.createCriteria().andIdEqualTo(1)
    val users = userService.findUserWithOrders(criteria)
    users.size should be (1)
    val user = users.head
    user.orders.size() should be (2)
  }
}
