package com.github.jccode.springcloud.integrateddemo.order.service

import com.github.jccode.springcloud.integrateddemo.account.api.AccountApi
import com.github.jccode.springcloud.integrateddemo.order.model.Order
import com.github.jccode.springcloud.integrateddemo.user.api.UserApi
import org.junit.Test
import org.junit.runner.RunWith
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.scalatest.Matchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.cloud.netflix.feign.FeignAutoConfiguration
import org.springframework.cloud.netflix.feign.ribbon.FeignRibbonClientAutoConfiguration
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(classOf[SpringRunner])
@MybatisTest
@Import(Array(classOf[OrderService]))
// Ugly fixed EnableFeignClient annotation. https://stackoverflow.com/questions/43093968/enablefeignclients-and-feignclient-fail-on-autowiring-feigncontext-nosuchbea
@ImportAutoConfiguration(Array(classOf[RibbonAutoConfiguration], classOf[FeignRibbonClientAutoConfiguration], classOf[FeignAutoConfiguration]))
@ActiveProfiles(Array("test"))
class OrderServiceTest extends Matchers {

  @Autowired
  private val orderService: OrderService = null

  @MockBean(name = "accountApi")
  private val accountClient: AccountApi = null

  @MockBean(name = "userApi")
  private val userClient: UserApi = null

  @Test
  def mybatisWorks(): Unit = {
    val order = new Order
    order.setUserId(1)
    order.setPayment(100)
    order.setStatus(0)
    order.getId should be (null)
    val insertRet = orderService.save(order)
    insertRet should be > 0
    order.getId.toInt should be > 0
  }
}
