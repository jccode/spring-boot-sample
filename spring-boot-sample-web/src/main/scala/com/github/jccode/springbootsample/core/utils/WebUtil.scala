package com.github.jccode.springbootsample.core.utils

import com.github.jccode.springbootsample.core.exception.RestException
import com.github.jccode.springbootsample.core.utils.JsonConvertImplicits._
import org.springframework.validation.BindingResult

import scala.collection.JavaConverters._


object WebUtil {

  def checkResult(result: BindingResult): Unit = {
    if (result.hasErrors) {
      val list = result.getFieldErrors().asScala.map(e => s"${e.getField} ${e.getDefaultMessage}")
      throw new RestException(list.toJson)
    }
  }
}
