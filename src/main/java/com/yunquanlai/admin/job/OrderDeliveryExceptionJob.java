package com.yunquanlai.admin.job;

import com.yunquanlai.admin.order.entity.OrderDeliveryInfoEntity;
import com.yunquanlai.admin.order.entity.OrderInfoEntity;
import com.yunquanlai.admin.order.service.OrderDeliveryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 处理开始配送后，1分钟未能分配成功标记为异常单
 *
 * @author liyanjun
 */
@Component
public class OrderDeliveryExceptionJob {

    @Autowired
    OrderDeliveryInfoService orderDeliveryInfoService;

    public void orderDeliveryException(){
        Map<String ,Object> filter = new HashMap<>(16);
        List<OrderDeliveryInfoEntity> orderDeliveryInfoEntities = orderDeliveryInfoService.queryList(filter);
        for (OrderDeliveryInfoEntity orderDeliveryInfoEntity: orderDeliveryInfoEntities) {
            orderDeliveryInfoService.distributorTimeOut(orderDeliveryInfoEntity);
        }
    }
}
