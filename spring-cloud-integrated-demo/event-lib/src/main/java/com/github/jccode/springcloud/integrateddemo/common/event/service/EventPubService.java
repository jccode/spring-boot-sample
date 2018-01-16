package com.github.jccode.springcloud.integrateddemo.common.event.service;

import com.github.jccode.springbootsample.core.repo.CrudMapper;
import com.github.jccode.springbootsample.core.service.CrudServiceImpl;
import com.github.jccode.springcloud.integrateddemo.common.event.model.EventPub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventPubService extends CrudServiceImpl<EventPub> {

    @Autowired
    public EventPubService(CrudMapper<EventPub> mapper) {
        super(mapper);
    }
}
