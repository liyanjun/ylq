package com.yunquanlai.admin.order.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 订单手工处理流水记录表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 22:42:21
 */
public class OrderOperateFlowEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 设置：主键 ID
     */
	private Long id;
    /**
     * 设置：处理类型，10：手工选定配送员，20：取消订单
     */
	private Integer type;
    /**
     * 设置：手工操作前状态
     */
	private Integer beforeStatus;
    /**
     * 设置：手工操作后状态
     */
	private Integer afterStatus;
    /**
     * 设置：操作备注信息
     */
	private String remark;
    /**
     * 设置：操作时间
     */
	private Date operatorTime;
    /**
     * 设置：操作人 ID
     */
	private Long operatorId;
    /**
     * 设置：操作人名
     */
	private String operatorName;

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
	 * 设置：处理类型，10：手工选定配送员，20：取消订单
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：处理类型，10：手工选定配送员，20：取消订单
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：手工操作前状态
	 */
	public void setBeforeStatus(Integer beforeStatus) {
		this.beforeStatus = beforeStatus;
	}
	/**
	 * 获取：手工操作前状态
	 */
	public Integer getBeforeStatus() {
		return beforeStatus;
	}
	/**
	 * 设置：手工操作后状态
	 */
	public void setAfterStatus(Integer afterStatus) {
		this.afterStatus = afterStatus;
	}
	/**
	 * 获取：手工操作后状态
	 */
	public Integer getAfterStatus() {
		return afterStatus;
	}
	/**
	 * 设置：操作备注信息
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：操作备注信息
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：操作时间
	 */
	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}
	/**
	 * 获取：操作时间
	 */
	public Date getOperatorTime() {
		return operatorTime;
	}
	/**
	 * 设置：操作人 ID
	 */
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}
	/**
	 * 获取：操作人 ID
	 */
	public Long getOperatorId() {
		return operatorId;
	}
	/**
	 * 设置：操作人名
	 */
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	/**
	 * 获取：操作人名
	 */
	public String getOperatorName() {
		return operatorName;
	}
}
