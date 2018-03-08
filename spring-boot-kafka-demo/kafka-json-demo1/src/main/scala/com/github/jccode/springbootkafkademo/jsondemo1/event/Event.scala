package com.github.jccode.springbootkafkademo.jsondemo1.event

import java.util.UUID

import scala.beans.BeanProperty

// 要加 @BeanProperty 以符合java规范, 要不然json序列化有问题.
class Event(@BeanProperty val uuid: String, @BeanProperty val eventType: String, @BeanProperty val payload: Any) extends Serializable {
  def this() = this(null, null, null)
  override def toString = s"Event($uuid, $eventType, $payload)"
}

object Event {
  def apply(uuid: String, eventType: String, payload: Any): Event =
    new Event(uuid, eventType, payload)

  def apply(eventType: String, payload: Any): Event =
    new Event(UUID.randomUUID().toString, eventType, payload)
}
