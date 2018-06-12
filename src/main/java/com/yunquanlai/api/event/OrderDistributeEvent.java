package com.yunquanlai.api.event;

import org.springframework.context.ApplicationEvent;

/**
 * 订单支付完成事件
 */
public class OrderDistributeEvent extends ApplicationEvent {
    public OrderDistributeEvent(Object source) {
        super(source);
    }
}
