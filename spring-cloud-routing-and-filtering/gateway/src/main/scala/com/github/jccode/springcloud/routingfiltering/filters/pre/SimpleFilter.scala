package com.github.jccode.springcloud.routingfiltering.filters.pre

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.slf4j.{Logger, LoggerFactory}

class SimpleFilter extends ZuulFilter {

  private val log: Logger = LoggerFactory.getLogger(classOf[SimpleFilter])

  override def filterOrder(): Int = 1

  override def filterType(): String = "pre"

  override def run(): AnyRef = {
    val context = RequestContext.getCurrentContext
    val request = context.getRequest
    log.info(s"${request.getMethod} request to ${request.getRequestURL}")
    null
  }

  override def shouldFilter(): Boolean = true
}
