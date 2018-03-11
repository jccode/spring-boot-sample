package com.github.jccode.springbootsample.core.data

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.module.scala.JsonScalaEnumeration
import com.github.jccode.springbootsample.core.data.RestResult.RestSuccess

import scala.beans.BeanProperty


object ErrorCode extends Enumeration {
  type ErrorCode = Value
  val NONE = Value(0)
  val NO_DATA = Value(100)
  val AUTH_ERROR = Value(200)
}
class ErrorCodeType extends TypeReference[ErrorCode.type]


import com.github.jccode.springbootsample.core.data.ErrorCode.ErrorCode
class Error[+T](@BeanProperty @JsonScalaEnumeration(classOf[ErrorCodeType]) val code: ErrorCode, @BeanProperty val message: String, @BeanProperty val data: Option[T])

object Error {
  def apply[T](message: String): Error[T] = new Error[T](ErrorCode.NONE, message, None)
  def apply[T](code: ErrorCode, message: String): Error[T] = new Error[T](code, message, None)
  def apply[T](message: String, data: T): Error[T] = new Error[T](ErrorCode.NONE, message, Option(data))
}


sealed class RestResult[+R, +T](@BeanProperty val isError: Boolean, @BeanProperty val payload: Option[R], @BeanProperty val error: Option[Error[T]]) {
}

/*
case class RestSuccess[+R](override val payload: Option[R]) extends RestResult[R, Nothing](false, payload, None)
case class RestFailed[+T](private val code: ErrorCode , private val message: String, private val data: Option[T])
  extends RestResult[Nothing, T](true, None, Some(new Error[T](code, message, data)))
*/

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
  type RestSuccess[+R] = RestResult[R, Nothing]
  type RestFailed[+T] = RestResult[Nothing, T]

  // def success[R, T](payload: R): RestResult[R, T] = new RestResult[R, T](false, Option(payload), None)
  def success[R](payload: R): RestSuccess[R] = new RestResult[R, Nothing](false, Option(payload), None)

  def fail[R, T](message: String, data: T) = new RestResult[R, T](true, None, Some(Error(message, data)))
  def fail[R, T](message: String): RestResult[R, T] = new RestResult[R, T](true, None, Some(Error(message)))

  def apply[R, T](payload: R, message: String): RestResult[R, T] =
    if (payload == null) fail(message) else success[R](payload)

  def apply[R, T](payload: R): RestResult[R, T] = apply(payload, "")
  def apply[R, T](payload: R, message: String, data: T): RestResult[R, T] =
    if (payload == null) fail(message, data) else success[R](payload)

}


class RichOption[+A](val option: Option[A]) {
  def restResult(implicit message: String): RestSuccess[A] =
    if (option.isEmpty) RestResult.fail(message) else RestResult.success(option.get)
}


object Implicits {
  implicit def option2RichOption[A](option: Option[A]): RichOption[A] = new RichOption[A](option)
}