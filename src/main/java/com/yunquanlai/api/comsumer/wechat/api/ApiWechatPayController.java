package com.yunquanlai.api.comsumer.wechat.api;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.service.WxPayService;
import com.yunquanlai.admin.order.entity.OrderInfoEntity;
import com.yunquanlai.admin.order.service.OrderInfoService;
import com.yunquanlai.admin.system.service.SysConfigService;
import com.yunquanlai.api.comsumer.wechat.utils.IpUtils;
import com.yunquanlai.api.comsumer.wechat.utils.StringUtils;
import com.yunquanlai.api.comsumer.wechat.utils.PayUtil;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.RRException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Map;

/**
 * 微信支付Controller
 *
 * @author liyanjun
 */
@RestController
@RequestMapping("/client/api/wechat/pay")
@Api(value = "客户端-支付", description = "微信支付相关接口")
public class ApiWechatPayController {

    private Logger logger = LoggerFactory.getLogger(ApiWechatPayController.class);

    @Autowired
    OrderInfoService orderInfoService;

    @Autowired
    SysConfigService sysConfigService;

    @Autowired
    private WxPayService wxPayService;

    @Value("${wechat.pay.mchKey}")
    public String key;

    /**
     * 发起微信支付
     *
     * @param httpServletRequest
     * @param openid
     * @param orderId
     * @return
     */
    @PostMapping("wxPay")
    @ApiOperation(value = "发起微信支付")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", dataType = "string", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", name = "openid", dataType = "string", value = "用户唯一标识 openid", required = true),
            @ApiImplicitParam(paramType = "query", name = "orderId", dataType = "long", value = "需要支付的订单 ID", required = true)
    })
    public R wxPay(@RequestParam String openid, @RequestParam Long orderId, HttpServletRequest httpServletRequest) {

        WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
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
            //获取本机的ip地址 TODO 线上记得打开
            String spbill_create_ip = IpUtils.getIpAddr(httpServletRequest);

//            String spbill_create_ip = "119.23.247.12";

            String orderNo = orderInfoEntity.getId() + "";
            Integer money = orderInfoEntity.getAmount().multiply(BigDecimal.TEN).multiply(BigDecimal.TEN).intValue();
            //支付金额，单位：分，这边需要转成字符串类型，否则后面的签名会失败

            request.setNonceStr(nonce_str);
            request.setBody(body);

            //商户订单号
            request.setOutTradeNo(orderNo);
            //支付金额，这边需要转成字符串类型，否则后面的签名会失败
            request.setTotalFee(money);
            request.setSpbillCreateIp(spbill_create_ip);
            request.setNotifyUrl("https://www.yunquanlai.com/client/api/wechat/pay/wxNotify");
            request.setTradeType("JSAPI");
            request.setOpenid(openid);

            WxPayUnifiedOrderResult wxPayUnifiedOrderResult = wxPayService.unifiedOrder(request);

            return R.ok("发起微信支付成功").put("wxPayUnifiedOrderResult", wxPayUnifiedOrderResult);
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
                if (PayUtil.verify(PayUtil.createLinkString(map), (String) map.get("sign"), key, "utf-8")) {
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
