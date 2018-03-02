package com.github.jccode.springbootrestdemo.common

import scala.beans.BeanProperty
import scala.collection.immutable.HashMap

class RestResult[T](@BeanProperty val error: Boolean, @BeanProperty val payload: T, @BeanProperty var meta: Map[String, String]) {

  def addMeta(key: String, value: String): Unit = {
    if (meta == null) meta = new HashMap[String, String]
    meta += (key -> value)
  }

}

class Success[T](payload: T) extends RestResult[T](false, payload, null) {

}

class Failed(payload: String) extends RestResult[String](true, payload, null) {

}

