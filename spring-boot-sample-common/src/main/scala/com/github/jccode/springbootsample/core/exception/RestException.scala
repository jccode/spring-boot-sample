package com.github.jccode.springbootsample.core.exception

class RestException(message: String, cause: Throwable) extends RuntimeException(message, cause) {
  def this(message: String) = this(message, null)
}
