package com.yunquanlai.api.comsumer.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 下单信息
 */
public class OrderVO {
    /**
     * 订单地址
     */
    private String address;
    /**
     * 收货人
     */
    private String name;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * loc.latlng.lat
     */
    private BigDecimal locationX;
    /**
     * loc.latlng.lng
     */
    private BigDecimal locationY;
    /**
     * 收货电话
     */
    private String phone;
    /**
     * 备注
     */
    private String remark;
    /**
     * 配送时间
     */
    private String deliveryTime;
    /**
     * 是否需要发票
     */
    private Integer needBill;
    /**
     * 支付类型：10：现金，20：水票
     */
    private Integer payType;
    /**
     * 水票 ID
     */
    private Integer ticketId;
    /**
     * 防重复提交token
     */
    private String orderToken;
    /**
     * 用户选择押金
     */
    private BigDecimal deposit;
    /**
     * 押金桶数
     */
    private BigDecimal bucketNum;
    /**
     * 购买产品及数量
     */
    private List<ProductOrderVO> productOrderVOList;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getNeedBill() {
        return needBill;
    }

    public void setNeedBill(Integer needBill) {
        this.needBill = needBill;
    }

    public List<ProductOrderVO> getProductOrderVOList() {
        return productOrderVOList;
    }

    public void setProductOrderVOList(List<ProductOrderVO> productOrderVOList) {
        this.productOrderVOList = productOrderVOList;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public BigDecimal getLocationX() {
        return locationX;
    }

    public void setLocationX(BigDecimal locationX) {
        this.locationX = locationX;
    }

    public BigDecimal getLocationY() {
        return locationY;
    }

    public void setLocationY(BigDecimal locationY) {
        this.locationY = locationY;
    }

    public String getOrderToken() {
        return orderToken;
    }

    public void setOrderToken(String orderToken) {
        this.orderToken = orderToken;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getBucketNum() {
        return bucketNum;
    }

    public void setBucketNum(BigDecimal bucketNum) {
        this.bucketNum = bucketNum;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }
}
