package com.yunquanlai.api.comsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yunquanlai.admin.delivery.entity.DeliveryEndpointEntity;
import com.yunquanlai.admin.delivery.service.DeliveryEndpointService;
import com.yunquanlai.admin.order.entity.OrderDeliveryInfoEntity;
import com.yunquanlai.admin.order.entity.OrderInfoEntity;
import com.yunquanlai.admin.order.service.OrderDeliveryInfoService;
import com.yunquanlai.admin.order.service.OrderInfoService;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.api.comsumer.vo.OrderVO;
import com.yunquanlai.utils.DeliveryDistanceUtils;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.TokenUtils;
import com.yunquanlai.utils.annotation.LoginUser;
import com.yunquanlai.utils.validator.Assert;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weicc
 * @date 2018/5/30 21:36
 * @desc
 **/
@RestController
@RequestMapping("/client/api")
@Api(value = "客户端-订单", description = "订单相关接口")
public class ApiOrderController {

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private OrderDeliveryInfoService orderDeliveryInfoService;

    @Autowired
    private DeliveryEndpointService deliveryEndpointService;

    @Autowired
    private TokenUtils tokenUtils;

    /**
     * 用户订单查询
     *
     * @param status
     * @return
     */
    @PostMapping("queryOrder")
    @ApiOperation(value = "用户订单查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "status", value = "订单状态"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "offset", value = "位移数", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "limit", value = "查询条数", required = true)
    })
    public R queryOrder(@LoginUser @ApiIgnore UserInfoEntity user, Integer status, @RequestParam Integer offset, @RequestParam Integer limit) {
        Map filter = new HashMap(16);
        filter.put("userInfoId", user.getId());
        filter.put("status", status);
        filter.put("offset", offset);
        filter.put("limit", limit);
        List<OrderInfoEntity> orderInfoEntities = orderInfoService.queryListClient(filter);
        return R.ok().put("orderInfoList", orderInfoEntities);
    }

    /**
     * 用户订单详情查询
     *
     * @param orderId
     * @return
     */
    @PostMapping("queryOrderDetail")
    @ApiOperation(value = "用户订单详情查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "long", name = "orderId", value = "订单 ID", required = true)
    })
    public R queryOrderDetail(@LoginUser @ApiIgnore UserInfoEntity user, Long orderId) {
        OrderInfoEntity orderInfoEntity = orderInfoService.queryObject(orderId);
        Assert.isNull(orderInfoEntity, "找不到订单");
        Assert.isNotEqual(orderInfoEntity.getUserInfoId(), user.getId(), "不能查询别人的订单详情");
        OrderDeliveryInfoEntity orderDeliveryInfoEntity = orderDeliveryInfoService.queryObjectByOrderId(orderId);
        return R.ok().put("orderInfo", orderInfoEntity).put("orderDeliveryInfo", orderDeliveryInfoEntity);
    }


    /**
     * 确认订单信息
     *
     * @return
     */
    @PostMapping("orderConfirm")
    @ApiOperation(value = "确认订单信息（从购物车跳转到详情页时使用），" +
            "orderVO样例{ \"productOrderVOList\":[ {\"productInfoId\": 10001, \"count\": 2}, {\"productInfoId\": 10003, \"count\": 1} ] }")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "token", required = true),
            @ApiImplicitParam(name = "orderVO", value = "订单信息", required = true, dataType = "com.yunquanlai.api.comsumer.vo.OrderVO", paramType = "body")
    })
    public R orderConfirm(@RequestBody OrderVO orderVO, @LoginUser @ApiIgnore UserInfoEntity user) {
        return orderInfoService.confirm(orderVO, user);
    }


    /**
     * 下单
     *
     * @return
     */
    @PostMapping("order")
    @ApiOperation(value = "下单orderVO样例{ \"orderToken\": \"xxxxx\",\"address\": \"南宁市幸福里\", \"name\": \"李有钱\", \"sex\": 10, \"locationX\": 22.156487, \"locationY\": 23.458798, \"phone\": \"15677188594\", \"remark\": \"水里多放辣椒\", \"deliveryTime\": null, \"productOrderVOList\":[ {\"productInfoId\": 10001, \"count\": 2}, {\"productInfoId\": 10002, \"count\": 1} ] }")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "token", required = true),
            @ApiImplicitParam(name = "orderVO", value = "订单信息", required = true, dataType = "com.yunquanlai.api.comsumer.vo.OrderVO", paramType = "body")
    })
    public R order(@RequestBody OrderVO orderVO, @LoginUser @ApiIgnore UserInfoEntity user) throws ParseException, JsonProcessingException {
        if (!tokenUtils.isExitToken(orderVO.getOrderToken())) {
            return R.error("订单确认已失效，请重新下单。");
        }
        // TODO 先注释
//        if (!availableDelivery(orderVO.getLocationX(), orderVO.getLocationY())) {
//            return R.error("订单不在派送范围内，请联系客服确认。");
//        }
        return orderInfoService.newOrder(orderVO, user);
    }


    /**
     * 关闭订单
     *
     * @param orderId
     * @param user
     * @return
     */
    @PostMapping("orderClose")
    @ApiOperation(value = "关闭订单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "token", required = true),
            @ApiImplicitParam(name = "orderId", value = "订单 ID", dataType = "long", paramType = "query", required = true)
    })
    public R orderClose(@RequestParam Long orderId, @LoginUser @ApiIgnore UserInfoEntity user) {
        orderInfoService.closeOrder(orderId, user.getId());
        return R.ok().put("orderId", orderId);
    }

    /**
     * 是否符合配送范围
     *
     * @param locationX：X坐标
     * @param locationY：Y坐标
     * @return true:配送范围内，false:距离太远，不可配送
     */
    public boolean availableDelivery(BigDecimal locationX, BigDecimal locationY) {
        //当前坐标
        double curX = locationX.doubleValue();
        double curY = locationY.doubleValue();
        //目标坐标
        double desX, desY;
        // 找出所有配送点
        List<DeliveryEndpointEntity> deliveryEndpointEntities = deliveryEndpointService.queryList(null);
        DeliveryDistanceUtils.sortDeliveryEndpoint(locationX, locationY, deliveryEndpointEntities);
        //获取距离最短的配送点
        DeliveryEndpointEntity deliveryEndpointEntity = deliveryEndpointEntities.get(0);
        desX = deliveryEndpointEntity.getLocationX().doubleValue();
        desY = deliveryEndpointEntity.getLocationY().doubleValue();
        double distance = DeliveryDistanceUtils.getDistance(curX, curY, desX, desY);
        //配送范围：6公里
        //TODO 6公里似乎太长了，从西大到火炬大厦都配送，如果从朝阳配送点送出怕是要死，需要和甲方讨论
        if (distance > 0 && distance <= 6) {
            return true;
        } else {
            return false;
        }
    }


}
