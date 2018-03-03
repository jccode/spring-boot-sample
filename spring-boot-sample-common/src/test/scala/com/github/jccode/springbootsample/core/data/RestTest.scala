package com.github.jccode.springbootsample.core.data

import org.scalatest.{FlatSpec, Matchers}

class RestTest extends FlatSpec with Matchers  {


  "Error type" should " works as declarations" in {
    val e1 = Error("hello")
    val e2: Error[Double] = Error[Double]("hello")
    val e3: Error[Nothing] = Error(ErrorCode.NO_DATA, "hello")
    val e4: Error[Double] = Error(ErrorCode.NO_DATA, "hello")
    val e10: Error[Int] = Error("Hello", 3)
    assert(true, "should complie without errors")
    e10.data.get should be (3)
    e2.data should be (None)
  }


  it should " tell me some option test" in {
    val a1: Option[Int] = None
    val a2: Option[Double] = None
  }

  it should " show me the new RestResult api" in {
    val ret1 = RestResult.success(10)
    ret1.payload should be (Some(10))
    ret1.isError should be (false)
    ret1.error should be (None)

    val errMsg = "error message"
    val ret2 = RestResult.fail(errMsg)
    ret2.payload should be (None)
    ret2.isError should be (true)
    ret2.error.get.message should be (errMsg)
  }
}