package com.github.jccode.springbootkafkademo.jsondemo22.event

import java.util.UUID


class Event[T] extends Serializable {

  var uuid: String = _

  var eventType: String = _

  var payload: T = _

  def this(uuid: String, eventType: String, payload: T) = {
    this()
    this.uuid = uuid
    this.eventType = eventType
    this.payload = payload
  }

  def this(eventType: String, payload: T) = {
    this()
    this.uuid = UUID.randomUUID.toString
    this.eventType = eventType
    this.payload = payload
  }

  override def toString = s"Event($uuid, $eventType, $payload)"
}

object Event {

  def apply[T](uuid: String, eventType: String, payload: T): Event[T] = new Event(uuid, eventType, payload)

  def apply[T](eventType: String, payload: T): Event[T] = new Event(eventType, payload)

}
