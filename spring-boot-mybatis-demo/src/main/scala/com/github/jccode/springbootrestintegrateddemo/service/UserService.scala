package com.github.jccode.springbootrestintegrateddemo.service

import com.github.jccode.springbootrestintegrateddemo.model.{User, UserCriteria, UserExt}
import com.github.jccode.springbootrestintegrateddemo.repo.UserMapper
import com.github.jccode.springbootsample.core.service.CrudServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import scala.collection.JavaConverters._

@Service
class UserService(@Autowired override val mapper: UserMapper) extends CrudServiceImpl[User](mapper) {

  def findUserWithOrders(example: UserCriteria): Seq[UserExt] = mapper.findUserWithOrders(example).asScala

}
