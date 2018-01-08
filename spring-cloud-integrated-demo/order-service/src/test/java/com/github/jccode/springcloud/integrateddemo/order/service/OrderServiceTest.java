package com.github.jccode.springcloud.integrateddemo.order.service;

import com.github.jccode.springcloud.integrateddemo.order.model.Order;
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
@MybatisTest
@Import(OrderService.class)
@ActiveProfiles("test")
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void mybatisWorks() {
        Order order = new Order();
        order.setUserId(1);
        order.setPayment(100);
        order.setStatus(0);
        assertThat(order.getId(), is(nullValue()));

        int insertRet = orderService.save(order);
        assertThat(insertRet, greaterThan(0));
        assertThat(order.getId(), greaterThan(0));
    }
}
