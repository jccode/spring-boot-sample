package com.github.jccode.springcloud.integrateddemo.user.api

import com.github.jccode.springbootsample.core.data.RestResult
import com.github.jccode.springbootsample.core.data.RestResult.RestSuccess
import com.github.jccode.springcloud.integrateddemo.user.model.User
import org.springframework.stereotype.Component

@Component
class UserApiFallback extends UserApi {

// TODO 这里能获取业务抛出的异常吗?
  override def findByName(name: String): RestSuccess[User] = RestResult.fail("user service is not available")

  override def find(id: Integer): RestSuccess[User] = RestResult.fail("user service is not available")
}
