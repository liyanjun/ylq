package com.yunquanlai.api.comsumer.wechat.utils.weixin.config;

/**
 * @Description:
 * @Date: 2018/4/8
 * @Author: wcf
 */
public class WxPayConfig {
    /**
     * 微信小程序appid
     */
    public static final String appid = "wx39b1debf7c7b57f7";
    /**
     * 微信小程序密钥
     */
    public static final String secret = "b82a5f7d5d654106447a28a2efeb9f73";

    public static final String grant_type = "authorization_code";
    /**
     * 微信支付的商户id
     */
    public static final String mch_id = "1494681812";
    /**
     * 微信支付的商户密钥
     */
    public static final String key = "a9b80505a74e4c748daf576357b8f20a";
    /**
     * 支付成功后的服务器回调url
     */
    public static final String notify_url = "https://??/weixin/wxNotify";
    /**
     * 签名方式
     */
    public static final String SIGNTYPE = "MD5";
    /**
     * 交易类型
     */
    public static final String TRADETYPE = "JSAPI";
    /**
     * 微信统一下单接口地址
     */
    public static final String pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}
