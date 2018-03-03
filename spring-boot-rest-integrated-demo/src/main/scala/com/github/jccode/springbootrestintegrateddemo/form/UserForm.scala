package com.github.jccode.springbootrestintegrateddemo.form

import javax.validation.constraints.{Min, NotNull, Size}

import scala.beans.BeanProperty

case class UserForm(@BeanProperty @NotNull @Size(min = 3, max = 30) var name: String,
                    @BeanProperty @NotNull @Size(min = 6, max = 20) var password: String,
                    @BeanProperty @NotNull @Min(18) var age: Int) {
}
