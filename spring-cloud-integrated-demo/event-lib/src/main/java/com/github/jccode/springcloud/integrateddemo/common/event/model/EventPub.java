package com.github.jccode.springcloud.integrateddemo.common.event.model;

import com.github.jccode.springbootsample.core.model.BaseEntity;
import java.util.Date;

public class EventPub extends BaseEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EVENT_PUB.ID
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EVENT_PUB.UUID
     *
     * @mbg.generated
     */
    private String uuid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EVENT_PUB.TYPE
     *
     * @mbg.generated
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EVENT_PUB.PAYLOAD
     *
     * @mbg.generated
     */
    private String payload;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EVENT_PUB.STATUS
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EVENT_PUB.CREATE_TIME
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column EVENT_PUB.UPDATE_TIME
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EVENT_PUB.ID
     *
     * @return the value of EVENT_PUB.ID
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_PUB
     *
     * @mbg.generated
     */
    public EventPub withId(Integer id) {
        this.setId(id);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EVENT_PUB.ID
     *
     * @param id the value for EVENT_PUB.ID
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EVENT_PUB.UUID
     *
     * @return the value of EVENT_PUB.UUID
     *
     * @mbg.generated
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_PUB
     *
     * @mbg.generated
     */
    public EventPub withUuid(String uuid) {
        this.setUuid(uuid);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EVENT_PUB.UUID
     *
     * @param uuid the value for EVENT_PUB.UUID
     *
     * @mbg.generated
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EVENT_PUB.TYPE
     *
     * @return the value of EVENT_PUB.TYPE
     *
     * @mbg.generated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_PUB
     *
     * @mbg.generated
     */
    public EventPub withType(String type) {
        this.setType(type);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EVENT_PUB.TYPE
     *
     * @param type the value for EVENT_PUB.TYPE
     *
     * @mbg.generated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EVENT_PUB.PAYLOAD
     *
     * @return the value of EVENT_PUB.PAYLOAD
     *
     * @mbg.generated
     */
    public String getPayload() {
        return payload;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_PUB
     *
     * @mbg.generated
     */
    public EventPub withPayload(String payload) {
        this.setPayload(payload);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EVENT_PUB.PAYLOAD
     *
     * @param payload the value for EVENT_PUB.PAYLOAD
     *
     * @mbg.generated
     */
    public void setPayload(String payload) {
        this.payload = payload == null ? null : payload.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EVENT_PUB.STATUS
     *
     * @return the value of EVENT_PUB.STATUS
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_PUB
     *
     * @mbg.generated
     */
    public EventPub withStatus(Integer status) {
        this.setStatus(status);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EVENT_PUB.STATUS
     *
     * @param status the value for EVENT_PUB.STATUS
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EVENT_PUB.CREATE_TIME
     *
     * @return the value of EVENT_PUB.CREATE_TIME
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_PUB
     *
     * @mbg.generated
     */
    public EventPub withCreateTime(Date createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EVENT_PUB.CREATE_TIME
     *
     * @param createTime the value for EVENT_PUB.CREATE_TIME
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column EVENT_PUB.UPDATE_TIME
     *
     * @return the value of EVENT_PUB.UPDATE_TIME
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_PUB
     *
     * @mbg.generated
     */
    public EventPub withUpdateTime(Date updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column EVENT_PUB.UPDATE_TIME
     *
     * @param updateTime the value for EVENT_PUB.UPDATE_TIME
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_PUB
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
        EventPub other = (EventPub) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getPayload() == null ? other.getPayload() == null : this.getPayload().equals(other.getPayload()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_PUB
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
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_PUB
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uuid=").append(uuid);
        sb.append(", type=").append(type);
        sb.append(", payload=").append(payload);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}