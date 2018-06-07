package com.yunquanlai.api.delivery;

import com.yunquanlai.admin.delivery.entity.DeliveryDistributorEntity;
import com.yunquanlai.admin.delivery.service.DeliveryDistributorService;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.RRException;
import com.yunquanlai.utils.annotation.IgnoreAuth;
import com.yunquanlai.utils.validator.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-26 17:27
 */
@RestController
@RequestMapping("/delivery/api")
@Api("注册接口")
public class ApiLoginController {
    @Autowired
    private DeliveryDistributorService deliveryDistributorService;

    /**
     * 注册
     */
    @IgnoreAuth
    @PostMapping("login")
    @ApiOperation(value = "配送员登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "平台标识", value = "platform", required = true),
            @ApiImplicitParam(paramType = "header", name = "版本", value = "platform", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "mobile", value = "手机号", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "password", value = "密码", required = true)
    })
    public R register(String mobile, String password) {
        Assert.isBlank(mobile, "手机号不能为空");
        Assert.isBlank(password, "密码不能为空");

        DeliveryDistributorEntity deliveryDistributorEntity = deliveryDistributorService.queryObjectByPhone(mobile);

        //密码错误
        if (!deliveryDistributorEntity.getPassword().equals(DigestUtils.sha256Hex(password))) {
            throw new RRException("手机号或密码错误");
        }
        //TODO  生成一个token
        return R.ok().put("deliveryUser", deliveryDistributorEntity);
    }
}
