package com.github.jccode.springcloud.integrateddemo.membership.service;

import com.github.jccode.springcloud.integrateddemo.membership.model.MemberPointFlow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@SpringBootTest
@RunWith(SpringRunner.class)
@Import(MemberPointFlowService.class)
@MybatisTest
@ActiveProfiles("test")
public class MemberPointFlowServiceTest {

    @Autowired
    private MemberPointFlowService memberPointFlowService;

    @Test
    public void mybatisWorks() {
        MemberPointFlow flow = new MemberPointFlow();
        flow.setUserId(1);
        flow.setOrderId(1);
        flow.setPoint(10);
        assertThat(flow.getId(), is(nullValue()));

        int ret = memberPointFlowService.save(flow);
        assertThat(ret, greaterThan(0));
        assertThat(flow.getId(), greaterThan(0));
    }
}
