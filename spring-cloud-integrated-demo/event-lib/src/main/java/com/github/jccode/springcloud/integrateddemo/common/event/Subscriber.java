package com.github.jccode.springcloud.integrateddemo.common.event;

/**
 * Subscriber 消息订阅
 *
 * 要点:
 * 1. 这里主要是需要对 msgId 做防重复消费处理.
 *    - 先对消息落地,状态为 New.
 *    - 业务处理后,状态更新为 DONE.
 *    - 如果消息处理时,发现此消息ID已存在,则不进行业务处理
 *
 * 2. 自定义一个事件处理器的注解. 可以针对事件类型(type)进行处理?
 *
 */
public class Subscriber {
}
