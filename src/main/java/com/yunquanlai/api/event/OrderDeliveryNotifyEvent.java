package com.yunquanlai.api.event;

import org.springframework.context.ApplicationEvent;

/**
 * 订单派送分配通知事件
 */
public class OrderDeliveryNotifyEvent extends ApplicationEvent {
    public OrderDeliveryNotifyEvent(Object source) {
        super(source);
    }
}
