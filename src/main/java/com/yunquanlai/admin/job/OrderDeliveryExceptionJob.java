package com.yunquanlai.admin.job;

import com.yunquanlai.admin.order.entity.OrderDeliveryInfoEntity;
import com.yunquanlai.admin.order.service.OrderDeliveryInfoService;
import com.yunquanlai.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 开始配送后，1分钟未能分配成功标记为异常单
 *
 * @author liyanjun
 */
@Component
public class OrderDeliveryExceptionJob {

    private static final Logger logger = LoggerFactory.getLogger(OrderDeliveryExceptionJob.class);

    @Autowired
    OrderDeliveryInfoService orderDeliveryInfoService;

    public void orderDeliveryException() {
        Map<String, Object> filter = new HashMap<>(16);
        filter.put("status",OrderDeliveryInfoEntity.STATUS_PAID);
        filter.put("distributeTime",DateUtils.localDateTimeToDate(LocalDateTime.now().plusMinutes(-1)));
        List<OrderDeliveryInfoEntity> orderDeliveryInfoEntities = orderDeliveryInfoService.queryList(filter);
        for (OrderDeliveryInfoEntity orderDeliveryInfoEntity : orderDeliveryInfoEntities) {
            try {
                orderDeliveryInfoService.distributorTimeOut(orderDeliveryInfoEntity);
            } catch (Exception e) {
                logger.error("定时任务标记配送单分配超时失败，配送单ID【" + orderDeliveryInfoEntity.getId() + "】", e);
            }

        }
    }
}
