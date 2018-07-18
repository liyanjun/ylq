package com.yunquanlai.api.comsumer.vo;

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
public class ProductTicketVO implements Serializable {
	private static final long serialVersionUID = 1L;

    /**
     * 设置：水票标题
     */
	private String title;
    /**
     * 设置：水票副标题
     */
	private String subtitle;
    /**
     * 设置：水票最低价格
     */
	private BigDecimal minAmount;
	/**
	 * 图片地址
	 */
	private String url;

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

	public BigDecimal getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
