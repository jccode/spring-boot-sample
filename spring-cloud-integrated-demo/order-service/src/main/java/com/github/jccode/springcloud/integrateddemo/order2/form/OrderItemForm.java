package com.github.jccode.springcloud.integrateddemo.order2.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderItemForm {

    @NotNull
    @Min(1)
    private int productId;

    @NotNull
    @Min(1)
    private int amount;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
