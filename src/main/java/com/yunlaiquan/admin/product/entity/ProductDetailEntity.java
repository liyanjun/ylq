package com.yunlaiquan.admin.product.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author liyanjun
 * @email 
 * @date 2018-05-31 21:53:08
 */
public class ProductDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 设置：
     */
	private Long id;
    /**
     * 设置：
     */
	private String banner;
    /**
     * 设置：
     */
	private String content;
    /**
     * 设置：
     */
	private Long productInfoId;

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
	public void setBanner(String banner) {
		this.banner = banner;
	}
	/**
	 * 获取：
	 */
	public String getBanner() {
		return banner;
	}
	/**
	 * 设置：
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：
	 */
	public void setProductInfoId(Long productInfoId) {
		this.productInfoId = productInfoId;
	}
	/**
	 * 获取：
	 */
	public Long getProductInfoId() {
		return productInfoId;
	}
}
