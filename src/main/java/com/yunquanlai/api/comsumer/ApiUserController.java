package com.yunquanlai.api.comsumer;

import com.yunquanlai.admin.user.entity.UserClientTokenEntity;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.admin.user.service.UserClientTokenService;
import com.yunquanlai.admin.user.service.UserInfoService;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.annotation.IgnoreAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@Api("微信小程序接口-用户接口")
public class ApiUserController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserClientTokenService userClientTokenService;

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
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "uid", value = "微信 ID", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "username", value = "微信名", required = true)
    })
    public R wechatLogin(String uid, String username) {
        UserInfoEntity userInfoEntity = userInfoService.queryObjectByUid(uid);
        if (userInfoEntity == null) {
            //不存在用户就创建用户
            userInfoEntity = new UserInfoEntity();
            userInfoEntity.setStatus(0);
            userInfoEntity.setCreationTime(new Date());
            userInfoEntity.setUid(uid);
            userInfoEntity.setUsername(username);
            userInfoService.save(userInfoEntity);
        }
        return createToken(userInfoEntity.getId());
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

//    /**
//     * 支付
//     *
//     * @param money
//     * @param pay_password
//     *
//     * @return
//     */
//    @IgnoreAuth
//    @PostMapping("pay")
//    @ApiOperation(value = "支付")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "query", dataType = "string", name = "money", value = "支付金额", required = true),
//            @ApiImplicitParam(paramType = "query", dataType = "string", name = "pay_password", value = "支付密码", required = true)
//    })
//    public R pay(String money, String pay_password) {
//        Assert.isBlank(money, "支付金额不能为空");
//        Assert.isBlank(pay_password, "支付密码不能为空");
//
//        userService.save(money, pay_password);
//
//        return R.ok();
//    }
//
//    /**
//     * 修改用户信息
//     *
//     * @param username
//     * @param phone
//     *
//     * @return
//     */
//    @IgnoreAuth
//    @PostMapping("modifyUserInfo")
//    @ApiOperation(value = "修改用户信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "query", dataType = "string", name = "username", value = "用户名", required = true),
//            @ApiImplicitParam(paramType = "query", dataType = "string", name = "phone", value = "电话", required = true)
//    })
//    public R modifyUserInfo(String username, String age, String phone) {
//        Assert.isBlank(username, "用户名不能为空");
//        Assert.isBlank(phone, "电话不能为空");
//
//        userService.save(username, phone);
//
//        return R.ok();
//    }

}
