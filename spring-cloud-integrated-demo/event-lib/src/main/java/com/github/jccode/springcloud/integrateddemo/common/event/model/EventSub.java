package com.github.jccode.springcloud.integrateddemo.common.event.model;

import com.github.jccode.springbootsample.core.model.BaseEntity;

public class EventSub extends BaseEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EVENT_SUB.UUID
     *
     * @mbg.generated
     */
    private String uuid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EVENT_SUB.TYPE
     *
     * @mbg.generated
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EVENT_SUB.PAYLOAD
     *
     * @mbg.generated
     */
    private String payload;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EVENT_SUB.STATUS
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EVENT_SUB.LOCK
     *
     * @mbg.generated
     */
    private Integer lock;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EVENT_SUB.UUID
     *
     * @return the value of EVENT_SUB.UUID
     *
     * @mbg.generated
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_SUB
     *
     * @mbg.generated
     */
    public EventSub withUuid(String uuid) {
        this.setUuid(uuid);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EVENT_SUB.UUID
     *
     * @param uuid the value for EVENT_SUB.UUID
     *
     * @mbg.generated
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EVENT_SUB.TYPE
     *
     * @return the value of EVENT_SUB.TYPE
     *
     * @mbg.generated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_SUB
     *
     * @mbg.generated
     */
    public EventSub withType(String type) {
        this.setType(type);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EVENT_SUB.TYPE
     *
     * @param type the value for EVENT_SUB.TYPE
     *
     * @mbg.generated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EVENT_SUB.PAYLOAD
     *
     * @return the value of EVENT_SUB.PAYLOAD
     *
     * @mbg.generated
     */
    public String getPayload() {
        return payload;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_SUB
     *
     * @mbg.generated
     */
    public EventSub withPayload(String payload) {
        this.setPayload(payload);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EVENT_SUB.PAYLOAD
     *
     * @param payload the value for EVENT_SUB.PAYLOAD
     *
     * @mbg.generated
     */
    public void setPayload(String payload) {
        this.payload = payload == null ? null : payload.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EVENT_SUB.STATUS
     *
     * @return the value of EVENT_SUB.STATUS
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_SUB
     *
     * @mbg.generated
     */
    public EventSub withStatus(Integer status) {
        this.setStatus(status);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EVENT_SUB.STATUS
     *
     * @param status the value for EVENT_SUB.STATUS
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EVENT_SUB.LOCK
     *
     * @return the value of EVENT_SUB.LOCK
     *
     * @mbg.generated
     */
    public Integer getLock() {
        return lock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_SUB
     *
     * @mbg.generated
     */
    public EventSub withLock(Integer lock) {
        this.setLock(lock);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EVENT_SUB.LOCK
     *
     * @param lock the value for EVENT_SUB.LOCK
     *
     * @mbg.generated
     */
    public void setLock(Integer lock) {
        this.lock = lock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_SUB
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
        EventSub other = (EventSub) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getPayload() == null ? other.getPayload() == null : this.getPayload().equals(other.getPayload()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getLock() == null ? other.getLock() == null : this.getLock().equals(other.getLock()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_SUB
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getPayload() == null) ? 0 : getPayload().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getLock() == null) ? 0 : getLock().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_SUB
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uuid=").append(uuid);
        sb.append(", type=").append(type);
        sb.append(", payload=").append(payload);
        sb.append(", status=").append(status);
        sb.append(", lock=").append(lock);
        sb.append("]");
        return sb.toString();
    }
}