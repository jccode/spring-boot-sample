package com.github.jccode.springcloud.integrateddemo.common.event;

import java.io.Serializable;

/**
 * 领域事件.
 *
 * 当将事件进行持久化时, 需要一个持久化对象与之对应.
 * (持久化对象中的payload必须转为字符串进行存储, 另外还需要一个事件状态, 表示发送成功/失败.)
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
