package com.github.jccode.springcloud.integrateddemo.membership.service

import com.github.jccode.springbootsample.core.service.CrudServiceImpl
import com.github.jccode.springcloud.integrateddemo.membership.repo.MemberPointFlowMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MemberPointFlowService(@Autowired override val mapper: MemberPointFlowMapper) extends CrudServiceImpl(mapper) {

}
