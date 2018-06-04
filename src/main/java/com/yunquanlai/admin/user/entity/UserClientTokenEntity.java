package com.yunquanlai.admin.user.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 15:44:08
 */
public class UserClientTokenEntity implements Serializable {
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
	private Long userId;
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
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：
	 */
	public Long getUserId() {
		return userId;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
