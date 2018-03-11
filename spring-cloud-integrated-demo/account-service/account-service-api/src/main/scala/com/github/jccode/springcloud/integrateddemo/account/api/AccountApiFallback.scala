package com.github.jccode.springcloud.integrateddemo.account.api

import com.github.jccode.springbootsample.core.data.RestResult
import com.github.jccode.springbootsample.core.data.RestResult.RestSuccess
import org.springframework.stereotype.Component

@Component
class AccountApiFallback extends AccountApi {
  override def getBalance(userId: Integer): RestSuccess[Integer] = RestResult.fail("Get user balance failed")
}
