package com.github.jccode.springbootrestintegrateddemo.service

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
    val list = orderService.findOrderAndUsers(null)
    println(list)
    list.size should be >= 0
  }

  @Test
  def findOrderExt(): Unit = {
    val list = orderService.findOrderExt(null)
    println(list)
    list.size should be >=0
  }
}
