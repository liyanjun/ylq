package com.yunquanlai.api.comsumer;

import com.yunquanlai.api.comsumer.vo.OrderVO;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.annotation.IgnoreAuth;
import io.swagger.annotations.*;
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
@RequestMapping("/api")
@Api("订单接口")
public class ApiOrderController {

    /**
     * 用户订单查询
     *
     * @param userInfoId
     * @param status
     * @return
     */
    @IgnoreAuth
    @PostMapping("queryOrder")
    @ApiOperation(value = "用户订单查询")
    public R queryOrder(@ApiParam("用户ID") String userInfoId,
                        @ApiParam("订单状态") String status) {

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
    public R order(@RequestBody OrderVO orderVO) {

        return R.ok();
    }
}
