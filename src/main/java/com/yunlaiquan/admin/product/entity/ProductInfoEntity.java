package com.yunlaiquan.admin.product.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 商品信息表表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-05-30 17:16:47
 */
public class ProductInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	private Integer id;
	//商品名称
	private String name;
	//标价
	private BigDecimal amountShow;
	//实价
	private BigDecimal amount;
	//商品品牌 ID
	private Integer brandId;
	//商品品牌
	private String brandName;
	//桶类型，10：一次性桶，20：可回收桶
	private Integer bucketType;
	//配送费
	private BigDecimal deliveryFee;
	//商品创建时间
	private Date creationTime;
	//商品最后一次更新时间
	private Date updateTime;
	//商品创建人 ID
	private Integer creatorId;
	//商品创建人名
	private String creatorName;
	//商品更新人ID
	private Integer updatorId;
	//商品更新人名
	private String updatorName;

	/**
	 * 设置：主键ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键ID
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：商品名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：商品名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：标价
	 */
	public void setAmountShow(BigDecimal amountShow) {
		this.amountShow = amountShow;
	}
	/**
	 * 获取：标价
	 */
	public BigDecimal getAmountShow() {
		return amountShow;
	}
	/**
	 * 设置：实价
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * 获取：实价
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * 设置：商品品牌 ID
	 */
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	/**
	 * 获取：商品品牌 ID
	 */
	public Integer getBrandId() {
		return brandId;
	}
	/**
	 * 设置：商品品牌
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	/**
	 * 获取：商品品牌
	 */
	public String getBrandName() {
		return brandName;
	}
	/**
	 * 设置：桶类型，10：一次性桶，20：可回收桶
	 */
	public void setBucketType(Integer bucketType) {
		this.bucketType = bucketType;
	}
	/**
	 * 获取：桶类型，10：一次性桶，20：可回收桶
	 */
	public Integer getBucketType() {
		return bucketType;
	}
	/**
	 * 设置：配送费
	 */
	public void setDeliveryFee(BigDecimal deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
	/**
	 * 获取：配送费
	 */
	public BigDecimal getDeliveryFee() {
		return deliveryFee;
	}
	/**
	 * 设置：商品创建时间
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	/**
	 * 获取：商品创建时间
	 */
	public Date getCreationTime() {
		return creationTime;
	}
	/**
	 * 设置：商品最后一次更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：商品最后一次更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：商品创建人 ID
	 */
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
	/**
	 * 获取：商品创建人 ID
	 */
	public Integer getCreatorId() {
		return creatorId;
	}
	/**
	 * 设置：商品创建人名
	 */
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	/**
	 * 获取：商品创建人名
	 */
	public String getCreatorName() {
		return creatorName;
	}
	/**
	 * 设置：商品更新人ID
	 */
	public void setUpdatorId(Integer updatorId) {
		this.updatorId = updatorId;
	}
	/**
	 * 获取：商品更新人ID
	 */
	public Integer getUpdatorId() {
		return updatorId;
	}
	/**
	 * 设置：商品更新人名
	 */
	public void setUpdatorName(String updatorName) {
		this.updatorName = updatorName;
	}
	/**
	 * 获取：商品更新人名
	 */
	public String getUpdatorName() {
		return updatorName;
	}
}