package com.yunquanlai.admin.comment.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 配送员评价
 * 
 * @author weicc
 * @email 
 * @date 2018-06-10 16:28:26
 */
public class CommentProductEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 设置：主键ID
     */
	private Integer id;
    /**
     * 设置：商品品牌名称
     */
	private Integer productId;
    /**
     * 设置：评论内容
     */
	private String comment;
    /**
     * 设置：打分，1-5分
     */
	private Integer level;
    /**
     * 设置：评论时间
     */
	private Date creationTime;

	/**
	 * 设置：主键ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键ID
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：商品品牌名称
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	/**
	 * 获取：商品品牌名称
	 */
	public Integer getProductId() {
		return productId;
	}
	/**
	 * 设置：评论内容
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * 获取：评论内容
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * 设置：打分，1-5分
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
	 * 获取：打分，1-5分
	 */
	public Integer getLevel() {
		return level;
	}
	/**
	 * 设置：评论时间
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	/**
	 * 获取：评论时间
	 */
	public Date getCreationTime() {
		return creationTime;
	}
}
