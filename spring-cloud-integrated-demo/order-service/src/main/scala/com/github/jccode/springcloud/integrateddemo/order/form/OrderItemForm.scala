package com.github.jccode.springcloud.integrateddemo.order.form

import javax.validation.constraints.{Min, NotNull}

import scala.beans.BeanProperty

class OrderItemForm {

  @BeanProperty @NotNull @Min(1) var productId: Integer = _
  @BeanProperty @NotNull @Min(1) var amount: Integer = _

  override def toString = s"OrderItemForm($productId, $amount)"
}
