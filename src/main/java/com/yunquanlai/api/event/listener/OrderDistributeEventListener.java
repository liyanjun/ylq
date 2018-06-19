package com.yunquanlai.api.event.listener;

import com.yunquanlai.admin.delivery.entity.DeliveryEndpointEntity;
import com.yunquanlai.admin.delivery.service.DeliveryEndpointService;
import com.yunquanlai.admin.order.entity.OrderDeliveryInfoEntity;
import com.yunquanlai.admin.order.entity.OrderProductDetailEntity;
import com.yunquanlai.admin.order.service.OrderDeliveryInfoService;
import com.yunquanlai.admin.order.service.OrderInfoService;
import com.yunquanlai.admin.order.service.OrderProductDetailService;
import com.yunquanlai.api.event.OrderDistributeEvent;
import com.yunquanlai.utils.DeliveryDistanceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 监听支付完成事件
 */
@Component
public class OrderDistributeEventListener implements ApplicationListener<OrderDistributeEvent> {
    Logger logger = LoggerFactory.getLogger(OrderDistributeEventListener.class);
    @Autowired
    OrderInfoService orderInfoService;

    @Autowired
    OrderDeliveryInfoService orderDeliveryInfoService;

    @Autowired
    OrderProductDetailService orderProductDetailService;

    @Autowired
    DeliveryEndpointService deliveryEndpointService;

    @Async
    @Override
    public void onApplicationEvent(OrderDistributeEvent applicationEvent) {
        Long orderId = Long.parseLong(applicationEvent.getSource().toString());
        OrderDeliveryInfoEntity orderDeliveryInfoEntity = null;
        try {
            orderDeliveryInfoEntity = orderDeliveryInfoService.queryObjectByOrderId(orderId);
            if (orderDeliveryInfoEntity == null) {
                logger.error("找不到对应的派送单，订单编号【" + orderId + "】");
                return;
            }

            if (OrderDeliveryInfoEntity.STATUS_UN_DISTRIBUTE != orderDeliveryInfoEntity.getStatus()) {
                logger.error("配送单" + orderDeliveryInfoEntity.getId() + "，不是可以分配派送的状态【" + orderDeliveryInfoEntity.getStatus() + "】");
                return;
            }

            // 找出订单购买的商品，便于计算库存
            List<OrderProductDetailEntity> orderProductDetailEntities = orderProductDetailService.queryListByOrderId(orderDeliveryInfoEntity.getOrderInfoId());
            // 找出所有配送点
            List<DeliveryEndpointEntity> deliveryEndpointEntities = deliveryEndpointService.queryList(null);
            DeliveryDistanceUtils.sortDeliveryEndpoint(orderDeliveryInfoEntity.getLocationX(),orderDeliveryInfoEntity.getLocationY(),deliveryEndpointEntities);
            for (DeliveryEndpointEntity deliveryEndpointEntity : deliveryEndpointEntities) {
                try {
                    orderInfoService.findDeliveryDistributor(orderProductDetailEntities, orderDeliveryInfoEntity, deliveryEndpointEntity);
                    // 分配完成方法结束
                    return;
                } catch (Exception e) {
                    logger.error("配送点分配配送单失败",e);
                    // 该配送点分配失败，抛出异常回滚事务，continue进行下一个配送点的检查
                    continue;
                }
            }

            orderDeliveryInfoService.markerException(orderDeliveryInfoEntity,"找不到有库存并且有可配送人员的配送点");
        } catch (Exception e) {
            logger.error("订单派送异常", e);
            if (orderDeliveryInfoEntity != null) {
                orderDeliveryInfoService.markerException(orderDeliveryInfoEntity,e.getMessage());
            }
        }
    }

}
