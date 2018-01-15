package com.github.jccode.springbootkafkademo.jsondemo2.event;

import java.io.Serializable;
import java.util.UUID;

/**
 * Event
 *
 */
public class Event<T> implements Serializable {

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
    private T payload;

    public Event() {
    }

    public Event(String uuid, String type, T payload) {
        this.uuid = uuid;
        this.type = type;
        this.payload = payload;
    }

    public Event(String type, T payload) {
        this.uuid = UUID.randomUUID().toString();
        this.type = type;
        this.payload = payload;
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

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
