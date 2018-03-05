package com.github.jccode.springbootkafkademo.jsondemo12.event

import java.util.UUID

class Event(val uuid: String, val eventType: String, val payload: Any) extends Serializable {
  def this() = this(null, null, null)

  override def toString = s"Event($uuid, $eventType, $payload)"
}

object Event {
  def apply(uuid: String, eventType: String, payload: Any): Event =
    new Event(uuid, eventType, payload)

  def apply(eventType: String, payload: Any): Event =
    new Event(UUID.randomUUID().toString, eventType, payload)
}
