package com.github.jccode.springcloud.integrateddemo.user.service

import com.github.jccode.springbootsample.core.service.CrudServiceImpl
import com.github.jccode.springcloud.integrateddemo.user.model.{User, UserCriteria}
import com.github.jccode.springcloud.integrateddemo.user.repo.UserMapper
import com.google.common.base.Preconditions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import scala.collection.JavaConverters._

@Service
class UserService(@Autowired override val mapper: UserMapper) extends CrudServiceImpl[User](mapper) {

  def findByName(name: String): List[User] = {
    Preconditions.checkNotNull(name)
    val criteria = new UserCriteria
    criteria.createCriteria().andNameEqualTo(name)
    mapper.selectByExample(criteria).asScala.toList
  }

  def findAll: List[User] = {
    mapper.selectByExample(new UserCriteria).asScala.toList
  }
}
