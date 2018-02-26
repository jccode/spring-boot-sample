package com.github.jccode.springbootrestdemo2.service

import com.github.jccode.springbootrestdemo2.model.User
import org.springframework.stereotype.Service

@Service
class UserService {

  val userMap = Map(
    "hello" -> User(1, "hello", "world", 10),
    "tom" -> User(2, "tom", "cat", 10),
  )

  def find(name: String): User = userMap(name)
}
