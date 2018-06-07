package com.yunquanlai.api.delivery;

import com.yunquanlai.admin.delivery.entity.DeliveryClientTokenEntity;
import com.yunquanlai.admin.delivery.entity.DeliveryDistributorEntity;
import com.yunquanlai.admin.delivery.service.DeliveryClientTokenService;
import com.yunquanlai.admin.delivery.service.DeliveryDistributorService;
import com.yunquanlai.admin.user.entity.UserClientTokenEntity;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @ApiOperation(value = "配送员登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "string",name = "platform", value = "平台标识", required = true),
            @ApiImplicitParam(paramType = "header", dataType = "string",name = "version", value = "版本", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "mobile", value = "手机号", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "password", value = "密码", required = true)
    })
    public R login(@RequestParam String mobile, @RequestParam String password) {
        Assert.isBlank(mobile, "手机号不能为空");
        Assert.isBlank(password, "密码不能为空");

        DeliveryDistributorEntity deliveryDistributorEntity = deliveryDistributorService.queryObjectByPhone(mobile);

        //密码错误
        if (!deliveryDistributorEntity.getPassword().equals(DigestUtils.sha256Hex(password))) {
            throw new RRException("手机号或密码错误");
        }
        return createToken(deliveryDistributorEntity.getId());
    }

    private R createToken(Long distributorId) {
        //生成一个token
        String token = UUID.randomUUID().toString();
        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        DeliveryClientTokenEntity deliveryClientTokenEntity = deliveryClientTokenService.queryByDistributorId(distributorId);
        if (deliveryClientTokenEntity != null) {
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
