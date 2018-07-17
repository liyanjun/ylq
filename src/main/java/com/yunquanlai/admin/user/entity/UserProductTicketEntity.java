package com.yunquanlai.admin.user.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 用户水票信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-07-17 12:11:07
 */
public class UserProductTicketEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 设置：
     */
	private Long id;
    /**
     * 设置：水票标题
     */
	private String productTicketTitle;
    /**
     * 设置：剩余数量
     */
	private Integer cout;
    /**
     * 设置：购买价格
     */
	private BigDecimal amount;
    /**
     * 设置：用户水票状态，10：新下单，20：已支付，30：已关闭
     */
	private Integer status;
    /**
     * 设置：关联产品 ID
     */
	private Long productInfoId;
    /**
     * 设置：购买时间
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
	 * 设置：水票标题
	 */
	public void setProductTicketTitle(String productTicketTitle) {
		this.productTicketTitle = productTicketTitle;
	}
	/**
	 * 获取：水票标题
	 */
	public String getProductTicketTitle() {
		return productTicketTitle;
	}
	/**
	 * 设置：剩余数量
	 */
	public void setCout(Integer cout) {
		this.cout = cout;
	}
	/**
	 * 获取：剩余数量
	 */
	public Integer getCout() {
		return cout;
	}
	/**
	 * 设置：购买价格
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * 获取：购买价格
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * 设置：用户水票状态，10：新下单，20：已支付，30：已关闭
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：用户水票状态，10：新下单，20：已支付，30：已关闭
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：关联产品 ID
	 */
	public void setProductInfoId(Long productInfoId) {
		this.productInfoId = productInfoId;
	}
	/**
	 * 获取：关联产品 ID
	 */
	public Long getProductInfoId() {
		return productInfoId;
	}
	/**
	 * 设置：购买时间
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	/**
	 * 获取：购买时间
	 */
	public Date getCreationTime() {
		return creationTime;
	}
}
