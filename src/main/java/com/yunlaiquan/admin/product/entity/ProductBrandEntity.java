package com.yunlaiquan.admin.product.entity;

import java.io.Serializable;



/**
 * 商品品牌信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-05-30 23:09:11
 */
public class ProductBrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 设置：主键 ID
     */
	private Long id;
    /**
     * 设置：商品名
     */
	private String name;
    /**
     * 设置：备注
     */
	private String remark;

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
	 * 设置：商品名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：商品名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
}
