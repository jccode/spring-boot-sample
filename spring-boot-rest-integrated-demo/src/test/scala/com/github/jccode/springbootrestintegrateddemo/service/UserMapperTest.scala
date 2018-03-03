package com.github.jccode.springbootrestintegrateddemo.service

import com.github.jccode.springbootrestintegrateddemo.model.User
import org.junit.Test
import org.junit.runner.RunWith
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.scalatest.Matchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.junit4.SpringRunner


@RunWith(classOf[SpringRunner])
@SpringBootTest
@MybatisTest
@Import(Array(classOf[UserService]))
class UserMapperTest extends Matchers {

  @Autowired
  private val userService: UserService = null

  @Test
  def findUser(): Unit = {
    val user: User = userService.find(1)
    user.getName should equal("hello")
    user.getAge should be (18)
  }

  @Test def count(): Unit = {
    val count = userService.count
    count should be > 0
  }

  @Test
  @Rollback(false)
  def save(): Unit = {
    val user = randomUser
    val c = userService.count
    // Insert
    val ret1 = userService.save(user)
    ret1 should be > 0

    val id = user.getId
    id.toInt should be > 0

    // Assert equals
    val user2 = userService.find(id)
    user2.getName should equal(user2.getName)
    user2.getAge should be(user.getAge)

    val c2 = userService.count
    c2 should be(c + 1)

    // Delete
    val ret = userService.delete(id)
    ret should be > 0

    val c3 = userService.count
    c3 should equal(c)
  }

  @Test
  @Rollback(false) def updateSelective(): Unit = {
    val user = randomUser
    val ret1 = userService.save(user)
    ret1 should be > 0

    val id = user.getId
    id.toInt should be > 0

    // Update user name
    val name = String.valueOf(Math.random)
    val cond = new User
    cond.setId(id)
    cond.setName(name)
    val ret2 = userService.updateSelective(cond)
    ret2 should be > 0

    val user2 = userService.find(id)
    user2.getName should equal(name)
    user2.getName should not equal user.getName
    user2.getAge should equal(user.getAge)

    userService.delete(id)
  }

  @Test def findByName(): Unit = {
    val hello = userService.findByName("hello")
    hello.size should be > 0
    hello(0).getName should be("hello")
  }

  private def randomUser = {
    val randomName = String.valueOf(Math.random)
    val age = 22
    val user = new User
    user.setName(randomName)
    user.setPassword("pass")
    user.setAge(age)
    user
  }

}
