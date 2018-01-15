package com.github.jccode.springcloud.integrateddemo.common.event.service;

import com.github.jccode.springcloud.integrateddemo.common.event.config.TestConfig;
import com.github.jccode.springcloud.integrateddemo.common.event.model.EventSub;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;


@SpringBootTest(classes = TestConfig.class)
@RunWith(SpringRunner.class)
@MybatisTest
@Import(EventSubService.class)
public class EventSubServiceTest {

    @Autowired
    private EventSubService eventSubService;

    @Test
    public void mybatisWorks() {
        EventSub eventSub = new EventSub();
        eventSub.setUuid("1111");
        eventSub.setType("test_type");
        eventSub.setPayload("payload");
        eventSub.setStatus(0);
        assertThat(eventSub.getId(), is(nullValue()));

        int ret = eventSubService.save(eventSub);
        assertThat(ret, greaterThan(0));
        assertThat(eventSub.getId(), is(notNullValue()));
    }
}