package com.github.jccode.springbootrestintegrateddemo2.service

import com.github.jccode.springbootrestintegrateddemo2.model.{User, UserCriteria}
import com.github.jccode.springbootrestintegrateddemo2.repo.UserMapper
import com.github.jccode.springbootsample.core.service.CrudServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import scala.collection.JavaConverters._

@Service
class UserService(@Autowired override val mapper: UserMapper[User]) extends CrudServiceImpl[User](mapper) {

  def count: Int = mapper.countByExample(new UserCriteria).toInt

  def findByName(name: String): List[User] = {
    val criteria: UserCriteria = new UserCriteria
    criteria.createCriteria.andNameEqualTo(name)
    mapper.selectByExample(criteria).asScala.toList
  }
}
