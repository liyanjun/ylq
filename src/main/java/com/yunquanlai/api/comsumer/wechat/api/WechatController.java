package com.yunquanlai.api.comsumer.wechat.api;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunquanlai.admin.order.entity.OrderInfoEntity;
import com.yunquanlai.admin.order.service.OrderInfoService;
import com.yunquanlai.admin.system.service.SysConfigService;
import com.yunquanlai.api.comsumer.wechat.utils.IpUtils;
import com.yunquanlai.api.comsumer.wechat.utils.StringUtils;
import com.yunquanlai.api.comsumer.wechat.utils.weixin.PayUtil;
import com.yunquanlai.api.comsumer.wechat.utils.weixin.config.WxPayConfig;
import com.yunquanlai.api.comsumer.wechat.utils.weixin.vo.OAuthJsToken;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.RRException;
import com.yunquanlai.utils.validator.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.weixin4j.WeixinException;
import org.weixin4j.WeixinSupport;
import org.weixin4j.http.HttpsClient;
import org.weixin4j.http.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付Controller
 *
 * @author liyanjun
 */
@RestController
@RequestMapping("/client/api/pay")
@Api(value = "客户端-支付", description = "微信支付相关接口")
public class WechatController extends WeixinSupport {

    private Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    OrderInfoService orderInfoService;

    @Autowired
    SysConfigService sysConfigService;

