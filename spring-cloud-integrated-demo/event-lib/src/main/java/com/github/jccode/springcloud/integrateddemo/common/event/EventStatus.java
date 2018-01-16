package com.github.jccode.springcloud.integrateddemo.common.event;

public enum EventStatus {

    /**
     * 0
     */
    NEW(0),

    /**
     * 1
     */
    PENDING(1),

    /**
     * 2
     */
    DONE(2);


    private final int status;

    EventStatus(int status) {
        this.status = status;
    }

    public int value() {
        return status;
    }
}
