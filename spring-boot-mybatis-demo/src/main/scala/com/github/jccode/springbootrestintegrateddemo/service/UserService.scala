package com.github.jccode.springbootrestintegrateddemo.service

import com.github.jccode.springbootrestintegrateddemo.model.User
import com.github.jccode.springbootrestintegrateddemo.repo.UserMapper
import com.github.jccode.springbootsample.core.service.CrudServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired override val mapper: UserMapper) extends CrudServiceImpl[User](mapper) {

}
