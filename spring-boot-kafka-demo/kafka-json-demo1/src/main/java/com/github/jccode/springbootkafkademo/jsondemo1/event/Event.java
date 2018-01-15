package com.github.jccode.springbootkafkademo.jsondemo1.event;

import java.io.Serializable;
import java.util.UUID;

/**
 * Event
 *
 * 这里不能加泛式,JsonDeserlizer反序列化时,要求精确匹配到泛式中的类型形式. 会导致kafkaTemplate注入失败.
 */
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
    private Object payload;

    public Event() {
    }

    public Event(String uuid, String type, Object payload) {
        this.uuid = uuid;
        this.type = type;
        this.payload = payload;
    }

    public Event(String type, Object payload) {
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

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
