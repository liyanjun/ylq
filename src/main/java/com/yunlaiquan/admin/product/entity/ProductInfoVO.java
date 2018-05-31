package com.yunlaiquan.admin.product.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 商品信息表表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-05-30 22:26:24
 */
public class ProductInfoVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private ProductInfoEntity productInfoEntity;

	private ProductDetailEntity productDetailEntity;

	public ProductInfoVO(){
		productInfoEntity = new ProductInfoEntity();
		productDetailEntity = new ProductDetailEntity();
	}

	public ProductInfoVO(ProductInfoEntity productInfoEntity, ProductDetailEntity productDetailEntity) {
		this.productInfoEntity = productInfoEntity;
		this.productDetailEntity = productDetailEntity;
	}

	/**
	 * 设置：主键ID
	 */
	public void setId(Long id) {
		productInfoEntity.setId(id);
	}
	/**
	 * 获取：主键ID
	 */
	public Long getId() {
		return productInfoEntity.getId();
	}
	/**
	 * 设置：商品名称
	 */
	public void setName(String name) {
		productInfoEntity.setName(name);
	}
	/**
	 * 获取：商品名称
	 */
	public String getName() {
		return productInfoEntity.getName();
	}

	/**
     * 商品主图
	 * @return
     */
	public String getImg() {
		return productInfoEntity.getImg();
	}

	/**
	 * 商品主图
	 * @return
	 */
	public void setImg(String img) {
		productInfoEntity.setImg(img);
	}

	/**
	 * 设置：标价
	 */
	public void setAmountShow(BigDecimal amountShow) {
		productInfoEntity.setAmountShow(amountShow);
	}
	/**
	 * 获取：标价
	 */
	public BigDecimal getAmountShow() {
		return productInfoEntity.getAmountShow();
	}
	/**
	 * 设置：实价
	 */
	public void setAmount(BigDecimal amount) {
		productInfoEntity.setAmount(amount);
	}
	/**
	 * 获取：实价
	 */
	public BigDecimal getAmount() {
		return productInfoEntity.getAmount();
	}
	/**
	 * 设置：商品品牌 ID
	 */
	public void setBrandId(Long brandId) {
		productInfoEntity.setBrandId(brandId);
	}
	/**
	 * 获取：商品品牌 ID
	 */
	public Long getBrandId() {
		return productInfoEntity.getBrandId();
	}
	/**
	 * 设置：桶类型，10：一次性桶，20：可回收桶
	 */
	public void setBucketType(Integer bucketType) {
		productInfoEntity.setBucketType(bucketType);
	}
	/**
	 * 获取：桶类型，10：一次性桶，20：可回收桶
	 */
	public Integer getBucketType() {
		return productInfoEntity.getBucketType();
	}
	/**
	 * 设置：配送费
	 */
	public void setDeliveryFee(BigDecimal deliveryFee) {
		productInfoEntity.setDeliveryFee(deliveryFee);
	}
	/**
	 * 获取：配送费
	 */
	public BigDecimal getDeliveryFee() {
		return productInfoEntity.getDeliveryFee();
	}

	public String getContent() {
		return productDetailEntity.getContent();
	}

	public void setContent(String content) {
		productDetailEntity.setContent(content);
	}

	/**
	 * 设置：
	 */
	public void setBanner(String banner) {
		productDetailEntity.setBanner(banner);
	}
	/**
	 * 获取：
	 */
	public String getBanner() {
		return productDetailEntity.getBanner();
	}

	public ProductInfoEntity getProductInfoEntity() {
		return productInfoEntity;
	}

	public void setProductInfoEntity(ProductInfoEntity productInfoEntity) {
		this.productInfoEntity = productInfoEntity;
	}

	public ProductDetailEntity getProductDetailEntity() {
		return productDetailEntity;
	}

	public void setProductDetailEntity(ProductDetailEntity productDetailEntity) {
		this.productDetailEntity = productDetailEntity;
	}
}
