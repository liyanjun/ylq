package com.yunquanlai.admin.user.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户水票消费流水
 * 
 * @author weicc
 * @email 
 * @date 2018-07-20 18:14:10
 */
public class UserProductTicketFlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 设置：主键 ID
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
     * 设置：用户水票id
     */
	private Long userProductTicketId;
    /**
     * 设置：关联商品 ID
     */
	private Long productInfoId;
    /**
     * 设置：关联商品名
     */
	private String productName;
    /**
     * 设置：使用数量
     */
	private Integer usedCount;
    /**
     * 设置：剩余数量
     */
	private Integer remainderCount;
    /**
     * 设置：使用时间
     */
	private Date creationTime;

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
	 * 设置：用户水票id
	 */
	public void setUserProductTicketId(Long userProductTicketId) {
		this.userProductTicketId = userProductTicketId;
	}
	/**
	 * 获取：用户水票id
	 */
	public Long getUserProductTicketId() {
		return userProductTicketId;
	}
	/**
	 * 设置：关联商品 ID
	 */
	public void setProductInfoId(Long productInfoId) {
		this.productInfoId = productInfoId;
	}
	/**
	 * 获取：关联商品 ID
	 */
	public Long getProductInfoId() {
		return productInfoId;
	}
	/**
	 * 设置：关联商品名
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 获取：关联商品名
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置：使用数量
	 */
	public void setUsedCount(Integer usedCount) {
		this.usedCount = usedCount;
	}
	/**
	 * 获取：使用数量
	 */
	public Integer getUsedCount() {
		return usedCount;
	}
	/**
	 * 设置：剩余数量
	 */
	public void setRemainderCount(Integer remainderCount) {
		this.remainderCount = remainderCount;
	}
	/**
	 * 获取：剩余数量
	 */
	public Integer getRemainderCount() {
		return remainderCount;
	}
	/**
	 * 设置：使用时间
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	/**
	 * 获取：使用时间
	 */
	public Date getCreationTime() {
		return creationTime;
	}
}
