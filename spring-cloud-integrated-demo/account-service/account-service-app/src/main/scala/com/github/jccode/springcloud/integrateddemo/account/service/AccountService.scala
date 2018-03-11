package com.github.jccode.springcloud.integrateddemo.account.service


import com.github.jccode.springbootsample.core.service.CrudServiceImpl
import com.github.jccode.springcloud.integrateddemo.account.model.{Account, AccountCriteria}
import com.github.jccode.springcloud.integrateddemo.account.repo.AccountMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import scala.collection.JavaConverters._

@Service
class AccountService(@Autowired override val mapper: AccountMapper) extends CrudServiceImpl(mapper) {
  def getBalance(userId: Integer): Option[Integer] = {
    val criteria = new AccountCriteria
    criteria.createCriteria().andUserIdEqualTo(userId)
    mapper.selectByExample(criteria).asScala.lift(0).map(_.getBalance)
  }
}
