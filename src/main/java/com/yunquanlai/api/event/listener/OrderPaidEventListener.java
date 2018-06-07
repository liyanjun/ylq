package com.yunquanlai.api.event.listener;

import com.yunquanlai.admin.delivery.entity.DeliveryEndpointEntity;
import com.yunquanlai.admin.delivery.service.DeliveryEndpointService;
import com.yunquanlai.admin.order.entity.OrderDeliveryInfoEntity;
import com.yunquanlai.admin.order.entity.OrderProductDetailEntity;
import com.yunquanlai.admin.order.service.OrderDeliveryInfoService;
import com.yunquanlai.admin.order.service.OrderInfoService;
import com.yunquanlai.admin.order.service.OrderProductDetailService;
import com.yunquanlai.api.event.OrderPaidEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * 监听支付完成事件
 */
@Component
public class OrderPaidEventListener implements ApplicationListener<OrderPaidEvent> {
    Logger logger = LoggerFactory.getLogger(OrderPaidEventListener.class);
    @Autowired
    OrderInfoService orderInfoService;

    @Autowired
    OrderDeliveryInfoService orderDeliveryInfoService;

    @Autowired
    OrderProductDetailService orderProductDetailService;

    @Autowired
    DeliveryEndpointService deliveryEndpointService;

    @Override
    public void onApplicationEvent(OrderPaidEvent applicationEvent) {
        Long orderId = Long.parseLong(applicationEvent.getSource().toString());
        OrderDeliveryInfoEntity orderDeliveryInfoEntity = null;
        try {
            orderDeliveryInfoEntity = orderDeliveryInfoService.queryObjectByOrderId(orderId);
            if (orderDeliveryInfoEntity == null) {
                logger.error("找不到对应的派送单，订单编号【" + orderId + "】");
                return;
            }

            if (OrderDeliveryInfoEntity.STATUS_UN_DISTRIBUTE != orderDeliveryInfoEntity.getStatus()) {
                logger.error("配送单" + orderDeliveryInfoEntity.getId() + "已处理派送【" + orderDeliveryInfoEntity.getStatus() + "】");
                return;
            }

            // 找出订单购买的商品，便于计算库存
            List<OrderProductDetailEntity> orderProductDetailEntities = orderProductDetailService.queryListByOrderId(orderDeliveryInfoEntity.getOrderInfoId());
            // 找出所有配送点
            List<DeliveryEndpointEntity> deliveryEndpointEntities = deliveryEndpointService.queryList(null);
            for (DeliveryEndpointEntity deliveryEndpointEntity : deliveryEndpointEntities) {
                // 求出x,y的差值的绝对值，即为距离
                BigDecimal x = orderDeliveryInfoEntity.getLocationX().subtract(deliveryEndpointEntity.getLocationX()).abs();
                BigDecimal y = orderDeliveryInfoEntity.getLocationY().subtract(deliveryEndpointEntity.getLocationY()).abs();
                BigDecimal distance = x.pow(2).add(y.pow(2));
                // 不用开方，因为开方了对比大小还是一样的。
                deliveryEndpointEntity.setDistance(distance);
            }
            // 按照距离排序
            Collections.sort(deliveryEndpointEntities);
            //TODO 先更新为分配中，当一分钟分配不完时，用定时任务做补偿
            orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_ON_DELIVERY);
            orderDeliveryInfoService.update(orderDeliveryInfoEntity);
            for (DeliveryEndpointEntity deliveryEndpointEntity : deliveryEndpointEntities) {
                try {
                    orderInfoService.findDeliveryDistributor(orderProductDetailEntities, orderDeliveryInfoEntity, deliveryEndpointEntity);
                    // 分配完成方法结束
                    return;
                } catch (Exception e) {
                    // 该配送点分配失败，抛出异常回滚事务，continue进行下一个配送点的检查
                    continue;
                }
            }

            // TODO 全部都找不到，标记异常,记录异常原因，并单独字段标记异常
            orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_EXCEPTION);
            orderDeliveryInfoService.update(orderDeliveryInfoEntity);
        } catch (Exception e) {
            logger.error("订单派送异常", e);
            if (orderDeliveryInfoEntity != null) {
                orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_EXCEPTION);
                orderDeliveryInfoService.update(orderDeliveryInfoEntity);
            }
        }
    }
}
