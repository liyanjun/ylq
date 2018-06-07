package com.yunquanlai.api.delivery;

import com.yunquanlai.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyanjun
 * @date 2018/5/30 22:05
 * @desc
 **/
@RestController
@RequestMapping("/delivery/api")
@Api("配送接口")
public class ApiDeliveryController {

    /**
     * 回收空桶接口
     *
     * @return
     */
    @PostMapping("recyclingEmptyBarrels")
    @ApiOperation(value = "回收空桶")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "header", name = "平台标识", value = "platform", required = true),
            @ApiImplicitParam(paramType = "header", name = "版本", value = "platform", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "number", value = "空桶数", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "long", name = "orderId", value = "从哪个订单回收的", required = true)
    })
    public R recyclingEmptyBarrels(Integer number, Long orderId) {
        return R.ok();
    }


    /**
     * 查询该用户的配送单（按照未配送，下单时间排序）
     *
     * @return
     */
    @PostMapping("recyclingEmptyBarrels")
    @ApiOperation(value = "查询该用户的配送单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "header", name = "平台标识", value = "platform", required = true),
            @ApiImplicitParam(paramType = "header", name = "版本", value = "platform", required = true),
    })
    public R queryOrderDeliveries() {
        return R.ok();
    }


}
