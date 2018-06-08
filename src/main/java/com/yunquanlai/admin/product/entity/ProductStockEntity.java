package com.yunquanlai.admin.product.entity;

import com.yunquanlai.admin.delivery.entity.DeliveryEndpointEntity;

import java.io.Serializable;


/**
 * 商品库存信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-01 22:49:34
 */
public class ProductStockEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 设置：主键ID
     */
	private Long id;
    /**
     * 设置：关联商品ID
     */
	private Long productInfoId;
    /**
     * 设置：商品名
     */
	private String productName;
    /**
     * 设置：关联配送点 ID
     */
	private Long deliveryEndpointId;
    /**
     * 设置：配送点名
     */
	private String deliveryName;
    /**
     * 设置：库存数
     */
	private Integer count;

	/**
	 * 添加库存数（负数时为扣减库存）
	 */
	private Integer countAdd;

	public ProductStockEntity(){

	}

    public ProductStockEntity(ProductInfoEntity productInfoEntity, DeliveryEndpointEntity deliveryEndpoint) {
		this.setProductInfoId(productInfoEntity.getId());
		this.setProductName(productInfoEntity.getName());
		this.setDeliveryName(deliveryEndpoint.getName());
		this.setDeliveryEndpointId(deliveryEndpoint.getId());
		this.setCount(0);
    }

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
	 * 设置：关联商品ID
	 */
	public void setProductInfoId(Long productInfoId) {
		this.productInfoId = productInfoId;
	}
	/**
	 * 获取：关联商品ID
	 */
	public Long getProductInfoId() {
		return productInfoId;
	}
	/**
	 * 设置：商品名
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 获取：商品名
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置：关联配送点 ID
	 */
	public void setDeliveryEndpointId(Long deliveryEndpointId) {
		this.deliveryEndpointId = deliveryEndpointId;
	}
	/**
	 * 获取：关联配送点 ID
	 */
	public Long getDeliveryEndpointId() {
		return deliveryEndpointId;
	}
	/**
	 * 设置：配送点名
	 */
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	/**
	 * 获取：配送点名
	 */
	public String getDeliveryName() {
		return deliveryName;
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

	public Integer getCountAdd() {
		return countAdd;
	}

	public void setCountAdd(Integer countAdd) {
		this.countAdd = countAdd;
	}
}
