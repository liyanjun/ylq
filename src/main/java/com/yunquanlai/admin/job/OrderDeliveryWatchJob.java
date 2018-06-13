package com.yunquanlai.admin.job;

import com.yunquanlai.admin.order.entity.OrderDeliveryInfoEntity;
import com.yunquanlai.admin.order.entity.OrderInfoEntity;
import com.yunquanlai.admin.order.service.OrderDeliveryInfoService;
import com.yunquanlai.admin.order.service.OrderInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于订单创建以后，未到配送时间，等待配送时间到以后分配
 *
 * @author liyanjun
 */
@Component
public class OrderDeliveryWatchJob {

    private static final Logger logger = LoggerFactory.getLogger(OrderDeliveryWatchJob.class);

    @Autowired
    OrderDeliveryInfoService orderDeliveryInfoService;

    @Autowired
    OrderInfoService orderInfoService;

    public void distributeOrder() {
        Map<String, Object> filter = new HashMap<>(16);
        filter.put("deliveryTime", OrderInfoEntity.STATUS_PAID);
        List<OrderDeliveryInfoEntity> orderDeliveryInfoEntities = orderDeliveryInfoService.queryList(filter);
        for (OrderDeliveryInfoEntity orderDeliveryInfoEntity : orderDeliveryInfoEntities) {
            try {
                orderDeliveryInfoService.distributeOrder(orderDeliveryInfoEntity.getId());
            } catch (Exception e) {
                logger.error("定时任务配送订单失败，配送订单ID【" + orderDeliveryInfoEntity.getId() + "】", e);
            }
        }
    }
}
