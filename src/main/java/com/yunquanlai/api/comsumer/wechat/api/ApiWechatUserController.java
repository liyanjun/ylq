package com.yunquanlai.api.comsumer.wechat.api;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.admin.user.service.UserInfoService;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.annotation.IgnoreAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 微信小程序用户接口
 *
 * @author liyanjun
 */
@RestController
@RequestMapping("/client/api/wechat/user")
@Api(value = "客户端-小程序", description = "小程序相关接口")
public class ApiWechatUserController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WxMaService wxMaService;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 微信小程序登录接口
     *
     * @return
     */
    @IgnoreAuth
    @PostMapping("login")
    @ApiOperation(value = "用户从小程序登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "code", value = "微信jscode"),
    })
    public R login(@RequestParam String code) {
        if (StringUtils.isBlank(code)) {
            return R.error("empty jscode");
        }

        if (StringUtils.isBlank(code)) {
            return R.error("empty name");
        }

        try {
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
            UserInfoEntity userInfoEntity = userInfoService.queryObjectByOpenId(session.getOpenid());
            if (userInfoEntity == null) {
                //不存在用户就创建用户
                userInfoEntity = new UserInfoEntity();
                userInfoEntity.setStatus(0);
                userInfoEntity.setCreationTime(new Date());
                userInfoEntity.setUid(session.getUnionid());
                userInfoEntity.setOpenId(session.getOpenid());
                userInfoEntity.setEmptyBucketNumber(0);
                userInfoEntity.setEnableDepositAmount(BigDecimal.ZERO);
                userInfoEntity.setDisableDepositAmount(BigDecimal.ZERO);
                userInfoEntity.setDepositAmount(BigDecimal.ZERO);
                userInfoService.save(userInfoEntity);
            }
            return R.ok().put("session", session);
        } catch (WxErrorException e) {
            LOGGER.error(e.getMessage(), e);
            return R.error(e.toString());
        }
    }


//    /**
//     * <pre>
//     * 获取用户信息接口
//     * </pre>
//     */
//    @PostMapping("/info")
//    public R info(String sessionKey, String signature, String rawData, String encryptedData, String iv) {
//        // 用户信息校验
//        if (!this.wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
//            return R.error("user check failed");
//        }
//
//        // 解密用户信息
//        WxMaUserInfo userInfo = this.wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
//        return R.ok().put("userInfo", userInfo);
//    }

//    /**
//     * <pre>
//     * 获取用户绑定手机号信息
//     * </pre>
//     */
//    @GetMapping("/phone")
//    public String phone(String sessionKey, String signature, String rawData, String encryptedData, String iv) {
//        // 用户信息校验
//        if (!this.wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
//            return R.error("user check failed");
//        }
//
//        // 解密
//        WxMaPhoneNumberInfo phoneNoInfo = this.wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);
//
//        return R.ok().put("phoneNoInfo",phoneNoInfo);
//    }

}
