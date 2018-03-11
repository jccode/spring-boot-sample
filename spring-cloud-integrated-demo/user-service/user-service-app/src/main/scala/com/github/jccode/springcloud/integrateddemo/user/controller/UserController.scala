package com.github.jccode.springcloud.integrateddemo.user.controller

import com.github.jccode.springbootsample.core.data.RestResult
import com.github.jccode.springbootsample.core.data.RestResult.RestSuccess
import com.github.jccode.springcloud.integrateddemo.user.api.UserApi
import com.github.jccode.springcloud.integrateddemo.user.model.User
import com.github.jccode.springcloud.integrateddemo.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{PathVariable, RequestParam, RestController}

@RestController
class UserController(@Autowired private val userService: UserService) extends UserApi {

  override def findByName(@RequestParam("name") name: String): RestSuccess[User] = {
    val users = userService.findByName(name)
    var result: RestSuccess[User] = null
    if (users.isEmpty) {
      result = RestResult.fail("user not found")
    } else if (users.lengthCompare(1) > 0) {
      result = RestResult.fail("more than 1 user found")
    } else {
      result = RestResult(users.head)
    }
    result
  }

  override def find(@PathVariable("id") id: Integer): RestSuccess[User] = RestResult(userService.find(id))
}
