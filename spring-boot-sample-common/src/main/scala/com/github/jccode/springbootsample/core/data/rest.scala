package com.github.jccode.springbootsample.core.data

import com.github.jccode.springbootsample.core.data.ErrorCode.ErrorCode

import scala.beans.BeanProperty


object ErrorCode extends Enumeration {
  type ErrorCode = Value
  val NONE = Value(0)
  val NO_DATA = Value(100)
  val AUTH_ERROR = Value(200)
}


class Error[F >: Null](@BeanProperty val code: ErrorCode, @BeanProperty val message: String, @BeanProperty val data: F) {
  def this(message: String) = this(ErrorCode.NONE, message, null)
  def this(message: String, data: F) = this(ErrorCode.NONE, message, data)
  def this(code: ErrorCode, message: String) = this(ErrorCode.NONE, message, null)
}

object Error {
  def apply(message: String) = new Error(message)
  def apply[F >: Null](message: String, data: F): Error[F] = new Error(ErrorCode.NONE, message, data)
  def apply(code: ErrorCode, message: String) = new Error(code, message)
}


class RestResult[R >: Null, F >: Null](@BeanProperty val isError: Boolean, @BeanProperty val payload: R, @BeanProperty val error: Error[F]) {
  def this(isError: Boolean, payload: R) = this(isError, payload, null)
  def this(isError: Boolean, error: Error[F]) = this(isError, null, error)

  def map[T >: Null](successFun: (R) => T): T = {
    if(isError) null else successFun.apply(payload)
  }

  def onSuccess(successFun: (R) => Unit): Unit = {
    if (!isError) successFun.apply(payload)
  }

  def onFailure(failFun: (Error[F]) => Unit): Unit = {
    if (isError) failFun.apply(error)
  }

}

object RestResult {

  def success[R >: Null, F >: Null](payload: R): RestResult[R, F] = new RestResult[R, F](false, payload)

  def fail[R >: Null, F >: Null](message: String): RestResult[Null, Null] = new RestResult[Null, Null](true, Error(message))

  def fail[F >: Null](message: String, data: F) = new RestResult[Null, F](true, Error(message, data))

//  def create[R >: Null, F >: Null](payload: R, message: String): RestResult[R, F] =
//    if(payload == null) fail(message) else success(payload)
//
//  def create[R](payload: R) =
//    if(payload == null) fail("payload is null") else success(payload)

  def apply[R >: Null](payload: R, message: String): RestResult[R, Null] =
    if (payload == null) {
      new RestResult[R, Null](true, Error(message))
    } else {
      success(payload)
    }

  def apply[R >: Null](payload: R): RestResult[R, Null] =
    if (payload == null)
      new RestResult[R, Null](true, Error("payload is null"))
    else
      success(payload)

  def apply[R >: Null, F >: Null](payload: R, message: String, data: F): RestResult[R, F] =
    if (payload == null) {
      new RestResult[R, F](true, null, Error(message, data))
    } else {
      new RestResult[R, F](false, payload)
    }

}