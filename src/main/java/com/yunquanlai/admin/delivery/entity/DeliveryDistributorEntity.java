package com.yunquanlai.admin.delivery.entity;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 配送员信息
 *
 * @author liyanjun
 * @email
 * @date 2018-06-04 08:25:11
 */
public class DeliveryDistributorEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 设置：
     */
    private Long id;
    /**
     * 设置：配送员姓名
     */
    private String name;
    /**
     * 设置：配送员手机号
     */
    private String phone;
    /**
     * 设置：配送员登录密码
     */
    private String password;
    /**
     * 设置：配送员生日
     */
    private String birthday;
    /**
     * 设置：用于点对点登录时的推送，由APP在登录的时候一起上传
     */
    private String clientid;
    /**
     * 设置：当前状态，10：可配送，20：不可配送
     */
    private Integer status;
    /**
     * 设置：身份证号（备用）
     */
    private String identifycation;
    /**
     * 设置：身份证照片地址
     */
    private String identifycationUrl;
    /**
     * 设置：健康证地址
     */
    private String healthUrl;
    /**
     * 设置：所属配送点
     */
    private Long deliveryEndpointId;

    /**
     * 当前配送订单数
     */
    private Integer orderCount;

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：配送员姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：配送员姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：配送员手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取：配送员手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置：配送员登录密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取：配送员登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置：配送员生日
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取：配送员生日
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 设置：用于点对点登录时的推送，由APP在登录的时候一起上传
     */
    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    /**
     * 获取：用于点对点登录时的推送，由APP在登录的时候一起上传
     */
    public String getClientid() {
        return clientid;
    }

    /**
     * 设置：当前状态，10：可配送，20：不可配送
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：当前状态，10：可配送，20：不可配送
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：身份证号（备用）
     */
    public void setIdentifycation(String identifycation) {
        this.identifycation = identifycation;
    }

    /**
     * 获取：身份证号（备用）
     */
    public String getIdentifycation() {
        return identifycation;
    }

    /**
     * 设置：身份证照片地址
     */
    public void setIdentifycationUrl(String identifycationUrl) {
        this.identifycationUrl = identifycationUrl;
    }

    /**
     * 获取：身份证照片地址
     */
    public String getIdentifycationUrl() {
        return identifycationUrl;
    }

    /**
     * 设置：健康证地址
     */
    public void setHealthUrl(String healthUrl) {
        this.healthUrl = healthUrl;
    }

    /**
     * 获取：健康证地址
     */
    public String getHealthUrl() {
        return healthUrl;
    }

    /**
     * 设置：所属配送点
     */
    public void setDeliveryEndpointId(Long deliveryEndpointId) {
        this.deliveryEndpointId = deliveryEndpointId;
    }

    /**
     * 获取：所属配送点
     */
    public Long getDeliveryEndpointId() {
        return deliveryEndpointId;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }
}
