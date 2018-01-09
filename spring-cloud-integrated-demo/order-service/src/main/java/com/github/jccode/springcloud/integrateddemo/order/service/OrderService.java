package com.github.jccode.springcloud.integrateddemo.order.service;

import com.github.jccode.springbootsample.core.data.rest.RestResult;
import com.github.jccode.springbootsample.core.repo.CrudMapper;
import com.github.jccode.springbootsample.core.service.CrudServiceImpl;
import com.github.jccode.springcloud.integrateddemo.account.api.AccountAPI;
import com.github.jccode.springcloud.integrateddemo.order.form.OrderForm;
import com.github.jccode.springcloud.integrateddemo.order.model.Order;
import com.github.jccode.springcloud.integrateddemo.order.repo.OrderItemMapper;
import com.github.jccode.springcloud.integrateddemo.order.repo.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends CrudServiceImpl<Order> {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private AccountClient accountClient;

    public OrderService(CrudMapper<Order> mapper) {
        super(mapper);
    }

    public Order reserveOrder(OrderForm orderForm) {
        Integer userId = orderForm.getUserId();
        // 获取用户余额
        RestResult<Integer> result = accountClient.getBalance(userId);

        System.out.println("by feign client: ");
        if (!result.isError()) {
            Integer balance = result.getPayload();
            System.out.println("return success: "+balance);
        } else {
            System.out.println("return fail:");
        }
        System.out.println(result);

        return null;
    }
}
