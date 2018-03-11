package com.github.jccode.springcloud.integrateddemo.user.service

import com.github.jccode.springcloud.integrateddemo.user.model.User
import org.junit.Test
import org.junit.runner.RunWith
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.scalatest.Matchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner


@SpringBootTest
@RunWith(classOf[SpringRunner])
@MybatisTest
@Import(Array(classOf[UserService]))
@ActiveProfiles(Array("test"))
class UserServiceTest extends Matchers {

  @Autowired
  private val userService: UserService = null

  private def createTestUser = {
    val user = new User
    user.setName("junit_test_user_name")
    user.setMobile("18100001111")
    user.setPassword("junit_test_user_pass")
    user.setSalt("junit_test_user_pwd_salt")
    user
  }

  @Test
  @Rollback(false)
  def mybatisWorks(): Unit = {
    val testUser = createTestUser
    testUser.getId should be (null)
    val insertResult = userService.save(testUser)
    insertResult should be >= 0
    val id = testUser.getId
    id.toInt should be >= 0
    val user = userService.find(id)
    user.getName should be (testUser.getName)
    user.getMobile should be (testUser.getMobile)
    val deleteResult = userService.delete(id)
    deleteResult should be >= 0
    val user2 = userService.find(id)
    user2 should be (null)
  }
}
