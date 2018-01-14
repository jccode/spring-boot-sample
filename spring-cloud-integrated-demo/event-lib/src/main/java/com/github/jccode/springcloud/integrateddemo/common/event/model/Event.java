package com.github.jccode.springcloud.integrateddemo.common.event.model;

import java.io.Serializable;

public class Event implements Serializable {

    /**
     * 事件ID
     */
    private String uuid;

    /**
     * 事件的名称
     */
    private String type;

    /**
     * 数据
     */
    private String payload;

    public Event() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
