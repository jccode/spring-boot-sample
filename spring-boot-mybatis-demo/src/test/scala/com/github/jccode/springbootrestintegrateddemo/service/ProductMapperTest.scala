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
  * ProductMapperTest
  *
  * @author 01372461
  */
@RunWith(classOf[SpringRunner])
@SpringBootTest
@MybatisTest
@Import(Array(classOf[ProductService]))
class ProductMapperTest extends Matchers {

  @Autowired
  val productService: ProductService = null;

  @Test
  def findProduct(): Unit = {
    val user = productService.find(1)
    println(user)
  }
}
