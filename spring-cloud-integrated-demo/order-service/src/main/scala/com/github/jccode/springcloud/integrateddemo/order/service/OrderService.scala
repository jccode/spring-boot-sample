package com.github.jccode.springcloud.integrateddemo.order.service

import com.github.jccode.springbootsample.core.service.CrudServiceImpl
import com.github.jccode.springcloud.integrateddemo.account.api.AccountApi
import com.github.jccode.springcloud.integrateddemo.order.form.OrderForm
import com.github.jccode.springcloud.integrateddemo.order.model.Order
import com.github.jccode.springcloud.integrateddemo.order.repo.{OrderItemMapper, OrderMapper}
import com.github.jccode.springcloud.integrateddemo.user.api.UserApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService(@Autowired override val mapper: OrderMapper) extends CrudServiceImpl(mapper) {

  @Autowired
  private val orderItemMapper: OrderItemMapper = null

  @Autowired
  private val accountClient: AccountApi = null

  @Autowired
  private val userClient: UserApi = null


  def reserveOrder(orderForm: OrderForm): Order = {
    val usrId = orderForm.getUserId
    val result = accountClient.getBalance(usrId)
    if (!result.isError) {
      val balance = result.payload.get
      println(s"usr ${usrId} 's balance is ${balance}")
    }
    // TODO 查产品的价格,看余额是否足够支付

    new Order
  }
}
