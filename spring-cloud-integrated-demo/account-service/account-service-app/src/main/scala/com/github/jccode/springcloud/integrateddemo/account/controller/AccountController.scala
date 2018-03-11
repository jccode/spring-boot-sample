package com.github.jccode.springcloud.integrateddemo.account.controller

import com.github.jccode.springbootsample.core.data.Implicits._
import com.github.jccode.springbootsample.core.data.RestResult.RestSuccess
import com.github.jccode.springcloud.integrateddemo.account.AccountApi
import com.github.jccode.springcloud.integrateddemo.account.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{PathVariable, RestController}

@RestController
class AccountController(@Autowired private val accountService: AccountService) extends AccountApi {
  override def getBalance(@PathVariable("id") userId: Integer): RestSuccess[Integer] =
    accountService.getBalance(userId).restResult("user not found")
}

