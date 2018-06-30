package com.yunquanlai.admin.comment.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 配送员评价
 * 
 * @author weicc
 * @email 
 * @date 2018-06-10 16:28:27
 */
public class CommentDeliveryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 设置：主键ID
     */
	private Long id;
	/**
	 * 设置：用户ID
	 */
	private Long userId;
	/**
	 * 设置：用户名
	 */
	private String userName;
	/**
	 * 设置：用户头像(备用)
	 */
	private String headUrl;
	/**
     * 设置：配送员id
     */
	private Long deliveryDistributorId;
    /**
     * 设置：评论内容
     */
	private String comment;
    /**
     * 设置：打分，1-5分
     */
	private Integer level;
    /**
     * 设置：评论时间
     */
	private Date creationTime;

	/**
	 * 设置：主键ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：配送员id
	 */
	public void setDeliveryDistributorId(Long deliveryDistributorId) {
		this.deliveryDistributorId = deliveryDistributorId;
	}
	/**
	 * 获取：配送员id
	 */
	public Long getDeliveryDistributorId() {
		return deliveryDistributorId;
	}
	/**
	 * 设置：评论内容
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * 获取：评论内容
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * 设置：打分，1-5分
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
	 * 获取：打分，1-5分
	 */
	public Integer getLevel() {
		return level;
	}
	/**
	 * 设置：评论时间
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	/**
	 * 获取：评论时间
	 */
	public Date getCreationTime() {
		return creationTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
}
