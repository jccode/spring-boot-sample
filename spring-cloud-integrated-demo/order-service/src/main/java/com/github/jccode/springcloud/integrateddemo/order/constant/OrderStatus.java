package com.github.jccode.springcloud.integrateddemo.order.constant;

/**
 * OrderStatus
 *
 * @author 01372461
 */
public enum OrderStatus {
    NON_PAY(0, "待支付"), PAID(1, "已支付");

    private int value;
    private String desc;

    OrderStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
