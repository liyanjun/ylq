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

    public static final int STATUS_NEW = 10;
    public static final int STATUS_PAID = 20;
    public static final int STATUS_ON_DELIVERY = 30;
    public static final int STATUS_DELIVERY_END = 40;
    public static final int STATUS_CLOSE = 50;
    public static final int STATUS_COMMENT = 60;


    public static final int TYPE_NORMAL = 10;
    public static final int TYPE_EXCEPTION = 20;

    public static final int PAY_TYPE_CASH = 10;
    public static final int PAY_TYPE_TICKET = 20;

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
     * 订单中的押金金额
     */
    private BigDecimal deposit;
    /**
     * 押金桶数
     */
    private BigDecimal bucketNum;
    /**
     * 订单购买商品详情
     */
    private String detail;
    /**
     * 设置：订单状态，10：新创建，20：已支付，待配送，30：配送中，40：已送达，50：已关闭，60：已评论
     */
    private Integer status;
    /**
     * 设置：订单状态类型，10：正常，20：异常
     */
    private Integer type;
    /**
     * 设置：订单支付类型，10：现金，20：水票
     */
    private Integer payType;
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
     * 设置：订单异常信息
     */
    private String exception;
    /**
     * 设置：订单创建时间
     */
    private Date creationTime;
    /**
     * 设置：订单支付时间
     */
    private Date paidTime;
    /**
     * 设置：订单分配时间
     */
    private Date distributeTime;
    /**
     * 设置：订单配送结束时间
     */
    private Date deliveryEndTime;
    /**
     * 设置：订单配送结束时间
     */
    private Date closeTime;

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
     * 设置：订单状态类型，10：正常，20：异常
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：订单状态类型，10：正常，20：异常
     */
    public Integer getType() {
        return type;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
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
     * 设置：订单异常信息
     */
    public void setException(String exception) {
        this.exception = exception;
    }

    /**
     * 获取：订单异常信息
     */
    public String getException() {
        return exception;
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
     * 设置：订单分配时间
     */
    public void setDistributeTime(Date distributeTime) {
        this.distributeTime = distributeTime;
    }

    /**
     * 获取：订单分配时间
     */
    public Date getDistributeTime() {
        return distributeTime;
    }

    /**
     * 设置：订单配送结束时间
     */
    public void setDeliveryEndTime(Date deliveryEndTime) {
        this.deliveryEndTime = deliveryEndTime;
    }

    /**
     * 获取：订单配送结束时间
     */
    public Date getDeliveryEndTime() {
        return deliveryEndTime;
    }

    public Date getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(Date paidTime) {
        this.paidTime = paidTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setBucketNum(BigDecimal bucketNum) {
        this.bucketNum = bucketNum;
    }

    public BigDecimal getBucketNum() {
        return bucketNum;
    }
}
