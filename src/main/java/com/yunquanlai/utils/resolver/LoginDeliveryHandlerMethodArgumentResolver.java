package com.yunquanlai.utils.resolver;

import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.admin.user.service.UserInfoService;
import com.yunquanlai.utils.annotation.LoginDelivery;
import com.yunquanlai.utils.annotation.LoginUser;
import com.yunquanlai.utils.interceptor.AuthorizationInterceptor;
import com.yunquanlai.utils.interceptor.DeliveryAuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 有@LoginDelivery注解的方法参数，注入当前登录用户
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 22:02
 */
@Component
public class LoginDeliveryHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private UserInfoService userInfoService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(UserInfoEntity.class) && parameter.hasParameterAnnotation(LoginDelivery.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        //获取配送员ID
        Object object = request.getAttribute(DeliveryAuthorizationInterceptor.LOGIN_DELIVERY_KEY, RequestAttributes.SCOPE_REQUEST);
        if (object == null) {
            return null;
        }

        //TODO 获取配送员信息
//        UserInfoEntity user = userInfoService.queryObject((Long)object);

        return null;
    }
}
