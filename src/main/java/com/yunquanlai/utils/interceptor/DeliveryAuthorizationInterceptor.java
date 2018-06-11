package com.yunquanlai.utils.interceptor;

import com.yunquanlai.admin.delivery.entity.DeliveryClientTokenEntity;
import com.yunquanlai.admin.delivery.service.DeliveryClientTokenService;
import com.yunquanlai.admin.user.entity.UserClientTokenEntity;
import com.yunquanlai.admin.user.service.UserClientTokenService;
import com.yunquanlai.utils.RRException;
import com.yunquanlai.utils.annotation.IgnoreAuth;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 配送员权限(Token)验证
 *
 * @author liyanjun
 */
@Component
public class DeliveryAuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private DeliveryClientTokenService deliveryClientTokenService;

    public static final String LOGIN_DELIVERY_KEY = "LOGIN_DELIVERY_KEY";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        IgnoreAuth annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAuth.class);
        } else {
            return true;
        }

        //如果有@IgnoreAuth注解，则不验证token
        if (annotation != null) {
            return true;
        }

        //从header中获取token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = request.getParameter("token");
        }

        //token为空
        if (StringUtils.isBlank(token)) {
            throw new RRException("token不能为空", 501);
        }

        DeliveryClientTokenEntity tokenEntity = deliveryClientTokenService.queryByToken(token);
        if (tokenEntity == null) {
            throw new RRException("token失效，请重新登录", 502);
        }

        if (tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
            throw new RRException("token过期，请重新登录", 503);
        }

        //设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(LOGIN_DELIVERY_KEY, tokenEntity.getDeliveryDistributorId());

        return true;
    }
}
