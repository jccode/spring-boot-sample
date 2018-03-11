package com.github.jccode.springcloud.integrateddemo.membership.service


import com.github.jccode.springcloud.integrateddemo.membership.model.MemberPointFlow
import org.junit.Test
import org.junit.runner.RunWith
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.scalatest.Matchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(classOf[SpringRunner])
@Import(Array(classOf[MemberPointFlowService]))
@MybatisTest
@ActiveProfiles(Array("test"))
class MemberPointFlowServiceTest extends Matchers {

  @Autowired
  private val memberPointFlowService: MemberPointFlowService = null

  @Test
  def mybatisWorks(): Unit = {
    val flow = new MemberPointFlow
    flow.setUserId(1)
    flow.setOrderId(1)
    flow.setPoint(10)
    flow.getId should be (null)
    val ret = memberPointFlowService.save(flow)
    ret should be >0
    flow.getId.toInt should be >0
  }
}
