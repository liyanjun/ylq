package com.yunquanlai.admin.product.entity;

import com.yunquanlai.utils.validator.group.AddGroup;
import com.yunquanlai.utils.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;



/**
 * 商品品牌信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-05-30 23:09:11
 */
public class ProductBrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 设置：主键 ID
     */
	private Long id;
    /**
     * 设置：品牌名
     */
	@NotBlank(message="品牌名不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String name;
    /**
     * 设置：备注
     */
	private String remark;

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
	 * 设置：品牌名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**品牌名
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
}
