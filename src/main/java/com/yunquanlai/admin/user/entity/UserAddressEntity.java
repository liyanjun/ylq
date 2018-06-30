package com.yunquanlai.admin.user.entity;

import io.swagger.models.auth.In;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 客户地址信息表
 *
 * @author liyanjun
 * @email
 * @date 2018-06-04 15:44:08
 */
public class UserAddressEntity implements Serializable,Comparable<UserAddressEntity> {
    private static final long serialVersionUID = 1L;

    /**
     * 设置：主键 ID
     */
    private Long id;
    /**
     * 设置：地址坐标x值
     */
    private BigDecimal locationX;
    /**
     * 设置：地址坐标y值
     */
    private BigDecimal locationY;
    /**
     * 设置：地址描述
     */
    private String address;
    /**
     * 详细地址
     */
    private String addressDetail;
    /**
     * 设置：收货人姓名
     */
    private String name;
    /**
     * 性别，10：男，20：女
     */
    private Integer sex;
    /**
     * 设置：收货人电话
     */
    private String phone;
    /**
     * 设置：对应用户ID
     */
    private Long userInfoId;

    private BigDecimal distance;

    public UserAddressEntity() {

    }

    public UserAddressEntity(String name, String phone, String address,String addressDetail, BigDecimal locationX, BigDecimal locationY, Integer sex, Long userInfoId) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.addressDetail = addressDetail;
        this.locationX = locationX;
        this.locationY = locationY;
        this.sex = sex;
        this.userInfoId = userInfoId;
    }

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
     * 设置：地址坐标x值
     */
    public void setLocationX(BigDecimal locationX) {
        this.locationX = locationX;
    }

    /**
     * 获取：地址坐标x值
     */
    public BigDecimal getLocationX() {
        return locationX;
    }

    /**
     * 设置：地址坐标y值
     */
    public void setLocationY(BigDecimal locationY) {
        this.locationY = locationY;
    }

    /**
     * 获取：地址坐标y值
     */
    public BigDecimal getLocationY() {
        return locationY;
    }

    /**
     * 设置：地址描述
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：地址描述
     */
    public String getAddress() {
        return address;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
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
     * 设置：收货人电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取：收货人电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置：对应用户ID
     */
    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    /**
     * 获取：对应用户ID
     */
    public Long getUserInfoId() {
        return userInfoId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    @Override
    public int compareTo(UserAddressEntity o) {
        return distance.compareTo(o.getDistance());
    }
}
