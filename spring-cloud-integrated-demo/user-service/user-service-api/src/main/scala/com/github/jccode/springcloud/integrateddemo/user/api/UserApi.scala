package com.github.jccode.springcloud.integrateddemo.user.api

import com.github.jccode.springbootsample.core.data.RestResult.RestSuccess
import com.github.jccode.springcloud.integrateddemo.user.model.User
import org.springframework.cloud.netflix.feign.FeignClient
import org.springframework.web.bind.annotation.{PathVariable, RequestMapping, RequestMethod, RequestParam}

@FeignClient(value = "user-service", fallback = classOf[UserApiFallback])
trait UserApi {

  @RequestMapping(value = Array("/users"), method = Array(RequestMethod.GET))
  def findByName(@RequestParam("name") name: String): RestSuccess[User]

  @RequestMapping(value = Array("/users/{id}"), method = Array(RequestMethod.GET))
  def find(@PathVariable("id") id: Integer): RestSuccess[User]

}
