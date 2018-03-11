package com.github.jccode.springcloud.integrateddemo.account.api

import com.github.jccode.springbootsample.core.data.RestResult.RestSuccess
import org.springframework.cloud.netflix.feign.FeignClient
import org.springframework.web.bind.annotation.{PathVariable, RequestMapping, RequestMethod}


@FeignClient(name = "account-service", fallback = classOf[AccountApiFallback])
trait AccountApi {

  @RequestMapping(value = Array("/users/{id}/balance"), method = Array(RequestMethod.GET))
  def getBalance(@PathVariable("id") userId: Integer): RestSuccess[Integer]

}
