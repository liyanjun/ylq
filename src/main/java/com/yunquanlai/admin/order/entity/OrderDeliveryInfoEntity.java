package com.yunquanlai.admin.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 订单配送信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 22:42:21
 */
public class OrderDeliveryInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final Integer STATUS_NEW = 10;
	public static final Integer STATUS_ON_DELIVERY = 30;
	public static final Integer STATUS_DISTRIBUTION = 40;
	public static final Integer STATUS_CLOSE = 50;

    /**
     * 设置：主键 ID
     */
	private Integer id;
    /**
     * 设置：收货人姓名
     */
	private String name;
    /**
     * 设置：订单地址信息（拼凑的用于在订单列表显示的）
     */
	private String address;
    /**
     * 设置：收货人性别
     */
	private Integer sex;
    /**
     * 设置：用户手机号
     */
	private String phone;
    /**
     * 设置：订单配送坐标x
     */
	private BigDecimal locationX;
    /**
     * 设置：订单配送坐标y
     */
	private BigDecimal locationY;
    /**
     * 设置：配送单状态，10：未分配，30：配送中，40：已配送，50：已关闭
     */
	private Integer status;
    /**
     * 设置：配送单备注
     */
	private String remark;
    /**
     * 设置：配送单创建时间
     */
	private Date creationTime;
    /**
     * 设置：期望配送时间
     */
	private Date deliveryTime;
    /**
     * 设置：关联配送员 ID
     */
	private Integer deliveryDistributorId;
    /**
     * 设置：关联订单 ID
     */
	private Long orderInfoId;
    /**
     * 设置：关联用户 ID
     */
	private Long userInfoId;

	/**
	 * 设置：主键 ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键 ID
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：收货人姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：收货人姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：订单地址信息（拼凑的用于在订单列表显示的）
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：订单地址信息（拼凑的用于在订单列表显示的）
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：收货人性别
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * 获取：收货人性别
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * 设置：用户手机号
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：用户手机号
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：订单配送坐标x
	 */
	public void setLocationX(BigDecimal locationX) {
		this.locationX = locationX;
	}
	/**
	 * 获取：订单配送坐标x
	 */
	public BigDecimal getLocationX() {
		return locationX;
	}
	/**
	 * 设置：订单配送坐标y
	 */
	public void setLocationY(BigDecimal locationY) {
		this.locationY = locationY;
	}
	/**
	 * 获取：订单配送坐标y
	 */
	public BigDecimal getLocationY() {
		return locationY;
	}
	/**
	 * 设置：配送单状态，10：未分配，20：配送中，30：已配送
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：配送单状态，10：未分配，20：配送中，30：已配送
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：配送单备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：配送单备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：配送单创建时间
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	/**
	 * 获取：配送单创建时间
	 */
	public Date getCreationTime() {
		return creationTime;
	}
	/**
	 * 设置：期望配送时间
	 */
	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	/**
	 * 获取：期望配送时间
	 */
	public Date getDeliveryTime() {
		return deliveryTime;
	}
	/**
	 * 设置：关联配送员 ID
	 */
	public void setDeliveryDistributorId(Integer deliveryDistributorId) {
		this.deliveryDistributorId = deliveryDistributorId;
	}
	/**
	 * 获取：关联配送员 ID
	 */
	public Integer getDeliveryDistributorId() {
		return deliveryDistributorId;
	}
	/**
	 * 设置：关联订单 ID
	 */
	public void setOrderInfoId(Long orderInfoId) {
		this.orderInfoId = orderInfoId;
	}
	/**
	 * 获取：关联订单 ID
	 */
	public Long getOrderInfoId() {
		return orderInfoId;
	}
	/**
	 * 设置：关联用户 ID
	 */
	public void setUserInfoId(Long userInfoId) {
		this.userInfoId = userInfoId;
	}
	/**
	 * 获取：关联用户 ID
	 */
	public Long getUserInfoId() {
		return userInfoId;
	}
}
