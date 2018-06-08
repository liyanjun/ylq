package com.yunquanlai.api.delivery;

import com.yunquanlai.admin.delivery.entity.DeliveryDistributorEntity;
import com.yunquanlai.admin.order.entity.OrderDeliveryInfoEntity;
import com.yunquanlai.admin.order.service.OrderDeliveryInfoService;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.annotation.LoginDelivery;
import com.yunquanlai.utils.validator.Assert;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liyanjun
 **/
@RestController
@RequestMapping("/delivery/api")
@Api(value = "配送端-业务", description = "业务接口")
public class ApiDeliveryController {

    @Autowired
    OrderDeliveryInfoService orderDeliveryInfoService;

    /**
     * 回收空桶接口
     *
     * @return
     */
    @PostMapping("recyclingEmptyBarrels")
    @ApiOperation(value = "回收空桶")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "string",name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "header", dataType = "string",name = "platform", value = "平台标识", required = true),
            @ApiImplicitParam(paramType = "header", dataType = "string",name = "version", value = "版本", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "number", value = "空桶数", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "long", name = "orderDeliveryId", value = "配送单 ID", required = true)
    })
    public R recyclingEmptyBarrels(Integer number, Long orderDeliveryId) {
        return R.ok();
    }


    /**
     * 查询该用户的配送单
     *
     * @return
     */
    @PostMapping("queryOrderDeliveries")
    @ApiOperation(value = "查询该用户的配送单,（按照配送中，期望配送时间，下单时间排序）\n 配送单状态，10：未支付，20：未分配，30：分配中，40：配送中，50：配送结束")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "string",name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "header", dataType = "string",name = "platform", value = "平台标识", required = true),
            @ApiImplicitParam(paramType = "header", dataType = "string",name = "version", value = "版本", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "offset", value = "位移数", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "limit", value = "查询条数", required = true)
    })
    public R queryOrderDeliveries(@LoginDelivery @ApiIgnore DeliveryDistributorEntity deliveryDistributorEntity,
                                  @RequestParam Integer offset,
                                  @RequestParam Integer limit) {
        Map<String, Object> filter = new HashMap(16);
        filter.put("deliveryDistributorId", deliveryDistributorEntity.getId());
        filter.put("offset", offset);
        filter.put("limit", limit);
        List<OrderDeliveryInfoEntity> orderDeliveryInfoEntityList = orderDeliveryInfoService.queryByDistributorId(filter);
        return R.ok().put("orderDeliveryInfoList", orderDeliveryInfoEntityList);
    }

    /**
     * 标记订单送达
     *
     * @return
     */
    @PostMapping("markerOrderDelivery")
    @ApiOperation(value = "标记订单送达")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "string",name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "header", dataType = "string",name = "platform", value = "平台标识", required = true),
            @ApiImplicitParam(paramType = "header", dataType = "string",name = "version", value = "版本", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "long", name = "orderDeliveryId", value = "配送单 ID", required = true)
    })
    public R markerOrderDelivery(@LoginDelivery @ApiIgnore DeliveryDistributorEntity deliveryDistributorEntity,
                                 @RequestParam Long orderDeliveryId) {
        OrderDeliveryInfoEntity orderDeliveryInfoEntity = orderDeliveryInfoService.queryObject(orderDeliveryId);
        Assert.isEqual(deliveryDistributorEntity.getId().longValue(), orderDeliveryInfoEntity.getDeliveryDistributorId().longValue(), "请不要标记别人的订单。");
        orderDeliveryInfoService.orderDelivery(deliveryDistributorEntity, orderDeliveryInfoEntity);
        return R.ok();
    }


}
