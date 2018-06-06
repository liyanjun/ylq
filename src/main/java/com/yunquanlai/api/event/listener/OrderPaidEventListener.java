package com.yunquanlai.api.event.listener;

import com.yunquanlai.admin.order.service.OrderInfoService;
import com.yunquanlai.api.event.OrderPaidEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 监听支付完成事件
 */
@Component
public class OrderPaidEventListener implements ApplicationListener<OrderPaidEvent> {
    @Autowired
    OrderInfoService orderInfoService;

    @Override
    public void onApplicationEvent(OrderPaidEvent applicationEvent) {
        orderInfoService.orderDelivery(applicationEvent.getSource());
    }
}
