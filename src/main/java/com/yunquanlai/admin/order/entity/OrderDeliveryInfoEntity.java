package com.yunquanlai.admin.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 订单配送信息表
 *
 * @author liyanjun
 * @email
 * @date 2018-06-04 22:42:21
 */
public class OrderDeliveryInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int STATUS_NEW = 10;
    public static final int STATUS_UN_DISTRIBUTE = 20;
    public static final int STATUS_CLOSE = 30;
    public static final int STATUS_ON_DELIVERY = 40;
    public static final int STATUS_DELIVERY_END = 50;
    public static final int STATUS_EXCEPTION = 60;

    /**
     * 设置：主键 ID
     */
    private Long id;
    /**
     * 设置：收货人姓名
     */
    private String name;
    /**
     * 设置：订单地址信息（拼凑的用于在订单列表显示的）
     */
    private String address;
    /**
     * 设置：收货人性别
     */
    private Integer sex;
    /**
     * 设置：用户手机号
     */
    private String phone;
    /**
     * 设置：订单配送坐标x
     */
    private BigDecimal locationX;
    /**
     * 设置：订单配送坐标y
     */
    private BigDecimal locationY;
    /**
     * 设置：配送单状态，10：新创建，20：未分配，30：已关闭，40：配送中，50：配送结束,60:异常
     */
    private Integer status;
    /**
     * 设置：配送单备注
     */
    private String remark;
    /**
     * 设置：配送单创建时间
     */
    private Date creationTime;

    /**
     * 配送单开始分配时间
     */
    private Date distributeTime;
    /**
     * 设置：期望配送时间
     */
    private Date deliveryTime;
    /**
     * 设置：关联配送员 ID
     */
    private Long deliveryDistributorId;
    /**
     * 设置：关联订单 ID
     */
    private Long orderInfoId;
    /**
     * 设置：关联用户 ID
     */
    private Long userInfoId;

    /**
     * 设置：主键 ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：主键 ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：收货人姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：收货人姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：订单地址信息（拼凑的用于在订单列表显示的）
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：订单地址信息（拼凑的用于在订单列表显示的）
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置：收货人性别
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取：收货人性别
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置：用户手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取：用户手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置：订单配送坐标x
     */
    public void setLocationX(BigDecimal locationX) {
        this.locationX = locationX;
    }

    /**
     * 获取：订单配送坐标x
     */
    public BigDecimal getLocationX() {
        return locationX;
    }

    /**
     * 设置：订单配送坐标y
     */
    public void setLocationY(BigDecimal locationY) {
        this.locationY = locationY;
    }

    /**
     * 获取：订单配送坐标y
     */
    public BigDecimal getLocationY() {
        return locationY;
    }

    /**
     * 设置：配送单状态，10：新创建，20：未分配，30：已关闭，40：配送中，50：配送结束,60:异常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：配送单状态，10：新创建，20：未分配，30：已关闭，40：配送中，50：配送结束,60:异常
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：配送单备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：配送单备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置：配送单创建时间
     */
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * 获取：配送单创建时间
     */
    public Date getCreationTime() {
        return creationTime;
    }

    public Date getDistributeTime() {
        return distributeTime;
    }

    public void setDistributeTime(Date distributeTime) {
        this.distributeTime = distributeTime;
    }

    /**
     * 设置：期望配送时间
     */
    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    /**
     * 获取：期望配送时间
     */
    public Date getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * 设置：关联配送员 ID
     */
    public void setDeliveryDistributorId(Long deliveryDistributorId) {
        this.deliveryDistributorId = deliveryDistributorId;
    }

    /**
     * 获取：关联配送员 ID
     */
    public Long getDeliveryDistributorId() {
        return deliveryDistributorId;
    }

    /**
     * 设置：关联订单 ID
     */
    public void setOrderInfoId(Long orderInfoId) {
        this.orderInfoId = orderInfoId;
    }

    /**
     * 获取：关联订单 ID
     */
    public Long getOrderInfoId() {
        return orderInfoId;
    }

    /**
     * 设置：关联用户 ID
     */
    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    /**
     * 获取：关联用户 ID
     */
    public Long getUserInfoId() {
        return userInfoId;
    }
}
