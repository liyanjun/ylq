package com.yunquanlai.api.delivery;

import com.yunquanlai.admin.delivery.entity.DeliveryClientTokenEntity;
import com.yunquanlai.admin.delivery.entity.DeliveryDistributorEntity;
import com.yunquanlai.admin.delivery.service.DeliveryClientTokenService;
import com.yunquanlai.admin.delivery.service.DeliveryDistributorService;
import com.yunquanlai.admin.user.entity.UserClientTokenEntity;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.RRException;
import com.yunquanlai.utils.annotation.IgnoreAuth;
import com.yunquanlai.utils.annotation.LoginDelivery;
import com.yunquanlai.utils.validator.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 注册
 *
 * @author liyanjun
 */
@RestController
@RequestMapping("/delivery/api")
@Api(value = "配送端-用户", description = "用户相关接口")
public class ApiDeliveryUserController {

    @Autowired
    private DeliveryDistributorService deliveryDistributorService;

    @Autowired
    private DeliveryClientTokenService deliveryClientTokenService;

    /**
     * 12小时后过期
     */
    private final static int EXPIRE = 3600 * 12;

    /**
     * 登录
     */
    @IgnoreAuth
    @PostMapping("login")
    @ApiOperation(value = "配送员登录,disable字段含义（0:停用    1：启用    2：新创建（默认））")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "platform", value = "平台标识(10：安卓，20：苹果)", required = true),
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "version", value = "版本", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "mobile", value = "手机号", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "password", value = "密码", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "clientId", value = "推送客户端 ID", required = true)
    })
    public R login(@RequestHeader String platform, @RequestHeader String version,
                   @RequestParam String mobile, @RequestParam String password, @RequestParam String clientId) {
        Assert.isBlank(mobile, "手机号不能为空");
        Assert.isBlank(password, "密码不能为空");

        DeliveryDistributorEntity deliveryDistributorEntity = deliveryDistributorService.queryObjectByPhone(mobile);

        if (deliveryDistributorEntity == null) {
            throw new RRException("用户不存在");
        }
        if (!deliveryDistributorEntity.getPassword().equals(DigestUtils.sha256Hex(password))) {
            throw new RRException("手机号或密码错误");
        }
        if (deliveryDistributorEntity.getDisable() == 0) {
            throw new RRException("账号已停用");
        }
        deliveryDistributorEntity.setClientId(clientId);
        deliveryDistributorService.update(deliveryDistributorEntity);
        return createToken(deliveryDistributorEntity.getId()).put("disable", deliveryDistributorEntity.getDisable());
    }

    /**
     * 修改密码
     *
     * @return
     */
    @PostMapping("editPassword")
    @ApiOperation(value = "修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "platform", value = "平台标识(10：安卓，20：苹果)", required = true),
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "version", value = "版本", required = true),
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "passwordOld", value = "旧密码", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "password", value = "新密码", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "passwordRepeat", value = "新密码重复", required = true)
    })
    public R editPassword(@LoginDelivery @ApiIgnore DeliveryDistributorEntity deliveryDistributorEntity,
                          @RequestParam String passwordOld, @RequestParam String password, @RequestParam String passwordRepeat) {
        if (!deliveryDistributorEntity.getPassword().equals(DigestUtils.sha256Hex(passwordOld))) {
            throw new RRException("旧密码错误");
        }
        if (!password.equals(passwordRepeat)) {
            throw new RRException("两次输入密码不一致");
        }
        deliveryDistributorEntity.setPassword(DigestUtils.sha256Hex(password));
        deliveryDistributorEntity.setDisable((byte) 1);
        deliveryDistributorService.update(deliveryDistributorEntity);
        return R.ok();
    }

    /**
     * 更新配送员配送状态
     *
     * @return
     */
    @PostMapping("updateDeliverStatus")
    @ApiOperation(value = "修改配送员配送状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "platform", value = "平台标识(10：安卓，20：苹果)", required = true),
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "version", value = "版本", required = true),
            @ApiImplicitParam(paramType = "header", dataType = "string", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "status", value = "10：可配送，20：不可配送", required = true)
    })
    public R updateDeliverStatus(@LoginDelivery @ApiIgnore DeliveryDistributorEntity deliveryDistributorEntity, @RequestParam Integer status) {
        if (status != 10 && status != 20) {
            throw new RRException("不是合法的状态值");
        }
        deliveryDistributorEntity.setStatus(status);
        deliveryDistributorService.update(deliveryDistributorEntity);
        return R.ok();
    }

    private R createToken(Long distributorId) {
        //生成一个token
        String token = UUID.randomUUID().toString();
        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        DeliveryClientTokenEntity deliveryClientTokenEntity = deliveryClientTokenService.queryByDistributorId(distributorId);
        if (deliveryClientTokenEntity == null) {
            deliveryClientTokenEntity = new DeliveryClientTokenEntity();
            deliveryClientTokenEntity.setDeliveryDistributorId(distributorId);
            deliveryClientTokenEntity.setToken(token);
            deliveryClientTokenEntity.setUpdateTime(now);
            deliveryClientTokenEntity.setExpireTime(expireTime);
            deliveryClientTokenService.save(deliveryClientTokenEntity);
        } else {
            String oldToken = deliveryClientTokenEntity.getToken();
            deliveryClientTokenEntity.setToken(token);
            deliveryClientTokenEntity.setUpdateTime(now);
            deliveryClientTokenEntity.setExpireTime(expireTime);
            deliveryClientTokenService.update(deliveryClientTokenEntity, oldToken);
        }

        Map<String, Object> map = new HashMap<>(16);
        map.put("token", token);
        map.put("expire", EXPIRE);

        return R.ok(map);
    }
}
