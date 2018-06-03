package com.yunquanlai.admin.product.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 商品库存流水信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-02 22:31:01
 */
public class ProductStockFlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 设置：主键ID
     */
	private Long id;
    /**
     * 设置：关联库存ID
     */
	private Long productStockId;
    /**
     * 设置：库存数
     */
	private Integer count;
    /**
     * 设置：库存变动类型，0：添加，1：减少
     */
	private Integer type;
    /**
     * 设置：改变前值
     */
	private Integer beforeCount;
    /**
     * 设置：改变后值
     */
	private Integer afterCount;
    /**
     * 设置：库存变动时间
     */
	private Date creationTime;
    /**
     * 设置：操作人名称
     */
	private String opreator;
    /**
     * 设置：
     */
	private Long opreatorId;

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

	public Long getProductStockId() {
		return productStockId;
	}

	public void setProductStockId(Long productStockId) {
		this.productStockId = productStockId;
	}

	/**
	 * 设置：库存数
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获取：库存数
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * 设置：库存变动类型，0：添加，1：减少
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：库存变动类型，0：添加，1：减少
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：改变前值
	 */
	public void setBeforeCount(Integer beforeCount) {
		this.beforeCount = beforeCount;
	}
	/**
	 * 获取：改变前值
	 */
	public Integer getBeforeCount() {
		return beforeCount;
	}
	/**
	 * 设置：改变后值
	 */
	public void setAfterCount(Integer afterCount) {
		this.afterCount = afterCount;
	}
	/**
	 * 获取：改变后值
	 */
	public Integer getAfterCount() {
		return afterCount;
	}
	/**
	 * 设置：库存变动时间
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	/**
	 * 获取：库存变动时间
	 */
	public Date getCreationTime() {
		return creationTime;
	}
	/**
	 * 设置：操作人名称
	 */
	public void setOpreator(String opreator) {
		this.opreator = opreator;
	}
	/**
	 * 获取：操作人名称
	 */
	public String getOpreator() {
		return opreator;
	}
	/**
	 * 设置：
	 */
	public void setOpreatorId(Long opreatorId) {
		this.opreatorId = opreatorId;
	}
	/**
	 * 获取：
	 */
	public Long getOpreatorId() {
		return opreatorId;
	}
}
