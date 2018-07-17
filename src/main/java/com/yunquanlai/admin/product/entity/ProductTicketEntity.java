package com.yunquanlai.admin.product.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 商品水票信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-07-17 12:11:07
 */
public class ProductTicketEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 设置：
     */
	private Long id;
    /**
     * 设置：水票编号
     */
	private String productTicketNum;
    /**
     * 设置：水票图片(备用)
     */
	private String img;
    /**
     * 设置：水票标题
     */
	private String title;
    /**
     * 设置：水票副标题
     */
	private String subtitle;
    /**
     * 设置：关联产品 ID
     */
	private Long productInfoId;
    /**
     * 设置：关联商品名称
     */
	private String productInfoName;
    /**
     * 设置：购买桶数
     */
	private Integer purchaseCount;
    /**
     * 设置：赠送的桶数
     */
	private Integer giftCount;
    /**
     * 设置：单次最小配送桶数
     */
	private Integer minDilivery;
    /**
     * 设置：每配送一桶，推广人获得的水币
     */
	private Integer waterCoin;
    /**
     * 设置：售卖上下架状态，10：新创建，20：商品上架，30：商品下架
     */
	private Integer status;
    /**
     * 设置：包含产品数量（如买5送二）这里就应该是7
     */
	private Integer cout;
    /**
     * 设置：水票价格
     */
	private BigDecimal amout;
    /**
     * 设置：使用须知
     */
	private String notes;
    /**
     * 设置：使用说明
     */
	private String instructions;
    /**
     * 设置：创建时间
     */
	private Date creationTime;
    /**
     * 设置：使用截止日期
     */
	private Date deadline;
    /**
     * 设置：备注
     */
	private String remarks;

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
	 * 设置：水票编号
	 */
	public void setProductTicketNum(String productTicketNum) {
		this.productTicketNum = productTicketNum;
	}
	/**
	 * 获取：水票编号
	 */
	public String getProductTicketNum() {
		return productTicketNum;
	}
	/**
	 * 设置：水票图片(备用)
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * 获取：水票图片(备用)
	 */
	public String getImg() {
		return img;
	}
	/**
	 * 设置：水票标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：水票标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：水票副标题
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	/**
	 * 获取：水票副标题
	 */
	public String getSubtitle() {
		return subtitle;
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
	 * 设置：关联商品名称
	 */
	public void setProductInfoName(String productInfoName) {
		this.productInfoName = productInfoName;
	}
	/**
	 * 获取：关联商品名称
	 */
	public String getProductInfoName() {
		return productInfoName;
	}
	/**
	 * 设置：购买桶数
	 */
	public void setPurchaseCount(Integer purchaseCount) {
		this.purchaseCount = purchaseCount;
	}
	/**
	 * 获取：购买桶数
	 */
	public Integer getPurchaseCount() {
		return purchaseCount;
	}
	/**
	 * 设置：赠送的桶数
	 */
	public void setGiftCount(Integer giftCount) {
		this.giftCount = giftCount;
	}
	/**
	 * 获取：赠送的桶数
	 */
	public Integer getGiftCount() {
		return giftCount;
	}
	/**
	 * 设置：单次最小配送桶数
	 */
	public void setMinDilivery(Integer minDilivery) {
		this.minDilivery = minDilivery;
	}
	/**
	 * 获取：单次最小配送桶数
	 */
	public Integer getMinDilivery() {
		return minDilivery;
	}
	/**
	 * 设置：每配送一桶，推广人获得的水币
	 */
	public void setWaterCoin(Integer waterCoin) {
		this.waterCoin = waterCoin;
	}
	/**
	 * 获取：每配送一桶，推广人获得的水币
	 */
	public Integer getWaterCoin() {
		return waterCoin;
	}
	/**
	 * 设置：售卖上下架状态，10：新创建，20：商品上架，30：商品下架
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：售卖上下架状态，10：新创建，20：商品上架，30：商品下架
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：包含产品数量（如买5送二）这里就应该是7
	 */
	public void setCout(Integer cout) {
		this.cout = cout;
	}
	/**
	 * 获取：包含产品数量（如买5送二）这里就应该是7
	 */
	public Integer getCout() {
		return cout;
	}
	/**
	 * 设置：水票价格
	 */
	public void setAmout(BigDecimal amout) {
		this.amout = amout;
	}
	/**
	 * 获取：水票价格
	 */
	public BigDecimal getAmout() {
		return amout;
	}
	/**
	 * 设置：使用须知
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/**
	 * 获取：使用须知
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * 设置：使用说明
	 */
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	/**
	 * 获取：使用说明
	 */
	public String getInstructions() {
		return instructions;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreationTime() {
		return creationTime;
	}
	/**
	 * 设置：使用截止日期
	 */
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	/**
	 * 获取：使用截止日期
	 */
	public Date getDeadline() {
		return deadline;
	}
	/**
	 * 设置：备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注
	 */
	public String getRemarks() {
		return remarks;
	}
}
