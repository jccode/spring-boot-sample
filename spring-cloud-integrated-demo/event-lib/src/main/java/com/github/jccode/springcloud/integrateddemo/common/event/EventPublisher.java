package com.github.jccode.springcloud.integrateddemo.common.event;


import com.github.jccode.springbootsample.core.utils.JsonUtil;
import com.github.jccode.springcloud.integrateddemo.common.event.model.EventPub;
import com.github.jccode.springcloud.integrateddemo.common.event.service.EventPubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;

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
 * 3. 定时器2 扫描状态为 Pending 的记录, 重新发布到 Kafka. (多服务器时,需要加乐观锁控制)
 *
 */
@Component
public class EventPublisher {

    @Autowired
    private EventPubService pubService;

    @Autowired
    private Publisher publisher;

    @Transactional(rollbackFor = Exception.class)
    public <T> int publish(String eventType, T payload) {
        EventPub event = new EventPub();
        event.setType(eventType);
        event.setPayload(JsonUtil.toJson(payload));
        event.setStatus(EventStatus.NEW.value());
        event.setUuid(UUID.randomUUID().toString());
        event.setLock(0);
        return pubService.save(event);
    }

    @Scheduled(fixedRate = 1000)
    private void publishNewStateRecords() {

    }

    @Scheduled(fixedRate = 5000)
    private void republishPendingStateRecords() {

    }

    /**
     * Publish event to Kafka
     *
     * @param event
     */
    private ListenableFuture<? extends SendResult<String, ?>> publish(Event event) {
        return publisher.send(EventConst.TOPIC, event.getType(), event);
    }
}