    /**
     * 小程序后台登录，向微信平台发送获取access_token请求，并返回openId
     *
     * @param code
     * @return
     * @throws WeixinException
     * @throws IOException
     */
    @PostMapping("login")
    @ApiOperation(value = "小程序登录，具体文档：https://developers.weixin.qq.com/miniprogram/dev/api/api-login.html")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", name = "code", value = "临时登录凭证code ", required = true),
    })
    public Map<String, Object> login(@RequestParam String code) throws WeixinException, IOException {
        Assert.isBlank(code, "code is null");

        Map<String, Object> ret = new HashMap<>();
        //拼接参数
        String param = "?grant_type=" + WxPayConfig.grant_type + "&appid=" + WxPayConfig.appid + "&secret=" + WxPayConfig.secret + "&js_code=" + code;

        //创建请求对象
        HttpsClient http = new HttpsClient();
        //调用获取access_token接口
        Response res = http.get("https://api.weixin.qq.com/sns/jscode2session" + param);
        //根据请求结果判定，是否验证成功
        JSONObject jsonObj = res.asJSONObject();
        if (jsonObj != null) {
            Object errcode = jsonObj.get("errcode");
            if (errcode != null) {
                //返回异常信息
                throw new RRException(getCause(Integer.parseInt(errcode.toString())));
            }

            ObjectMapper mapper = new ObjectMapper();
            OAuthJsToken oauthJsToken = mapper.readValue(jsonObj.toJSONString(), OAuthJsToken.class);

            ret.put("openid", oauthJsToken.getOpenid());
            ret.put("session_key", oauthJsToken.getSession_key());
        }
        return ret;
    }

    /**
     * 发起微信支付
     *
     * @param openid
     * @param orderId
     * @param request
     * @return
     */
    @PostMapping("wxPay")
    @ApiOperation(value = "发起微信支付")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", dataType = "string", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", name = "openid", dataType = "string", value = "用户唯一标识 openid", required = true),
            @ApiImplicitParam(paramType = "query", name = "orderId", dataType = "long", value = "需要支付的订单 ID", required = true)
    })
    public R wxPay(@RequestParam String openid, @RequestParam Long orderId, HttpServletRequest request) {

        OrderInfoEntity orderInfoEntity = orderInfoService.queryObject(orderId);
        if (orderInfoEntity.getStatus() == OrderInfoEntity.STATUS_CLOSE) {
            // 已关闭的订单不能发起请求，但是发起支付请求以后，关闭的订单，然后支付回调了，是要把订单从新拉起来到待分配状态的。
            throw new RRException("订单已关闭，不能发起支付请求");
        }
        try {
            //生成的随机字符串
            String nonce_str = StringUtils.getRandomStringByLength(32);
            //商品名称
            String body = "运泉来-" + orderInfoEntity.getId();
            //获取本机的ip地址
            String spbill_create_ip = IpUtils.getIpAddr(request);

            String orderNo = orderInfoEntity.getId() + "";
            String money = orderInfoEntity.getAmount().multiply(BigDecimal.TEN).multiply(BigDecimal.TEN).toString();
            //支付金额，单位：分，这边需要转成字符串类型，否则后面的签名会失败

            Map<String, String> packageParams = new HashMap<String, String>();
            packageParams.put("appid", WxPayConfig.appid);
            packageParams.put("mch_id", WxPayConfig.mch_id);
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("body", body);
            //商户订单号
            packageParams.put("out_trade_no", orderNo);
            //支付金额，这边需要转成字符串类型，否则后面的签名会失败
            packageParams.put("total_fee", money);
            packageParams.put("spbill_create_ip", spbill_create_ip);
            packageParams.put("notify_url", WxPayConfig.notify_url);
            packageParams.put("trade_type", WxPayConfig.TRADETYPE);
            packageParams.put("openid", openid);

            // 除去数组中的空值和签名参数
            packageParams = PayUtil.paraFilter(packageParams);
            // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
            String prestr = PayUtil.createLinkString(packageParams);

            //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
            String mysign = PayUtil.sign(prestr, WxPayConfig.key, "utf-8").toUpperCase();
            logger.info("=======================第一次签名：" + mysign + "=====================");

            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
            String xml = "<xml>" + "<appid>" + WxPayConfig.appid + "</appid>"
                    + "<body><![CDATA[" + body + "]]></body>"
                    + "<mch_id>" + WxPayConfig.mch_id + "</mch_id>"
                    + "<nonce_str>" + nonce_str + "</nonce_str>"
                    + "<notify_url>" + WxPayConfig.notify_url + "</notify_url>"
                    + "<openid>" + openid + "</openid>"
                    + "<out_trade_no>" + orderNo + "</out_trade_no>"
                    + "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
                    + "<total_fee>" + money + "</total_fee>"
                    + "<trade_type>" + WxPayConfig.TRADETYPE + "</trade_type>"
                    + "<sign>" + mysign + "</sign>"
                    + "</xml>";

            logger.debug("调试模式_统一下单接口 请求XML数据：" + xml);

            //调用统一下单接口，并接受返回的结果
            String result = PayUtil.httpRequest(WxPayConfig.pay_url, "POST", xml);

            logger.debug("调试模式_统一下单接口 返回XML数据：" + result);

            // 将解析结果存储在HashMap中
            Map map = PayUtil.doXMLParse(result);

            //返回状态码
            String return_code = (String) map.get("return_code");

            //返回给移动端需要的参数
            Map<String, Object> response = new HashMap<String, Object>();
            if (return_code == "SUCCESS" || return_code.equals(return_code)) {
                // 业务结果
                //返回的预付单信息
                String prepay_id = (String) map.get("prepay_id");
                response.put("nonceStr", nonce_str);
                response.put("package", "prepay_id=" + prepay_id);
                Long timeStamp = System.currentTimeMillis() / 1000;
                //这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
                response.put("timeStamp", timeStamp + "");

                String stringSignTemp = "appId=" + WxPayConfig.appid + "&nonceStr=" + nonce_str + "&package=prepay_id=" + prepay_id + "&signType=" + WxPayConfig.SIGNTYPE + "&timeStamp=" + timeStamp;
                //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
                String paySign = PayUtil.sign(stringSignTemp, WxPayConfig.key, "utf-8").toUpperCase();
                logger.info("=======================第二次签名：" + paySign + "=====================");

                response.put("paySign", paySign);

                //更新订单信息
                //业务逻辑代码
            }

            response.put("appid", WxPayConfig.appid);

            return R.ok("发起微信支付成功").put("response", response);
        } catch (Exception e) {
            logger.error("发起微信支付失败", e);
            return R.ok("发起微信支付失败：" + e.getMessage());
        }
    }

    /**
     * 微信支付，通知
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping(value = "/wxNotify")
    public void wxNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        //sb为微信返回的xml
        String notify = sb.toString();
        String resXml = "";
        logger.debug("接收到的报文：" + notify);

        Map map = PayUtil.doXMLParse(notify);

        String returnCode = (String) map.get("return_code");
        if ("SUCCESS".equals(returnCode)) {
            try {
                //验证签名是否正确
                if (PayUtil.verify(PayUtil.createLinkString(map), (String) map.get("sign"), WxPayConfig.key, "utf-8")) {
                    /**业务逻辑 start**/
                    orderInfoService.orderPay(map.get("out_trade_no"), map.get("total_fee"));
                    /**逻辑 end**/

                    //通知微信服务器已经支付成功
                    resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                            + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                }
            } catch (Exception e) {
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            }
        } else {
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        logger.debug(resXml);
        logger.debug("微信支付回调数据结束");

        BufferedOutputStream out = new BufferedOutputStream(
                response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }
}
