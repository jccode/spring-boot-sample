package com.github.jccode.springcloud.integrateddemo.membership.model;

import com.github.jccode.springbootsample.core.model.BaseEntity;

public class MemberPoints extends BaseEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEMBER_POINTS.USER_ID
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MEMBER_POINTS.POINTS
     *
     * @mbg.generated
     */
    private Integer points;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEMBER_POINTS.USER_ID
     *
     * @return the value of MEMBER_POINTS.USER_ID
     *
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MEMBER_POINTS
     *
     * @mbg.generated
     */
    public MemberPoints withUserId(Integer userId) {
        this.setUserId(userId);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEMBER_POINTS.USER_ID
     *
     * @param userId the value for MEMBER_POINTS.USER_ID
     *
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MEMBER_POINTS.POINTS
     *
     * @return the value of MEMBER_POINTS.POINTS
     *
     * @mbg.generated
     */
    public Integer getPoints() {
        return points;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MEMBER_POINTS
     *
     * @mbg.generated
     */
    public MemberPoints withPoints(Integer points) {
        this.setPoints(points);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MEMBER_POINTS.POINTS
     *
     * @param points the value for MEMBER_POINTS.POINTS
     *
     * @mbg.generated
     */
    public void setPoints(Integer points) {
        this.points = points;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MEMBER_POINTS
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
        MemberPoints other = (MemberPoints) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getPoints() == null ? other.getPoints() == null : this.getPoints().equals(other.getPoints()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MEMBER_POINTS
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getPoints() == null) ? 0 : getPoints().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MEMBER_POINTS
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
        sb.append(", points=").append(points);
        sb.append("]");
        return sb.toString();
    }
}