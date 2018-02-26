package com.github.jccode.springbootrestdemo2.controller

import com.github.jccode.springbootrestdemo2.common.{RestResult, Success}
import com.github.jccode.springbootrestdemo2.model.User
import com.github.jccode.springbootrestdemo2.service.UserService
import com.google.common.base.Preconditions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{PathVariable, RequestMapping, RequestMethod, RestController}

@RestController
@RequestMapping(Array("/user"))
class UserController (@Autowired val userService: UserService) {

  @RequestMapping(value = Array("/{name}"), method = Array(RequestMethod.GET))
  def find(@PathVariable name: String): RestResult[User] = {
//    Preconditions.checkNotNull(name)
    val user = userService.find(user)
    if (user == null) {

    }
    new Success[User](user)
  }
}
