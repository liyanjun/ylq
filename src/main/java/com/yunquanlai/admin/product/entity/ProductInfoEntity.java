package com.yunquanlai.admin.product.entity;

import com.yunquanlai.utils.validator.group.AddGroup;
import com.yunquanlai.utils.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 商品信息表表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-05-30 22:26:24
 */
public class ProductInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 设置：主键ID
     */
	private Long id;
    /**
     * 设置：商品名称
     */
	@NotBlank(message="商品名不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	/**
	 * 商品主图
	 */
    @NotBlank(message="商品主图不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String img;
    /**
     * 设置：标价
     */
	private BigDecimal amountShow;
    /**
     * 设置：实价
     */
    @NotNull(message="商品实价不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private BigDecimal amount;
    /**
     * 设置：商品品牌 ID
     */
    @NotNull(message="商品品牌ID不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private Long brandId;
    /**
     * 设置：商品品牌
     */
    @NotBlank(message="商品品牌名不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String brandName;
    /**
     * 设置：桶类型，10：一次性桶，20：可回收桶
     */
    @NotNull(message="桶类型不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private Integer bucketType;
    /**
     * 设置：配送费
     */
    @NotNull(message="配送费不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private BigDecimal deliveryFee;

	private Integer count;

    /**
     * 设置：商品创建时间
     */
	private Date creationTime;
    /**
     * 设置：商品最后一次更新时间
     */
	private Date updateTime;
    /**
     * 设置：商品创建人 ID
     */
	private Long creatorId;
    /**
     * 设置：商品创建人名
     */
	private String creatorName;
    /**
     * 设置：商品更新人ID
     */
	private Long updateId;
    /**
     * 设置：商品更新人名
     */
	private String updateName;

	/**
	 * 是否是一键送水产品，10：是，20：不是
	 */
    @NotNull(message="是否一键送水不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private Integer isQuick;

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
	 * 设置：商品名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：商品名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 商品主图
	 * @return
	 */
	public String getImg() {
		return img;
	}

	/**
	 * 商品主图
	 * @return
	 */
	public void setImg(String img) {
		this.img = img;
	}

	/**
	 * 设置：标价
	 */
	public void setAmountShow(BigDecimal amountShow) {
		this.amountShow = amountShow;
	}
	/**
	 * 获取：标价
	 */
	public BigDecimal getAmountShow() {
		return amountShow;
	}
	/**
	 * 设置：实价
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * 获取：实价
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * 设置：商品品牌 ID
	 */
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	/**
	 * 获取：商品品牌 ID
	 */
	public Long getBrandId() {
		return brandId;
	}
	/**
	 * 设置：商品品牌
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	/**
	 * 获取：商品品牌
	 */
	public String getBrandName() {
		return brandName;
	}
	/**
	 * 设置：桶类型，10：一次性桶，20：可回收桶
	 */
	public void setBucketType(Integer bucketType) {
		this.bucketType = bucketType;
	}
	/**
	 * 获取：桶类型，10：一次性桶，20：可回收桶
	 */
	public Integer getBucketType() {
		return bucketType;
	}
	/**
	 * 设置：配送费
	 */
	public void setDeliveryFee(BigDecimal deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
	/**
	 * 获取：配送费
	 */

	public BigDecimal getDeliveryFee() {
		return deliveryFee;
	}
	/**
	 * 设置：商品创建时间
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	/**
	 * 获取：商品创建时间
	 */
	public Date getCreationTime() {
		return creationTime;
	}
	/**
	 * 设置：商品最后一次更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：商品最后一次更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：商品创建人 ID
	 */
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	/**
	 * 获取：商品创建人 ID
	 */
	public Long getCreatorId() {
		return creatorId;
	}
	/**
	 * 设置：商品创建人名
	 */
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	/**
	 * 获取：商品创建人名
	 */
	public String getCreatorName() {
		return creatorName;
	}
	/**
	 * 设置：商品更新人ID
	 */
	public void setUpdateId(Long updateId) {
		this.updateId = updateId;
	}
	/**
	 * 获取：商品更新人ID
	 */
	public Long getUpdateId() {
		return updateId;
	}
	/**
	 * 设置：商品更新人名
	 */
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	/**
	 * 获取：商品更新人名
	 */
	public String getUpdateName() {
		return updateName;
	}

	public Integer getIsQuick() {
		return isQuick;
	}

	public void setIsQuick(Integer isQuick) {
		this.isQuick = isQuick;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
