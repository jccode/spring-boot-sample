package com.github.jccode.springcloud.integrateddemo.order.form

import javax.validation.Valid
import javax.validation.constraints.{Min, NotNull}

import scala.beans.BeanProperty

class OrderForm {

  @BeanProperty @NotNull @Min(1) var userId: Integer = _
  @BeanProperty var address: String = _
  @BeanProperty @Valid @NotNull var items: java.util.List[OrderItemForm] = _

  override def toString = s"OrderForm($userId, $address, $items)"
}
