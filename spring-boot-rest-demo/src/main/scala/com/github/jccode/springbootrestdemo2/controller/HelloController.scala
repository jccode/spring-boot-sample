package com.github.jccode.springbootrestdemo2.controller

import org.springframework.web.bind.annotation.{RequestMapping, RestController}

@RestController
class HelloController {

  @RequestMapping(Array("/"))
  def index(): String = "Greetings from Spring Boot!"

}