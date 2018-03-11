package com.github.jccode.springcloud.integrateddemo.order.controller

import javax.validation.Valid

import com.github.jccode.springbootsample.core.data.RestResult
import com.github.jccode.springbootsample.core.data.RestResult.RestSuccess
import com.github.jccode.springbootsample.core.utils.WebUtil
import com.github.jccode.springcloud.integrateddemo.order.form.OrderForm
import com.github.jccode.springcloud.integrateddemo.order.model.Order
import com.github.jccode.springcloud.integrateddemo.order.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.{PostMapping, RestController}

@RestController
class OrderController(@Autowired val orderService: OrderService) {

  @PostMapping(Array("/reserve"))
  def reserveOrder(@Valid orderForm: OrderForm, result: BindingResult): RestSuccess[Order] = {
    WebUtil.checkResult(result)
    RestResult.success(orderService.reserveOrder(orderForm))
  }

  @PostMapping(Array("/confirm"))
  def confirmOrder() = ???

}
