package com.github.jccode.springbootrestintegrateddemo.model;

import com.github.jccode.springbootsample.core.model.BaseEntity;

public class Order extends BaseEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ORDERS.USER_ID
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ORDERS.NO
     *
     * @mbg.generated
     */
    private String no;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ORDERS.NOTE
     *
     * @mbg.generated
     */
    private String note;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ORDERS.USER_ID
     *
     * @return the value of ORDERS.USER_ID
     *
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDERS
     *
     * @mbg.generated
     */
    public Order withUserId(Integer userId) {
        this.setUserId(userId);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ORDERS.USER_ID
     *
     * @param userId the value for ORDERS.USER_ID
     *
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ORDERS.NO
     *
     * @return the value of ORDERS.NO
     *
     * @mbg.generated
     */
    public String getNo() {
        return no;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDERS
     *
     * @mbg.generated
     */
    public Order withNo(String no) {
        this.setNo(no);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ORDERS.NO
     *
     * @param no the value for ORDERS.NO
     *
     * @mbg.generated
     */
    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ORDERS.NOTE
     *
     * @return the value of ORDERS.NOTE
     *
     * @mbg.generated
     */
    public String getNote() {
        return note;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDERS
     *
     * @mbg.generated
     */
    public Order withNote(String note) {
        this.setNote(note);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ORDERS.NOTE
     *
     * @param note the value for ORDERS.NOTE
     *
     * @mbg.generated
     */
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDERS
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
        Order other = (Order) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getNo() == null ? other.getNo() == null : this.getNo().equals(other.getNo()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDERS
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getNo() == null) ? 0 : getNo().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDERS
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", no=").append(no);
        sb.append(", note=").append(note);
        sb.append("]");
        return sb.toString();
    }
}