package com.github.jccode.springcloud.integrateddemo.order.form

import javax.validation.Valid
import javax.validation.constraints.{Min, NotNull}

import scala.beans.BeanProperty

class OrderForm {

  // 1.得用这种方式,不能用case class方式;
  // 2.而且primary constructor必须是无参的.要不然 Validation 不起作用.
  @BeanProperty @NotNull @Min(1) var userId: Integer = _
  @BeanProperty var address: String = _
  @BeanProperty @Valid @NotNull var items: java.util.List[OrderItemForm] = _

  override def toString = s"OrderForm($userId, $address, $items)"
}
