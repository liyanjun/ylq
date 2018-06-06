package com.yunquanlai.api.event.listener;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import com.yunquanlai.api.event.OrderDeliveryNotifyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 监听订单派送通知事件
 *
 */
@Component
public class OrderDeliveryNotifyEventListener implements ApplicationListener<OrderDeliveryNotifyEvent> {
    Logger logger = LoggerFactory.getLogger(OrderDeliveryNotifyEventListener.class);
    private static String appId = "0akI3PR6H8A3Izo1upRh04";
    private static String appKey = "mwvxOxyxk89WiO612cGqY5";
    private static String masterSecret = "ZL0K1tV5y87fix2tpIgHg2";
    static String host = "http://sdk.open.api.igexin.com/apiex.htm";
    @Override
    public void onApplicationEvent(OrderDeliveryNotifyEvent applicationEvent) {
        IGtPush push = new IGtPush(host, appKey, masterSecret);
        NotificationTemplate template = getNotificationTemplate();
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0);
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(applicationEvent.getSource().toString());
        //target.setAlias(Alias);
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            logger.error("推送请求发送异常",e);
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        if (ret != null) {
            logger.debug(ret.getResponse().toString());
        } else {
            logger.error("推送请求发送异常");
        }

    }

    

    public static NotificationTemplate getNotificationTemplate() {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);
        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle("请输入通知栏标题");
        style.setText("请输入通知栏内容");
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
}
