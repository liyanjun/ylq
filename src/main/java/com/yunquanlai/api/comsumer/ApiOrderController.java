package com.yunquanlai.api.comsumer;

import com.yunquanlai.admin.order.entity.OrderInfoEntity;
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
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "status", value = "订单状态")
    })
    public R queryOrder(@LoginUser @ApiIgnore UserInfoEntity user, Integer status) {
        return R.ok();
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
        orderInfoService.closeOrder(orderId,user.getId());
        return R.ok();
    }

}
