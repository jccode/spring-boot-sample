package com.github.jccode.springbootsample.core.data

import org.scalatest.{FlatSpec, Matchers}

class RestTest extends FlatSpec with Matchers {

  it should "RestResult api works" in {
    val ret1 = RestResult.success(10)
    ret1.getPayload should equal(10)
    ret1.isError should be (false)
    ret1.error should be (null)

    val errMsg = "error message"
    val ret2 = RestResult.fail(errMsg)
    assert(ret2.payload == null)
    ret2.isError should be (true)
    ret2.error.message should equal(errMsg)
  }

  it should "follow transform on RestResult" in {
    val list = List("1", "2", "3")
    val succRet = RestResult.success(list)
    val tranRet = succRet.map(i => i.mkString(","))
    tranRet should equal(list.mkString(","))

    val failRet: RestResult[List[String], Null] = RestResult(null, "error occur")
    val tranRet2 = failRet.map(i => i.mkString(","))
    tranRet2 should equal(null)
  }

}
