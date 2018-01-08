package com.github.jccode.springcloud.integrateddemo.order.controller;

import com.github.jccode.springbootsample.core.data.rest.RestResult;
import com.github.jccode.springcloud.integrateddemo.order.form.OrderForm;
import com.github.jccode.springcloud.integrateddemo.order.service.OrderService;
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
        //  TODO To be implemented.
        System.out.println(orderForm);
        return success("Hello");
    }

    @PostMapping("/confirm")
    public RestResult confirmOrder() {
        return null;
    }
}
