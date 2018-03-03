package com.github.jccode.springbootrestintegrateddemo.form

import javax.validation.constraints.{Min, NotNull, Size}

import scala.beans.BeanProperty

/*
case class UserForm(@BeanProperty @NotNull @Size(min = 3, max = 30) var name: String,
                    @BeanProperty @NotNull @Size(min = 6, max = 20) var password: String,
                    @BeanProperty @NotNull @Min(18) var age: Int) {
  def this() = this(null, null, 0)
}
*/

class UserForm {

  @BeanProperty @NotNull @Size(min = 3, max = 30) var name: String = _

  @BeanProperty @NotNull @Size(min = 6, max = 20) var password: String = _

  @BeanProperty @NotNull @Min(18) var age: Int = _

  def this(name: String, password: String, age: Int) = {
    this()
    this.name = name
    this.password = password
    this.age = age
  }
}
