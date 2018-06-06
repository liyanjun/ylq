package com.yunquanlai.api.event;

import org.springframework.context.ApplicationEvent;

/**
 * 订单支付完成事件
 */
public class OrderPaidEvent extends ApplicationEvent {
    public OrderPaidEvent(Object source) {
        super(source);
    }
}
