package com.yunquanlai.admin.user.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 推广审批申请表
 * 
 * @author weicc
 * @email 
 * @date 2018-07-21 18:00:31
 */
public class UserRecommendApprovalEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 设置：主键id
     */
	private Long id;
    /**
     * 设置：用户id
     */
	private Long userId;
    /**
     * 设置：用户名
     */
	private String username;
    /**
     * 设置：身份证正面照片
     */
	private String positiveIdPhoto;
    /**
     * 设置：身份证反面照片
     */
	private String reverseIdPhoto;
    /**
     * 设置：验证手机号码
     */
	private String phone;
    /**
     * 设置：备注个人能力
     */
	private String remarkPersonalAbility;
    /**
     * 设置：申请时间
     */
	private Date applicationTime;
    /**
     * 设置：审批时间
     */
	private Date approveTime;
    /**
     * 设置：审批意见
     */
	private String approveOpinion;
    /**
     * 设置：是否通过审批，10：通过，20：不通过
     */
	private Integer isApproved;

	/**
	 * 设置：主键id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：用户id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public Long getUserId() {
		return userId;
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
	 * 设置：身份证正面照片
	 */
	public void setPositiveIdPhoto(String positiveIdPhoto) {
		this.positiveIdPhoto = positiveIdPhoto;
	}
	/**
	 * 获取：身份证正面照片
	 */
	public String getPositiveIdPhoto() {
		return positiveIdPhoto;
	}
	/**
	 * 设置：身份证反面照片
	 */
	public void setReverseIdPhoto(String reverseIdPhoto) {
		this.reverseIdPhoto = reverseIdPhoto;
	}
	/**
	 * 获取：身份证反面照片
	 */
	public String getReverseIdPhoto() {
		return reverseIdPhoto;
	}
	/**
	 * 设置：验证手机号码
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：验证手机号码
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：备注个人能力
	 */
	public void setRemarkPersonalAbility(String remarkPersonalAbility) {
		this.remarkPersonalAbility = remarkPersonalAbility;
	}
	/**
	 * 获取：备注个人能力
	 */
	public String getRemarkPersonalAbility() {
		return remarkPersonalAbility;
	}
	/**
	 * 设置：申请时间
	 */
	public void setApplicationTime(Date applicationTime) {
		this.applicationTime = applicationTime;
	}
	/**
	 * 获取：申请时间
	 */
	public Date getApplicationTime() {
		return applicationTime;
	}
	/**
	 * 设置：审批时间
	 */
	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}
	/**
	 * 获取：审批时间
	 */
	public Date getApproveTime() {
		return approveTime;
	}
	/**
	 * 设置：审批意见
	 */
	public void setApproveOpinion(String approveOpinion) {
		this.approveOpinion = approveOpinion;
	}
	/**
	 * 获取：审批意见
	 */
	public String getApproveOpinion() {
		return approveOpinion;
	}
	/**
	 * 设置：是否通过审批，10：通过，20：不通过
	 */
	public void setIsApproved(Integer isApproved) {
		this.isApproved = isApproved;
	}
	/**
	 * 获取：是否通过审批，10：通过，20：不通过
	 */
	public Integer getIsApproved() {
		return isApproved;
	}
}
