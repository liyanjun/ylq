package com.yunquanlai.admin.user.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 客户押金流水信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-10 12:04:01
 */
public class UserEmptyBucketFlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 设置：
     */
	private Long id;
    /**
     * 设置：流水类型，10：归还空桶，20：获取空桶
     */
	private Integer type;
    /**
     * 设置：流水前空桶数
     */
	private Integer beforeEmptyBucket;
    /**
     * 设置：流水后空桶数
     */
	private Integer afterEmptyBucket;
    /**
     * 设置：操作空桶数
     */
	private Integer emptyBucketNumber;
    /**
     * 设置：关联用户 ID
     */
	private Long userInfoId;
    /**
     * 设置：操作关联 ID（如果为归还关联配送员用户 ID，如果是获取关联订单 ID）
     */
	private Long opreatorId;
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
	 * 设置：流水类型，10：归还空桶，20：获取空桶
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：流水类型，10：归还空桶，20：获取空桶
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：流水前空桶数
	 */
	public void setBeforeEmptyBucket(Integer beforeEmptyBucket) {
		this.beforeEmptyBucket = beforeEmptyBucket;
	}
	/**
	 * 获取：流水前空桶数
	 */
	public Integer getBeforeEmptyBucket() {
		return beforeEmptyBucket;
	}
	/**
	 * 设置：流水后空桶数
	 */
	public void setAfterEmptyBucket(Integer afterEmptyBucket) {
		this.afterEmptyBucket = afterEmptyBucket;
	}
	/**
	 * 获取：流水后空桶数
	 */
	public Integer getAfterEmptyBucket() {
		return afterEmptyBucket;
	}
	/**
	 * 设置：操作空桶数
	 */
	public void setEmptyBucketNumber(Integer emptyBucketNumber) {
		this.emptyBucketNumber = emptyBucketNumber;
	}
	/**
	 * 获取：操作空桶数
	 */
	public Integer getEmptyBucketNumber() {
		return emptyBucketNumber;
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
	 * 设置：操作关联 ID（如果为归还关联配送员用户 ID，如果是获取关联订单 ID）
	 */
	public void setOpreatorId(Long opreatorId) {
		this.opreatorId = opreatorId;
	}
	/**
	 * 获取：操作关联 ID（如果为归还关联配送员用户 ID，如果是获取关联订单 ID）
	 */
	public Long getOpreatorId() {
		return opreatorId;
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
