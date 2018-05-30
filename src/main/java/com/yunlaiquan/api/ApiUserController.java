package com.yunlaiquan.api;

import com.yunlaiquan.service.UserService;
import com.yunlaiquan.utils.R;
import com.yunlaiquan.utils.annotation.IgnoreAuth;
import com.yunlaiquan.utils.validator.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author  weicc
 * @date  2018/5/30 18:30
 * @desc
 **/
@RestController
@RequestMapping("/api")
@Api("用户接口")
public class ApiUserController {
    @Autowired
    private UserService userService;

    /**
     * 支付
     * @param money
     * @param pay_password
     * @return
     */
    @IgnoreAuth
    @PostMapping("pay")
    @ApiOperation(value = "支付")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType="string", name = "money", value = "支付金额", required = true),
            @ApiImplicitParam(paramType = "query", dataType="string", name = "pay_password", value = "支付密码", required = true)
    })
    public R pay(String money, String pay_password){
        Assert.isBlank(money, "支付金额不能为空");
        Assert.isBlank(pay_password, "支付密码不能为空");

        userService.save(money, pay_password);

        return R.ok();
    }

    /**
     * 修改用户信息
     * @param username
     * @param phone
     * @return
     */
    @IgnoreAuth
    @PostMapping("modifyUserInfo")
    @ApiOperation(value = "修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType="string", name = "username", value = "用户名", required = true),
            @ApiImplicitParam(paramType = "query", dataType="string", name = "phone", value = "电话", required = true)
    })
    public R modifyUserInfo(String username, String age, String phone){
        Assert.isBlank(username, "用户名不能为空");
        Assert.isBlank(phone, "电话不能为空");

        userService.save(username, phone);

        return R.ok();
    }

    /**
     * 押金
     */
}
