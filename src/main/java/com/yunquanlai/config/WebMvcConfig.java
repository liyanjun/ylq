package com.yunquanlai.config;

import com.yunquanlai.utils.interceptor.AuthorizationInterceptor;
import com.yunquanlai.utils.interceptor.DeliveryAuthorizationInterceptor;
import com.yunquanlai.utils.resolver.LoginDeliveryHandlerMethodArgumentResolver;
import com.yunquanlai.utils.resolver.LoginUserHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * MVC配置
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-20 22:30
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Autowired
    private DeliveryAuthorizationInterceptor deliveryAuthorizationInterceptor;

    @Autowired
    private LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver;

    @Autowired
    private LoginDeliveryHandlerMethodArgumentResolver loginDeliveryHandlerMethodArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/client/api/**");
        registry.addInterceptor(deliveryAuthorizationInterceptor).addPathPatterns("/delivery/api/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserHandlerMethodArgumentResolver);
        argumentResolvers.add(loginDeliveryHandlerMethodArgumentResolver);
    }
}
