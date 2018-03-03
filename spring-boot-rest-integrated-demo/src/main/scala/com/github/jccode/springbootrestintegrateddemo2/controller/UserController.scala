package com.github.jccode.springbootrestintegrateddemo2.controller

import scala.collection.JavaConverters._
import javax.validation.Valid

import com.github.jccode.springbootrestintegrateddemo2.form.UserForm
import com.github.jccode.springbootrestintegrateddemo2.model.User
import com.github.jccode.springbootrestintegrateddemo2.service.UserService
import com.github.jccode.springbootsample.core.data.RestResult
import com.github.jccode.springbootsample.core.exception.RestException
import com.google.common.base.Preconditions
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.{BindingResult, FieldError}
import org.springframework.web.bind.annotation._
import org.springframework.web.util.WebUtils

@RestController
@RequestMapping(Array("/user"))
class UserController (@Autowired userService: UserService) {

  @GetMapping(Array("/{name}"))
  def find(@PathVariable name: String): RestResult[User, Null] = {
    Preconditions.checkNotNull(name)
    val users = userService.findByName(name)
    if (users == null || users.isEmpty) throw new RestException("user not exist.")
    else if (users.lengthCompare(1) > 0) throw new RestException("more than one user have the same name.")
    RestResult(users.head)
  }


  @PostMapping(Array("/register"))
  def register(@Valid userForm: UserForm, result: BindingResult): RestResult[User, List[String]] = {
    /*
    if (result.hasErrors) {
      val list = result.getFieldErrors.asScala.map(e => e.getField +" "+e.getDefaultMessage).toList
      RestResult.fail("Illegeal arrguments", list)
    } else {
      val user = new User
      BeanUtils.copyProperties(userForm, user)
      userService.save(user)
      RestResult.success(user)
    }
    */
    ???
  }
}
