package com.github.jccode.springcloud.integrateddemo.order.service;

import com.github.jccode.springbootsample.core.data.rest.RestResult;
import com.github.jccode.springbootsample.core.repo.CrudMapper;
import com.github.jccode.springbootsample.core.service.CrudServiceImpl;
import com.github.jccode.springbootsample.core.utils.CommonUtil;
import com.github.jccode.springcloud.integrateddemo.account.api.AccountAPI;
import com.github.jccode.springcloud.integrateddemo.order.constant.OrderStatus;
import com.github.jccode.springcloud.integrateddemo.order.form.OrderForm;
import com.github.jccode.springcloud.integrateddemo.order.form.OrderItemForm;
import com.github.jccode.springcloud.integrateddemo.order.model.Order;
import com.github.jccode.springcloud.integrateddemo.order.repo.OrderItemMapper;
import com.github.jccode.springcloud.integrateddemo.order.repo.OrderMapper;
import com.github.jccode.springcloud.integrateddemo.user.api.UserAPI;
import com.github.jccode.springcloud.integrateddemo.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

import static com.github.jccode.springbootsample.core.utils.CommonUtil.fault;

@Service
public class OrderService extends CrudServiceImpl<Order> {

    private final Logger log = LoggerFactory.getLogger(OrderService.class);

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
        log.info("reverse order | {}", orderForm);

        Integer userId = orderForm.getUserId();
        // 获取产品总价
        int totalPrice = orderForm.getItems().stream().mapToInt(OrderItemForm::getAmount).sum();

        // 检查用户余额
        RestResult<Integer> result = accountClient.getBalance(userId);

        // 判断用户余额是否足够赎买. 然后生成订单.
        if (result.getIsError()) {
            fault("Failed to check user account balance. " + result.getError().toString());
        }
        else if (result.getPayload() < totalPrice) {
            fault("User doesn't have enough money for this order. Total price: "+totalPrice+", account balance: "+result.getPayload());
        }


        Order order = new Order();
        order.setUserId(orderForm.getUserId());
        order.setAddress(orderForm.getAddress());
        order.setPayment(totalPrice);
        order.setStatus(OrderStatus.NON_PAY.getValue());
        Date now = new Date();
        order.setCreateTime(now);
        order.setUpdateTime(now);
        // int id = orderMapper.insert(order);

        return order;
    }
}
