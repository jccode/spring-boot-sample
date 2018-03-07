package com.github.jccode.springbootkafkademo.jsondemo22.event

import java.util.UUID

import scala.beans.BeanProperty


class Event[T](@BeanProperty val uuid: String,
               @BeanProperty val eventType: String,
               @BeanProperty val payload: T) extends Serializable {

  def this(eventType: String, payload: T) = this(UUID.randomUUID.toString, eventType, payload)

  override def toString = s"Event($uuid, $eventType, $payload)"
}

object Event {

  def apply[T](uuid: String, eventType: String, payload: T): Event[T] = new Event(uuid, eventType, payload)

  def apply[T](eventType: String, payload: T): Event[T] = new Event(eventType, payload)

}
