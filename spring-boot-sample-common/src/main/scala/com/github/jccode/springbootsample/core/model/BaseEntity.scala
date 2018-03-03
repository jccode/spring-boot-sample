package com.github.jccode.springbootsample.core.model

import java.util.Date

import scala.beans.BeanProperty

class BaseEntity extends Serializable {

  @BeanProperty var id: Integer = _
  @BeanProperty var createTime: Date = _
  @BeanProperty var updateTime: Date = _


}
