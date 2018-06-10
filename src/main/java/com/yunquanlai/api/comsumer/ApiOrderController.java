package com.yunquanlai.api.comsumer;

import com.yunquanlai.admin.order.entity.OrderDeliveryInfoEntity;
import com.yunquanlai.admin.order.entity.OrderInfoEntity;
import com.yunquanlai.admin.order.service.OrderDeliveryInfoService;
import com.yunquanlai.admin.order.service.OrderInfoService;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.api.comsumer.vo.OrderVO;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.annotation.LoginUser;
import com.yunquanlai.utils.validator.Assert;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
    OrderInfoService orderInfoService;

    @Autowired
    OrderDeliveryInfoService orderDeliveryInfoService;

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
        List<OrderInfoEntity> orderInfoEntities = orderInfoService.queryList(filter);
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
    @ApiOperation(value = "下单orderVO样例{ \"address\": \"南宁市幸福里\", \"name\": \"李有钱\", \"sex\": 10, \"locationX\": 22.156487, \"locationY\": 23.458798, \"phone\": \"15677188594\", \"remark\": \"水里多放辣椒\", \"deliveryTime\": null, \"productOrderVOList\":[ {\"productInfoId\": 10001, \"count\": 2}, {\"productInfoId\": 10003, \"count\": 1} ] }")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "token", required = true),
            @ApiImplicitParam(name = "orderVO", value = "订单信息", required = true, dataType = "com.yunquanlai.api.comsumer.vo.OrderVO", paramType = "body")
    })
    public R order(@RequestBody OrderVO orderVO, @LoginUser @ApiIgnore UserInfoEntity user) {
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

}
