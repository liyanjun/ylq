package com.yunquanlai.admin.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 订单信息表
 *
 * @author liyanjun
 * @email
 * @date 2018-06-04 22:42:21
 */
public class OrderInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final Integer STATUS_NEW = 10;
    public static final Integer STATUS_PAID = 20;
    public static final Integer STATUS_ON_DELIVERY = 30;
    public static final Integer STATUS_DELIVERY_END = 40;
    public static final Integer STATUS_CLOSE = 50;
    public static final Integer STATUS_EXCEPTION = 60;

    /**
     * 设置：主键 ID
     */
    private Long id;
    /**
     * 设置：订单总额
     */
    private BigDecimal amountTotal;
    /**
     * 设置：订单金额
     */
    private BigDecimal amount;
    /**
     * 设置：订单折扣优惠金额
     */
    private BigDecimal amountBenifit;
    /**
     * 设置：订单活动优惠金额（即除了优惠标价外，使用的活动奖励）
     */
    private BigDecimal amountActivity;
    /**
     * 设置：订单配送费
     */
    private BigDecimal amountDeliveryFee;
    /**
     * 设置：订单状态，10：新创建，20：已支付，待配送，30：配送中，40：已送达，50已关闭
     */
    private Integer status;
    /**
     * 设置：关联配送员 ID
     */
    private Long deliveryDistributorId;
    /**
     * 设置：关联配送员名
     */
    private String deliveryDistributorName;
    /**
     * 设置：关联用户 ID
     */
    private Long userInfoId;
    /**
     * 设置：用户名
     */
    private String username;
    /**
     * 设置：订单备注
     */
    private String remark;
    /**
     * 设置：订单创建时间
     */
    private Date creationTime;
    /**
     * 设置：更新时间
     */
    private Date updateTime;

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
     * 设置：订单总额
     */
    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    /**
     * 获取：订单总额
     */
    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    /**
     * 设置：订单金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取：订单金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置：订单折扣优惠金额
     */
    public void setAmountBenifit(BigDecimal amountBenifit) {
        this.amountBenifit = amountBenifit;
    }

    /**
     * 获取：订单折扣优惠金额
     */
    public BigDecimal getAmountBenifit() {
        return amountBenifit;
    }

    /**
     * 设置：订单活动优惠金额（即除了优惠标价外，使用的活动奖励）
     */
    public void setAmountActivity(BigDecimal amountActivity) {
        this.amountActivity = amountActivity;
    }

    /**
     * 获取：订单活动优惠金额（即除了优惠标价外，使用的活动奖励）
     */
    public BigDecimal getAmountActivity() {
        return amountActivity;
    }

    /**
     * 设置：订单配送费
     */
    public void setAmountDeliveryFee(BigDecimal amountDeliveryFee) {
        this.amountDeliveryFee = amountDeliveryFee;
    }

    /**
     * 获取：订单配送费
     */
    public BigDecimal getAmountDeliveryFee() {
        return amountDeliveryFee;
    }

    /**
     * 设置：订单状态，10：新创建，20：已支付，待配送，30：配送中，40：已送达，50已关闭
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：订单状态，10：新创建，20：已支付，待配送，30：配送中，40：已送达，50已关闭
     */
    public Integer getStatus() {
        return status;
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
     * 设置：关联配送员名
     */
    public void setDeliveryDistributorName(String deliveryDistributorName) {
        this.deliveryDistributorName = deliveryDistributorName;
    }

    /**
     * 获取：关联配送员名
     */
    public String getDeliveryDistributorName() {
        return deliveryDistributorName;
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

    /**
     * 设置：用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取：用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置：订单备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：订单备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置：订单创建时间
     */
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * 获取：订单创建时间
     */
    public Date getCreationTime() {
        return creationTime;
    }

    /**
     * 设置：更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }
}
