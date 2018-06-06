package com.yunquanlai.admin.delivery.entity;

import com.yunquanlai.utils.validator.group.AddGroup;
import com.yunquanlai.utils.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 配送点信息
 *
 * @author weicc
 * @email
 * @date 2018-05-31 15:26:38
 */
public class DeliveryEndpointEntity implements Serializable, Comparable<DeliveryEndpointEntity> {
    private static final long serialVersionUID = 1L;

    /**
     * 设置：主键 ID
     */
    private Long id;
    /**
     * 设置：配送点名
     */
    @NotBlank(message="配送点名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String name;
    /**
     * 设置：配送点坐标x
     */
    private BigDecimal locationX;
    /**
     * 设置：配送点坐标y
     */
    private BigDecimal locationY;
    /**
     * 设置：
     */
    private String remark;

    /**
     * 距离，只参与运算，不存入数据库
     */
    private BigDecimal distance;

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
     * 设置：配送点名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：配送点名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：配送点坐标x
     */
    public void setLocationX(BigDecimal locationX) {
        this.locationX = locationX;
    }

    /**
     * 获取：配送点坐标x
     */
    public BigDecimal getLocationX() {
        return locationX;
    }

    /**
     * 设置：配送点坐标y
     */
    public void setLocationY(BigDecimal locationY) {
        this.locationY = locationY;
    }

    /**
     * 获取：配送点坐标y
     */
    public BigDecimal getLocationY() {
        return locationY;
    }

    /**
     * 设置：
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：
     */
    public String getRemark() {
        return remark;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(DeliveryEndpointEntity o) {
        return distance.subtract(o.getDistance()).intValue();
    }
}
