package com.github.jccode.springbootrestintegrateddemo.service

import com.github.jccode.springbootrestintegrateddemo.model.{User, UserCriteria}
import com.github.jccode.springbootrestintegrateddemo.repo.UserMapper
import com.github.jccode.springbootsample.core.service.CrudServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import scala.collection.JavaConverters._

@Service
class UserService(@Autowired override val mapper: UserMapper) extends CrudServiceImpl[User](mapper) {

  def count: Int = mapper.countByExample(new UserCriteria).toInt

  def findByName(name: String): List[User] = {
    val criteria: UserCriteria = new UserCriteria
    criteria.createCriteria.andNameEqualTo(name)
    mapper.selectByExample(criteria).asScala.toList
  }
}
