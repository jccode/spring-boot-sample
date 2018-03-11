package com.github.jccode.springbootrestintegrateddemo.controller

import javax.validation.Valid

import com.github.jccode.springbootrestintegrateddemo.form.UserForm
import com.github.jccode.springbootrestintegrateddemo.model.User
import com.github.jccode.springbootrestintegrateddemo.service.UserService
import com.github.jccode.springbootsample.core.data.RestResult
import com.github.jccode.springbootsample.core.data.RestResult.RestSuccess
import com.github.jccode.springbootsample.core.exception.RestException
import com.google.common.base.Preconditions
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation._

import scala.collection.JavaConverters._

@RestController
@RequestMapping(Array("/user"))
class UserController (@Autowired userService: UserService) {

  @GetMapping(Array("/{name}"))
  def find(@PathVariable name: String): RestResult[User, Nothing] = {
    Preconditions.checkNotNull(name)
    val users = userService.findByName(name)
    if (users == null || users.isEmpty) throw new RestException("user not exist.")
    else if (users.lengthCompare(1) > 0) throw new RestException("more than one user have the same name.")
    RestResult(users.head)
  }

  @GetMapping
  def list(): RestSuccess[List[User]] = {
    RestResult(userService.findAll())
  }

  @PostMapping(Array("/register"))
  def register(@Valid userForm: UserForm, result: BindingResult): RestResult[User, List[String]] = {
    if (result.hasErrors) {
      val list = result.getFieldErrors.asScala.map(e => e.getField +" "+e.getDefaultMessage).toList
      RestResult.fail("Illegeal arrguments", list)
    } else {
      val user = new User
      BeanUtils.copyProperties(userForm, user)
      userService.save(user)
      RestResult.success(user)
    }
  }
}
