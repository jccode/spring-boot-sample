package com.github.jccode.springcloud.integrateddemo.order2.controller;

import com.github.jccode.springbootsample.core.data.rest.RestResult;
import com.github.jccode.springcloud.integrateddemo.order2.form.OrderForm;
import com.github.jccode.springcloud.integrateddemo.order2.model.Order;
import com.github.jccode.springcloud.integrateddemo.order2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.github.jccode.springbootsample.core.utils.CommonUtil.success;
import static com.github.jccode.springbootsample.core.utils.WebUtil.checkResult;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/reserve")
    public RestResult reserveOrder(@Valid OrderForm orderForm, BindingResult result) {
        checkResult(result);
        Order order = orderService.reserveOrder(orderForm);
        return success(order);
    }

    // 支付确认.
    @PostMapping("/confirm")
    public RestResult confirmOrder() {
        return null;
    }
}
