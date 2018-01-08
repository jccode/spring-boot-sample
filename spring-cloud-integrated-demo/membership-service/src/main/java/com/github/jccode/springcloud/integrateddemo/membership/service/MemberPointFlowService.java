package com.github.jccode.springcloud.integrateddemo.membership.service;

import com.github.jccode.springbootsample.core.repo.CrudMapper;
import com.github.jccode.springbootsample.core.service.CrudServiceImpl;
import com.github.jccode.springcloud.integrateddemo.membership.model.MemberPointFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberPointFlowService extends CrudServiceImpl<MemberPointFlow> {

    @Autowired
    public MemberPointFlowService(CrudMapper<MemberPointFlow> mapper) {
        super(mapper);
    }
}
