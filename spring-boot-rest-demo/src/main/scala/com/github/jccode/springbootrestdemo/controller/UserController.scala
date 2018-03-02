package com.github.jccode.springbootrestdemo.controller

import com.github.jccode.springbootrestdemo.common.{RestResult, Success}
import com.github.jccode.springbootrestdemo.exception.RestException
import com.github.jccode.springbootrestdemo.model.User
import com.github.jccode.springbootrestdemo.service.UserService
import com.google.common.base.Preconditions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{PathVariable, RequestMapping, RequestMethod, RestController}

@RestController
@RequestMapping(Array("/user"))
class UserController (@Autowired val userService: UserService) {

  @RequestMapping(value = Array("/{name}"), method = Array(RequestMethod.GET))
  def find(@PathVariable name: String): RestResult[User] = {
    Preconditions.checkNotNull(name)
    val user = userService.find(name)
    if (user == None) {
      throw new RestException("user not exist")
    }
    new Success[User](user.get)
  }
}
