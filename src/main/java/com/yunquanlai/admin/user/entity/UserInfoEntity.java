package com.yunquanlai.admin.user.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 客户信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 15:44:08
 */
public class UserInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 设置：
     */
	private Long id;
    /**
     * 设置：用户名(从小程序过来是就是微信昵称)
     */
	private String username;
    /**
     * 设置：用户绑定手机号
     */
	private String phone;
    /**
     * 设置：用户微信 ID
     */
	private String uid;
    /**
     * 设置：用户状态：0：启用，1：禁用
     */
	private Integer status;
    /**
     * 设置：总押金金额
     */
	private BigDecimal depositAmount;
    /**
     * 设置：可用押金金额
     */
	private BigDecimal enableDepositAmount;
    /**
     * 设置：不可用押金金额
     */
	private BigDecimal disableDepositAmout;
    /**
     * 设置：持有空桶数
     */
	private Integer emptyBucketNumber;
    /**
     * 设置：用户注册时间
     */
	private Date creationTime;

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
	 * 设置：用户名(从小程序过来是就是微信昵称)
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：用户名(从小程序过来是就是微信昵称)
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：用户绑定手机号
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：用户绑定手机号
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：用户微信 ID
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * 获取：用户微信 ID
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * 设置：用户状态：0：启用，1：禁用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：用户状态：0：启用，1：禁用
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：总押金金额
	 */
	public void setDepositAmount(BigDecimal depositAmount) {
		this.depositAmount = depositAmount;
	}
	/**
	 * 获取：总押金金额
	 */
	public BigDecimal getDepositAmount() {
		return depositAmount;
	}
	/**
	 * 设置：可用押金金额
	 */
	public void setEnableDepositAmount(BigDecimal enableDepositAmount) {
		this.enableDepositAmount = enableDepositAmount;
	}
	/**
	 * 获取：可用押金金额
	 */
	public BigDecimal getEnableDepositAmount() {
		return enableDepositAmount;
	}
	/**
	 * 设置：不可用押金金额
	 */
	public void setDisableDepositAmout(BigDecimal disableDepositAmout) {
		this.disableDepositAmout = disableDepositAmout;
	}
	/**
	 * 获取：不可用押金金额
	 */
	public BigDecimal getDisableDepositAmout() {
		return disableDepositAmout;
	}
	/**
	 * 设置：持有空桶数
	 */
	public void setEmptyBucketNumber(Integer emptyBucketNumber) {
		this.emptyBucketNumber = emptyBucketNumber;
	}
	/**
	 * 获取：持有空桶数
	 */
	public Integer getEmptyBucketNumber() {
		return emptyBucketNumber;
	}
	/**
	 * 设置：用户注册时间
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	/**
	 * 获取：用户注册时间
	 */
	public Date getCreationTime() {
		return creationTime;
	}
}
