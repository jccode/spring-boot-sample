package com.github.jccode.springcloud.integrateddemo.product.service

import com.github.jccode.springcloud.integrateddemo.product.model.Product
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
@Import(Array(classOf[ProductService]))
@ActiveProfiles(Array("test"))
class ProductServiceTest extends Matchers {

  @Autowired
  val productService: ProductService = null

  @Test
  def mybatisWorks(): Unit = {
    val product = new Product
    product.setName("iphone X")
    product.setPrice(6957)
    product.setStock(0)
    product.getId should be (null)
    val result = productService.save(product)
    result should be >0
    product.getId.toInt should be >0
  }
}
