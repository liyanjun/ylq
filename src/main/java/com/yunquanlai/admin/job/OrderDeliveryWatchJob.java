package com.yunquanlai.admin.job;

import com.yunquanlai.admin.order.entity.OrderInfoEntity;
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
    OrderInfoService orderInfoService;

    public void distributeOrder() {
        Map<String, Object> filter = new HashMap<>(16);
        // TODO 条件设置
        filter.put("status", OrderInfoEntity.STATUS_PAID);
        List<OrderInfoEntity> orderInfoEntityList = orderInfoService.queryList(filter);
        for (OrderInfoEntity orderInfoEntity : orderInfoEntityList) {
            try {
                orderInfoService.distributeOrder(orderInfoEntity);
            } catch (Exception e) {
                logger.error("定时任务关闭订单失败，订单ID【" + orderInfoEntity.getId() + "】", e);
            }
        }
    }
}
