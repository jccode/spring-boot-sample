package com.github.jccode.springbootsample.core.model

import java.util.Date

import scala.beans.BeanProperty

case class BaseEntity (@BeanProperty var id: Int,
                       @BeanProperty var createTime: Date,
                       @BeanProperty var updateTime: Date
                      ) extends Serializable {

}
