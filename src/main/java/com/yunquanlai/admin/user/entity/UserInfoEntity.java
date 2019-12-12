package com.yunquanlai.admin.user.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
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
	 * openId
	 */
	private String openId;
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
	private BigDecimal disableDepositAmount;
    /**
     * 设置：持有空桶数
     */
	private Integer emptyBucketNumber;
    /**
     * 设置：用户注册时间
     */
	private Date creationTime;
    /**
     * 设置：推荐人ID
     */
	private Long recommenderID;
    /**
     * 设置：推荐人姓名
     */
	private String recommenderName;
    /**
     * 设置：是否是推荐人，10：是，20：否
     */
	private Integer isRecommender;

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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
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
	public void setDisableDepositAmount(BigDecimal disableDepositAmount) {
		this.disableDepositAmount = disableDepositAmount;
	}
	/**
	 * 获取：不可用押金金额
	 */
	public BigDecimal getDisableDepositAmount() {
		return disableDepositAmount;
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

    public Long getRecommenderID() {
        return recommenderID;
    }

    public void setRecommenderID(Long recommenderID) {
        this.recommenderID = recommenderID;
    }

    public String getRecommenderName() {
        return recommenderName;
    }

    public void setRecommenderName(String recommenderName) {
        this.recommenderName = recommenderName;
    }

    public Integer getIsRecommender() {
        return isRecommender;
    }

    public void setIsRecommender(Integer isRecommender) {
        this.isRecommender = isRecommender;
    }
}
