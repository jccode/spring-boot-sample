package com.github.jccode.springbootrestdemo.controller.advice

import javax.servlet.http.HttpServletRequest

import com.github.jccode.springbootrestdemo.common.Failed
import com.github.jccode.springbootrestdemo.exception.RestException
import org.springframework.web.bind.annotation.{ControllerAdvice, ExceptionHandler, ResponseBody}

@ControllerAdvice
class GlobalControllerExceptionHandler {

  @ResponseBody
  @ExceptionHandler(Array(classOf[RestException]))
  def restException(e: Exception, request: HttpServletRequest): Failed =
    new Failed(e.getMessage)

}
