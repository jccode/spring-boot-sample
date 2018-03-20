package com.github.jccode.springbootrestintegrateddemo.model;

import com.github.jccode.springbootsample.core.model.BaseEntity;

public class OrderDetail extends BaseEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ORDER_DETAIL.ORDERS_ID
     *
     * @mbg.generated
     */
    private Integer ordersId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ORDER_DETAIL.PRODUCT_ID
     *
     * @mbg.generated
     */
    private Integer productId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ORDER_DETAIL.AMOUNT
     *
     * @mbg.generated
     */
    private Integer amount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ORDER_DETAIL.ORDERS_ID
     *
     * @return the value of ORDER_DETAIL.ORDERS_ID
     *
     * @mbg.generated
     */
    public Integer getOrdersId() {
        return ordersId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDER_DETAIL
     *
     * @mbg.generated
     */
    public OrderDetail withOrdersId(Integer ordersId) {
        this.setOrdersId(ordersId);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ORDER_DETAIL.ORDERS_ID
     *
     * @param ordersId the value for ORDER_DETAIL.ORDERS_ID
     *
     * @mbg.generated
     */
    public void setOrdersId(Integer ordersId) {
        this.ordersId = ordersId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ORDER_DETAIL.PRODUCT_ID
     *
     * @return the value of ORDER_DETAIL.PRODUCT_ID
     *
     * @mbg.generated
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDER_DETAIL
     *
     * @mbg.generated
     */
    public OrderDetail withProductId(Integer productId) {
        this.setProductId(productId);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ORDER_DETAIL.PRODUCT_ID
     *
     * @param productId the value for ORDER_DETAIL.PRODUCT_ID
     *
     * @mbg.generated
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ORDER_DETAIL.AMOUNT
     *
     * @return the value of ORDER_DETAIL.AMOUNT
     *
     * @mbg.generated
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDER_DETAIL
     *
     * @mbg.generated
     */
    public OrderDetail withAmount(Integer amount) {
        this.setAmount(amount);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ORDER_DETAIL.AMOUNT
     *
     * @param amount the value for ORDER_DETAIL.AMOUNT
     *
     * @mbg.generated
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDER_DETAIL
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OrderDetail other = (OrderDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrdersId() == null ? other.getOrdersId() == null : this.getOrdersId().equals(other.getOrdersId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDER_DETAIL
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrdersId() == null) ? 0 : getOrdersId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDER_DETAIL
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ordersId=").append(ordersId);
        sb.append(", productId=").append(productId);
        sb.append(", amount=").append(amount);
        sb.append("]");
        return sb.toString();
    }
}