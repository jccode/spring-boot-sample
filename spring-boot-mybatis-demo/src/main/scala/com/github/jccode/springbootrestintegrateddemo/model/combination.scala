package com.github.jccode.springbootrestintegrateddemo.model


import scala.beans.BeanProperty


class OrderAndUser {

  @BeanProperty var order: Order = _

  @BeanProperty var user: User = _

  override def toString = s"OrderAndUser($order, $user)"
}

class OrderExt extends Order {
  @BeanProperty var userName: String = _

  @BeanProperty var age: Integer = _

  @BeanProperty var user: User = _

  @BeanProperty var details: java.util.List[OrderDetailExt] = _

  override def toString = s"OrderExt($userName, $age, $user)"
}

class OrderDetailExt extends OrderDetail {
  @BeanProperty var product: Product = _
}
