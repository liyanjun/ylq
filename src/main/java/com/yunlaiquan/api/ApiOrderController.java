package com.yunlaiquan.api;

import com.yunlaiquan.utils.R;
import com.yunlaiquan.utils.annotation.IgnoreAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author  weicc
 * @date  2018/5/30 21:36
 * @desc
 **/
@RestController
@RequestMapping("/api")
@Api("订单接口")
public class ApiOrderController {

    /**
     * 用户订单查询
     * @param user_info_id
     * @param status
     * @return
     */
    @IgnoreAuth
    @PostMapping("trackOrder")
    @ApiOperation(value = "用户订单查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType="string", name = "user_info_id", value = "用户ID", required = true),
            @ApiImplicitParam(paramType = "query", dataType="int", name = "status", value = "订单状态", required = true)
    })
    public R trackOrder(String user_info_id, String status){

        return R.ok();
    }

    /**
     * 订单推送
     */
    @IgnoreAuth
    @PostMapping("pushOrder")
    @ApiOperation(value = "订单推送")
    public R pushOrder(){

        return R.ok();
    }
}
