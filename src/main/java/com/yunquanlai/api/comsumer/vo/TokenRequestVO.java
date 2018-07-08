package com.yunquanlai.api.comsumer.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 下单信息
 */
public class TokenRequestVO {
    /**
     * openId
     */
    private String openId;
    /**
     * 用户名
     */
    private String username;
    /**
     * uid
     */
    private String unionId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
}
