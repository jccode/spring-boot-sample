package com.github.jccode.springcloud.integrateddemo.common.event;


import org.springframework.stereotype.Component;

/**
 * Event Publisher
 *
 * 要点:
 *
 * 1. 需要先将事件持久化到DB, 待Kafka发送成功后, 在回调中更新发送状态为成功.
 *
 * 逻辑:
 * 1. 先将消息落地,写库;
 * 2. 定时器1 扫描状态为 New 的记录, 发布到 Kafka;
 * 3. 定时器2 扫描状态为 Pending 的记录, 重新发布到 Kafka.
 *
 */
@Component
public class Publisher {

    
}
