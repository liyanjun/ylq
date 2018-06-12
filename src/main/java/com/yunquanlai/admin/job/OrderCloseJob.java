package com.yunquanlai.admin.job;

import com.yunquanlai.admin.order.entity.OrderInfoEntity;
import com.yunquanlai.admin.order.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于订单创建以后，一分钟未能完成支付，关闭订单
 *
 * @author liyanjun
 */
@Component
public class OrderCloseJob {

    @Autowired
    OrderInfoService orderInfoService;

    public void closeOrder(){
        Map<String ,Object> filter = new HashMap<>(16);
        List<OrderInfoEntity> orderInfoEntityList = orderInfoService.queryList(filter);
        for (OrderInfoEntity orderInfoEntity: orderInfoEntityList) {
            orderInfoService.payTimeOut(orderInfoEntity);
        }
    }
}
