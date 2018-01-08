package com.github.jccode.springcloud.integrateddemo.order.service;

import com.github.jccode.springbootsample.core.repo.CrudMapper;
import com.github.jccode.springbootsample.core.service.CrudServiceImpl;
import com.github.jccode.springcloud.integrateddemo.order.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends CrudServiceImpl<Order> {

    public OrderService(CrudMapper<Order> mapper) {
        super(mapper);
    }
}
