package com.github.jccode.springbootrestintegrateddemo.service

import com.github.jccode.springbootrestintegrateddemo.model.OrderCriteria
import org.junit.Test
import org.junit.runner.RunWith
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.scalatest.Matchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner

/**
  * OrderMapperTest
  *
  * @author 01372461
  */
@RunWith(classOf[SpringRunner])
@SpringBootTest
@MybatisTest
@Import(Array(classOf[OrderService]))
class OrderMapperTest extends Matchers {

  @Autowired
  val orderService: OrderService = null

  @Test
  def findOrderAndUser(): Unit = {
    val criteria = new OrderCriteria()
    criteria.createCriteria().andNoEqualTo("1001")
    val list = orderService.findOrderAndUsers(criteria)
    list.size should be (1)
    list(0).user should not be null
  }

  @Test
  def findOrderExt(): Unit = {
    val criteria = new OrderCriteria
    criteria.createCriteria().andNoEqualTo("1001")
    val list = orderService.findOrderExt(criteria)
    println(list)
    list.size should be >=0
  }
}
