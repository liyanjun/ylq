package com.yunquanlai.api.event.listener;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.*;
import com.gexin.rp.sdk.template.style.Style0;
import com.yunquanlai.admin.delivery.entity.DeliveryDistributorEntity;
import com.yunquanlai.admin.delivery.service.DeliveryDistributorService;
import com.yunquanlai.admin.order.entity.OrderDeliveryInfoEntity;
import com.yunquanlai.admin.order.entity.OrderInfoEntity;
import com.yunquanlai.admin.order.service.OrderDeliveryInfoService;
import com.yunquanlai.admin.order.service.OrderInfoService;
import com.yunquanlai.api.event.OrderDeliveryNotifyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 监听订单派送通知事件
 */
@Component
public class OrderDeliveryNotifyEventListener implements ApplicationListener<OrderDeliveryNotifyEvent> {
    Logger logger = LoggerFactory.getLogger(OrderDeliveryNotifyEventListener.class);
    private static String appId = "0akI3PR6H8A3Izo1upRh04";
    private static String appKey = "mwvxOxyxk89WiO612cGqY5";
    private static String masterSecret = "ZL0K1tV5y87fix2tpIgHg2";
    static String host = "http://sdk.open.api.igexin.com/apiex.htm";

    @Autowired
    private DeliveryDistributorService deliveryDistributorService;

    @Autowired
    private OrderDeliveryInfoService orderDeliveryInfoService;

    @Autowired
    private OrderInfoService orderInfoService;

    @Async
    @Override
    public void onApplicationEvent(OrderDeliveryNotifyEvent applicationEvent) {
        Long orderDeliveryId = Long.parseLong(applicationEvent.getSource().toString());
        OrderDeliveryInfoEntity orderDeliveryInfoEntity = orderDeliveryInfoService.queryObject(orderDeliveryId);
        DeliveryDistributorEntity deliveryDistributorEntity = deliveryDistributorService.queryObject(orderDeliveryInfoEntity.getDeliveryDistributorId());
        IGtPush push = new IGtPush(host, appKey, masterSecret);
        AbstractTemplate template;
        if (deliveryDistributorEntity.getPlatform() == 10) {
            template = getNotificationTemplate(orderDeliveryId);
        } else {
            template = getTransmissionTemplate(orderDeliveryInfoEntity.getId() + "");
        }

        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0);
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(deliveryDistributorEntity.getClientId());
        //target.setAlias(Alias);
        IPushResult ret;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            logger.error("推送请求发送异常", e);
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }

        if (ret != null) {
            if (!"ok".equals(ret.getResponse().get("result"))) {
                OrderInfoEntity orderInfoEntity = orderInfoService.queryObject(orderDeliveryInfoEntity.getOrderInfoId());
                orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_EXCEPTION);
                orderDeliveryInfoEntity.setRemark("推送订单异常：" + ret.getResponse().toString());
                orderInfoEntity.setType(OrderInfoEntity.TYPE_EXCEPTION);
                orderInfoEntity.setException("推送订单异常：" + ret.getResponse().toString());
                orderInfoService.markException(orderInfoEntity, orderDeliveryInfoEntity);
            }
            logger.debug(ret.getResponse().toString());
        } else {
            OrderInfoEntity orderInfoEntity = orderInfoService.queryObject(orderDeliveryInfoEntity.getOrderInfoId());
            orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_EXCEPTION);
            orderDeliveryInfoEntity.setRemark("推送订单异常：" + "推送请求发送异常");
            orderInfoEntity.setType(OrderInfoEntity.TYPE_EXCEPTION);
            orderInfoEntity.setException("推送订单异常：" + "推送请求发送异常");
            orderInfoService.markException(orderInfoEntity, orderDeliveryInfoEntity);
            logger.error("推送请求发送异常");
        }

    }


    /**
     * 安卓用
     *
     * @return
     */
    public static NotificationTemplate getNotificationTemplate(Long orderDeliveryId) {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);
        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle("您有新的配送任务");
        style.setText("{\"orderDeliveryId\":\"" + orderDeliveryId + "\",\"content\":\"您有新的配送任务,请注意查看\"}");
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);
        return template;
    }

    /**
     * 苹果用
     *
     * @return
     */
    public static TransmissionTemplate getTransmissionTemplate(String orderDeliveryId) {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTransmissionType(2);
        template.setTransmissionContent("{\"oid\":" + orderDeliveryId + "}");
        APNPayload apnPayload = new APNPayload();
        apnPayload.setAutoBadge("+1");
        apnPayload.setContentAvailable(1);
        apnPayload.setSound("default");
        apnPayload.setAlertMsg(new APNPayload.SimpleAlertMsg("您有新的配送任务,请注意查看。"));
        template.setAPNInfo(apnPayload);
        return template;
    }
}
