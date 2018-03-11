package com.github.jccode.springcloud.integrateddemo.account.service

import com.github.jccode.springcloud.integrateddemo.account.model.Account
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.{greaterThan, is, nullValue}
import org.junit.Test
import org.junit.runner.RunWith
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.scalatest.Matchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(classOf[SpringRunner])
@MybatisTest
@Import(Array(classOf[AccountService]))
@ActiveProfiles(Array("test"))
class AccountServiceTest extends Matchers {

  @Autowired
  private val accountService: AccountService = null

  @Test
  def mybatisWorks(): Unit = {
    val account = new Account
    account.setName("Hello")
    account.setUserId(1)
    account.setBalance(10)
    account.getId should be (null)
    val ret = accountService.save(account)
    ret should be > 0
    account.getId().toInt should be > 0
  }
}
