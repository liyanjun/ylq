package com.yunquanlai.admin.delivery.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 配送员收入信息流水
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-11 14:51:36
 */
public class DeliveryDistributorFinancialFlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 设置：
     */
	private Long id;
    /**
     * 设置：流水前金额
     */
	private BigDecimal beforeAmount;
    /**
     * 设置：流水后金额
     */
	private BigDecimal afterAmount;
    /**
     * 设置：流水类型，10：收益，20：发工资
     */
	private Integer type;
    /**
     * 设置：流水金额
     */
	private BigDecimal amount;
    /**
     * 设置：配送员 ID
     */
	private Long deliveryDistributorId;

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
	 * 设置：流水前金额
	 */
	public void setBeforeAmount(BigDecimal beforeAmount) {
		this.beforeAmount = beforeAmount;
	}
	/**
	 * 获取：流水前金额
	 */
	public BigDecimal getBeforeAmount() {
		return beforeAmount;
	}
	/**
	 * 设置：流水后金额
	 */
	public void setAfterAmount(BigDecimal afterAmount) {
		this.afterAmount = afterAmount;
	}
	/**
	 * 获取：流水后金额
	 */
	public BigDecimal getAfterAmount() {
		return afterAmount;
	}
	/**
	 * 设置：流水类型，10：收益，20：发工资
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：流水类型，10：收益，20：提现申请，30：提现成功
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：流水金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * 获取：流水金额
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * 设置：配送员 ID
	 */
	public void setDeliveryDistributorId(Long deliveryDistributorId) {
		this.deliveryDistributorId = deliveryDistributorId;
	}
	/**
	 * 获取：配送员 ID
	 */
	public Long getDeliveryDistributorId() {
		return deliveryDistributorId;
	}
}
