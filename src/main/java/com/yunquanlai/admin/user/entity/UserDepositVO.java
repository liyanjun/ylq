package com.yunquanlai.admin.user.entity;

import java.math.BigDecimal;

/**
 * 
 * @author  weicc
 * @date  2018/6/7 16:50
 **/

/**
 * 客户押金信息显示表
 */
public class UserDepositVO {
    private static final long serialVersionUID = 1L;

    /**
     * 设置：主键 ID
     */
    private Long id;
    /**
     * 设置：对应用户 ID
     */
    private Long userInfoId;
    /**
     * 设置：押金总额
     */
    private BigDecimal depositAmount;
    /**
     * 设置：有效的押金
     */
    private BigDecimal enableDepositAmount;
    /**
     * 设置：无效的押金（空桶没退回）
     */
    private BigDecimal disableDepositAmount;
    /**
     * 设置：用户持有空桶数
     */
    private Integer emptyBucketNumber;
    /**
     * 设置用户名
     */
    private String username;

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
     * 设置：对应用户 ID
     */
    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }
    /**
     * 获取：对应用户 ID
     */
    public Long getUserInfoId() {
        return userInfoId;
    }
    /**
     * 设置：押金总额
     */
    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }
    /**
     * 获取：押金总额
     */
    public BigDecimal getDepositAmount() {
        return depositAmount;
    }
    /**
     * 设置：有效的押金
     */
    public void setEnableDepositAmount(BigDecimal enableDepositAmount) {
        this.enableDepositAmount = enableDepositAmount;
    }
    /**
     * 获取：有效的押金
     */
    public BigDecimal getEnableDepositAmount() {
        return enableDepositAmount;
    }
    /**
     * 设置：无效的押金（空桶没退回）
     */
    public void setDisableDepositAmount(BigDecimal disableDepositAmount) {
        this.disableDepositAmount = disableDepositAmount;
    }
    /**
     * 获取：无效的押金（空桶没退回）
     */
    public BigDecimal getDisableDepositAmount() {
        return disableDepositAmount;
    }
    /**
     * 设置：用户持有空桶数
     */
    public void setEmptyBucketNumber(Integer emptyBucketNumber) {
        this.emptyBucketNumber = emptyBucketNumber;
    }
    /**
     * 获取：用户持有空桶数
     */
    public Integer getEmptyBucketNumber() {
        return emptyBucketNumber;
    }
    /**
     * 设置：用户名
     */
    public void setUsername(String username) { this.username = username; }
    /**
     * 获取：用户名
     */
    public String getUsername() { return username; }
}
