package com.yunquanlai.admin.order.entity;

import java.io.Serializable;



/**
 * 订单商品信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 22:42:21
 */
public class OrderProductDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 设置：主键 ID
     */
	private Long id;
    /**
     * 设置：对应商品 ID
     */
	private Long productInfoId;
    /**
     * 设置：商品名称
     */
	private String productName;
    /**
     * 设置：商品数量
     */
	private Integer count;
	/**
	 * 设置：商品规格
	 */
	private Integer bucketType;
    /**
     * 设置：
     */
	private Long orderInfoId;

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
	 * 设置：对应商品 ID
	 */
	public void setProductInfoId(Long productInfoId) {
		this.productInfoId = productInfoId;
	}
	/**
	 * 获取：对应商品 ID
	 */
	public Long getProductInfoId() {
		return productInfoId;
	}
	/**
	 * 设置：商品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 获取：商品名称
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置：商品数量
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获取：商品数量
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * 设置：
	 */
	public void setOrderInfoId(Long orderInfoId) {
		this.orderInfoId = orderInfoId;
	}
	/**
	 * 获取：
	 */
	public Long getOrderInfoId() {
		return orderInfoId;
	}

	public Integer getBucketType() {
		return bucketType;
	}

	public void setBucketType(Integer bucketType) {
		this.bucketType = bucketType;
	}
}
