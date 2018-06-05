package com.yunquanlai.api.comsumer;

import com.yunquanlai.admin.order.service.OrderInfoService;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.api.comsumer.vo.OrderVO;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.annotation.IgnoreAuth;
import com.yunquanlai.utils.annotation.LoginUser;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weicc
 * @date 2018/5/30 21:36
 * @desc
 **/
@RestController
@RequestMapping("/client/api")
@Api("订单接口")
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
    public R queryOrder(@LoginUser UserInfoEntity user,
                        @ApiParam("订单状态") Integer status) {
        // TODO 判断当前请求是否是由该用户发起的
        return R.ok();
    }


    /**
     * 下单
     *
     * @return
     */
    @PostMapping("order")
    @ApiOperation(value = "下单")
    @ApiImplicitParam(name = "orderVO", value = "订单信息", required = true, dataType = "com.yunquanlai.api.comsumer.vo.OrderVO", paramType = "body")
    public R order(@RequestBody OrderVO orderVO,@LoginUser UserInfoEntity user) {

        if(orderInfoService.newOrder(orderVO,user)){
            return R.ok();
        }
        // TODO 返回具体失败原因
        return R.error("下单失败");
    }
}
