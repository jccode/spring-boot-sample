package com.github.jccode.springbootrestintegrateddemo.controller.advice

import javax.servlet.http.HttpServletRequest

import com.github.jccode.springbootsample.core.data.RestResult
import com.github.jccode.springbootsample.core.exception.RestException
import org.springframework.web.bind.annotation.{ControllerAdvice, ExceptionHandler, ResponseBody}

@ControllerAdvice
class GlobalControllerExceptionHandler {

  @ResponseBody
  @ExceptionHandler(Array(classOf[RestException]))
  def restException(e: Exception, request: HttpServletRequest): RestResult[Nothing, Nothing] = RestResult.fail(e.getMessage)

}
