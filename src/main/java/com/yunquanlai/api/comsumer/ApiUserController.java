package com.yunquanlai.api.comsumer;

import com.yunquanlai.admin.user.entity.UserClientTokenEntity;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.admin.user.entity.UserWithdrawDepositEntity;
import com.yunquanlai.admin.user.service.UserClientTokenService;
import com.yunquanlai.admin.user.service.UserInfoService;
import com.yunquanlai.admin.user.service.UserWithdrawDepositService;
import com.yunquanlai.utils.ConfigUtils;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.annotation.IgnoreAuth;
import com.yunquanlai.utils.annotation.LoginUser;
import com.yunquanlai.utils.validator.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author weicc
 * @date 2018/5/30 18:30
 * @desc
 **/
@RestController
@RequestMapping("/client/api")
@Api(value = "客户端-用户", description = "用户相关接口")
public class ApiUserController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserClientTokenService userClientTokenService;

    @Autowired
    private UserWithdrawDepositService userWithdrawDepositService;

    @Autowired
    private ConfigUtils configUtils;

    //12小时后过期
    private final static int EXPIRE = 3600 * 12;

    /**
     * 微信小程序登录接口
     *
     * @return
     */
    @IgnoreAuth
    @PostMapping("wechat/login")
    @ApiOperation(value = "用户从小程序登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "openId", value = "微信 openId", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "username", value = "微信名", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "unionId", value = "unionId"),
    })
    public R wechatLogin(@RequestParam String openId, @RequestParam String username, String unionId) {
        Assert.isBlank(openId, "openId不能为空");
//        Assert.isBlank(username, "用户名不能为空");
        UserInfoEntity userInfoEntity = userInfoService.queryObjectByOpenId(openId);
        if (userInfoEntity == null) {
            //不存在用户就创建用户
            userInfoEntity = new UserInfoEntity();
            userInfoEntity.setStatus(0);
            userInfoEntity.setCreationTime(new Date());
            userInfoEntity.setOpenId(openId);
            userInfoEntity.setUid(unionId);
            userInfoEntity.setEmptyBucketNumber(0);
            userInfoEntity.setEnableDepositAmount(BigDecimal.ZERO);
            userInfoEntity.setDisableDepositAmount(BigDecimal.ZERO);
            userInfoEntity.setDepositAmount(BigDecimal.ZERO);
            userInfoService.save(userInfoEntity);
        }
        if (!userInfoEntity.equals(userInfoEntity.getUsername())) {
            userInfoEntity.setUsername(username);
            userInfoService.update(userInfoEntity);
        }
        userInfoEntity.setOpenId(null);
        userInfoEntity.setUid(null);
        return createToken(userInfoEntity.getId()).put("userInfo", userInfoEntity).put("isOpenTime", configUtils.isOpenTime()).put("emptyBucketValue", configUtils.getEmptyValue());
    }

    @PostMapping("user/withdraw")
    @ApiOperation(value = "用户提交押金提现申请（只能一次全部提取）")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "token", required = true)
    })
    public R depositoryWithdraw(@LoginUser @ApiIgnore UserInfoEntity userInfoEntity) {
        UserWithdrawDepositEntity temp = userWithdrawDepositService.queryObjectByUserId(userInfoEntity.getId());
        Assert.isNotNull(temp, "已存在未处理的押金提现申请，请耐心等待。");
        UserWithdrawDepositEntity userWithdrawDepositEntity = new UserWithdrawDepositEntity();
        userWithdrawDepositEntity.setUserInfoId(userInfoEntity.getId());
        userWithdrawDepositEntity.setIsHandle(10);
        userWithdrawDepositEntity.setCreationTime(new Date());
        userWithdrawDepositService.saveDepositoryWithdraw(userWithdrawDepositEntity, userInfoEntity);
        userInfoEntity.setOpenId(null);
        userInfoEntity.setUid(null);
        return R.ok().put("userInfo", userInfoEntity);
    }

    private R createToken(Long userId) {
        //生成一个token
        String token = UUID.randomUUID().toString();
        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        UserClientTokenEntity userClientTokenEntity = userClientTokenService.queryByUserId(userId);
        if (userClientTokenEntity == null) {
            userClientTokenEntity = new UserClientTokenEntity();
            userClientTokenEntity.setUserId(userId);
            userClientTokenEntity.setToken(token);
            userClientTokenEntity.setUpdateTime(now);
            userClientTokenEntity.setExpireTime(expireTime);

            //保存token
            userClientTokenService.save(userClientTokenEntity);
        } else {
            String oldToken = userClientTokenEntity.getToken();
            userClientTokenEntity.setToken(token);
            userClientTokenEntity.setUpdateTime(now);
            userClientTokenEntity.setExpireTime(expireTime);
            //更新token
            userClientTokenService.update(userClientTokenEntity, oldToken);
        }

        Map<String, Object> map = new HashMap<>(16);
        map.put("token", token);
        map.put("expire", EXPIRE);

        return R.ok(map);
    }

}
