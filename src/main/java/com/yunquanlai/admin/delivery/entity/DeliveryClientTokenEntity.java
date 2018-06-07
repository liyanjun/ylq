package com.yunquanlai.admin.delivery.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-07 21:02:38
 */
public class DeliveryClientTokenEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 设置：
     */
	private Long id;
    /**
     * 设置：
     */
	private String token;
    /**
     * 设置：
     */
	private Long deliveryDistributorId;
    /**
     * 设置：
     */
	private Date expireTime;
    /**
     * 设置：
     */
	private Date updateTime;

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
	 * 设置：
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * 获取：
	 */
	public String getToken() {
		return token;
	}
	/**
	 * 设置：
	 */
	public void setDeliveryDistributorId(Long deliveryDistributorId) {
		this.deliveryDistributorId = deliveryDistributorId;
	}
	/**
	 * 获取：
	 */
	public Long getDeliveryDistributorId() {
		return deliveryDistributorId;
	}
	/**
	 * 设置：
	 */
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	/**
	 * 获取：
	 */
	public Date getExpireTime() {
		return expireTime;
	}
	/**
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
