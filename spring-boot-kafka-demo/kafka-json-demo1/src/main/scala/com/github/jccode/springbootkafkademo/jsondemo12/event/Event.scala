package com.github.jccode.springbootkafkademo.jsondemo12.event

import java.util.UUID

case class Event(val uuid: String, val `type`: String, val payload: Any) {
  this(`type`: String, payload: Any) = this(UUID.randomUUID().toString, `type`, payload)
}


