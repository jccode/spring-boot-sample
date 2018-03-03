package com.github.jccode.springbootsample.core.data

import scala.beans.BeanProperty



object ErrorCode extends Enumeration {
  type ErrorCode = Value
  val NONE = Value(0)
  val NO_DATA = Value(100)
  val AUTH_ERROR = Value(200)
}


import com.github.jccode.springbootsample.core.data.ErrorCode.ErrorCode
class Error[+T](@BeanProperty val code: ErrorCode, @BeanProperty val message: String, @BeanProperty val data: Option[T])

object Error {
  def apply[T](message: String): Error[T] = new Error[T](ErrorCode.NONE, message, None)
  def apply[T](code: ErrorCode, message: String): Error[T] = new Error[T](code, message, None)
  def apply[T](message: String, data: T): Error[T] = new Error[T](ErrorCode.NONE, message, Option(data))
}



sealed class RestResult[+R, +T](@BeanProperty val isError: Boolean, @BeanProperty val payload: Option[R], @BeanProperty val error: Option[Error[T]]) {
}

case class RestSuccess[+R](override val payload: Option[R]) extends RestResult[R, Nothing](false, payload, None)
case class RestFailed[+T](code: ErrorCode , message: String, data: Option[T])
  extends RestResult[Nothing, T](true, None, Some(new Error[T](code, message, data)))

/*
object RestResult {
  def success[R, T](payload: R): RestResult[R, T] = new RestResult[R, T](false, Option(payload), None)
  def fail[R, T](message: String, data: T) = new RestResult[R, T](true, None, Some(Error(message, data)))
  def fail[R, T](message: String): RestResult[R, T] = new RestResult[R, T](true, None, Some(Error(message)))
  def apply[R, T](payload: R, message: String): RestResult[R, T] =
    if (payload == null) fail(message) else success(payload)
  def apply[R, T](payload: R): RestResult[R, T] = apply(payload, "")
  def apply[R, T](payload: R, message: String, data: T): RestResult[R, T] =
    if (payload == null) fail(message, data) else success(payload)
}
*/

object RestResult {
  def success[R, T](payload: R): RestResult[R, T] = new RestSuccess[R](Option(payload))
  def fail[R, T](message: String, data: T) = RestFailed[T](ErrorCode.NONE, message, Option(data))
  def fail[R, T](message: String): RestResult[R, T] = RestFailed[T](ErrorCode.NONE, message, null)
  def apply[R, T](payload: R, message: String): RestResult[R, T] =
    if (payload == null) fail(message) else success(payload)
  def apply[R, T](payload: R): RestResult[R, T] = apply(payload, "")
  def apply[R, T](payload: R, message: String, data: T): RestResult[R, T] =
    if (payload == null) fail(message, data) else success(payload)
}
