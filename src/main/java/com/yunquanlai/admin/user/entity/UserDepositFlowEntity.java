package com.yunquanlai.admin.user.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 客户押金流水信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 15:44:08
 */
public class UserDepositFlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 设置：
     */
	private Long id;
    /**
     * 设置：流水类型，101：押金充值，2：押金提现，3：归还空桶，4：订水（等价于获取空桶）
     */
	private Integer type;
    /**
     * 设置：流水前押金信息
     */
	private String beforeDeposit;
    /**
     * 设置：流水号押金信息
     */
	private String afterDeposit;
    /**
     * 设置：关联用户 ID
     */
	private Long userInfoId;
    /**
     * 设置：流水时间
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
	 * 设置：流水类型，101：押金充值，2：押金提现，3：归还空桶，4：订水（等价于获取空桶）
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：流水类型，101：押金充值，2：押金提现，3：归还空桶，4：订水（等价于获取空桶）
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：流水前押金信息
	 */
	public void setBeforeDeposit(String beforeDeposit) {
		this.beforeDeposit = beforeDeposit;
	}
	/**
	 * 获取：流水前押金信息
	 */
	public String getBeforeDeposit() {
		return beforeDeposit;
	}
	/**
	 * 设置：流水号押金信息
	 */
	public void setAfterDeposit(String afterDeposit) {
		this.afterDeposit = afterDeposit;
	}
	/**
	 * 获取：流水号押金信息
	 */
	public String getAfterDeposit() {
		return afterDeposit;
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
	 * 设置：流水时间
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	/**
	 * 获取：流水时间
	 */
	public Date getCreationTime() {
		return creationTime;
	}
}
