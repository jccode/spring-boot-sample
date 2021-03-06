package com.github.jccode.springcloud.integrateddemo.common.event.service;

import com.github.jccode.springbootsample.core.repo.CrudMapper;
import com.github.jccode.springbootsample.core.service.CrudServiceImpl;
import com.github.jccode.springcloud.integrateddemo.common.event.model.EventSub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO  mybatis 映射数据库字段到 enum.
 */
@Service
public class EventSubService extends CrudServiceImpl<EventSub> {

    @Autowired
    public EventSubService(CrudMapper<EventSub> mapper) {
        super(mapper);
    }
}
