package com.github.jccode.springbootrestdemo.model

import scala.beans.BeanProperty

case class User(@BeanProperty val id: Int = 0,
                @BeanProperty val name: String = "",
                @BeanProperty val password: String = "",
                @BeanProperty val age: Int = 0)
