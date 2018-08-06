package com.yunquanlai.api.comsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yunquanlai.admin.delivery.entity.DeliveryDistributorEntity;
import com.yunquanlai.admin.delivery.entity.DeliveryEndpointEntity;
import com.yunquanlai.admin.delivery.service.DeliveryDistributorService;
import com.yunquanlai.admin.delivery.service.DeliveryEndpointService;
import com.yunquanlai.admin.order.entity.OrderDeliveryInfoEntity;
import com.yunquanlai.admin.order.entity.OrderInfoEntity;
import com.yunquanlai.admin.order.service.OrderDeliveryInfoService;
import com.yunquanlai.admin.order.service.OrderInfoService;
import com.yunquanlai.admin.product.entity.ProductTicketEntity;
import com.yunquanlai.admin.product.service.ProductTicketService;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.admin.user.entity.UserProductTicketEntity;
import com.yunquanlai.admin.user.service.UserProductTicketService;
import com.yunquanlai.api.comsumer.vo.OrderVO;
import com.yunquanlai.utils.ConfigUtils;
import com.yunquanlai.utils.DistanceUtils;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.TokenUtils;
import com.yunquanlai.utils.annotation.IgnoreAuth;
import com.yunquanlai.utils.annotation.LoginUser;
import com.yunquanlai.utils.validator.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author weicc
 * @date 2018/8/06 21:36
 * @desc
 **/
@RestController
@RequestMapping("/client/api")
@Api(value = "客户端-水票订单", description = "水票订单相关接口")
public class ApiTicketOrderController {

    @Autowired
    private ProductTicketService productTicketService;

    @Autowired
    private UserProductTicketService userProductTicketService;

    /**
     * 水票下单
     *
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
    public R queryOrder(@LoginUser @ApiIgnore UserInfoEntity user, Long productTicketId) {
        ProductTicketEntity productTicket = productTicketService.queryObject(productTicketId);
        userProductTicketService.buyTicket(user,productTicket);
        return R.ok();
    }


}
