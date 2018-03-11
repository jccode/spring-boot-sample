package com.github.jccode.springcloud.integrateddemo.order2.service;

import com.github.jccode.springbootsample.core.data.rest.RestResult;
import com.github.jccode.springbootsample.core.repo.CrudMapper;
import com.github.jccode.springbootsample.core.service.CrudServiceImpl;
import com.github.jccode.springcloud.integrateddemo.account2.api.AccountAPI;
import com.github.jccode.springcloud.integrateddemo.order2.form.OrderForm;
import com.github.jccode.springcloud.integrateddemo.order2.model.Order;
import com.github.jccode.springcloud.integrateddemo.order2.repo.OrderItemMapper;
import com.github.jccode.springcloud.integrateddemo.order2.repo.OrderMapper;
import com.github.jccode.springcloud.integrateddemo.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends CrudServiceImpl<Order> {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private AccountAPI accountClient;

    @Autowired
    private UserAPI userClient;

    public OrderService(CrudMapper<Order> mapper) {
        super(mapper);
    }

    public Order reserveOrder(OrderForm orderForm) {
        Integer userId = orderForm.getUserId();
        // 获取产品总价
        int totalPrice = 0; // TODO: Calculate total price by each product.

        // 检查用户余额
        RestResult<Integer> result = accountClient.getBalance(userId);

        // 判断用户余额是否足够赎买. 然后生成订单.

        return null;
    }
}
