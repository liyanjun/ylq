package com.yunquanlai.admin.user.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 用户水票信息表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-07-17 12:11:07
 */
public class UserProductTicketEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 设置：主键 ID
     */
	private Long id;
	/**
	 * 设置：关联用户 ID
	 */
	private Long userId;
	/**
	 * 设置：关联用户名
	 */
	private Long userName;
    /**
     * 设置：关联水票 ID
     */
    private Long productTicketId;
    /**
     * 设置：关联产品 ID
     */
    private Long productId;
    /**
     * 设置：关联产品名称
     */
    private String productName;
    /**
     * 设置：水票标题
     */
	private String productTicketTitle;
    /**
     * 设置：水票编号
     */
    private String productTicketNum;
    /**
     * 设置：水票副标题
     */
    private String productTicketSubtitle;
    /**
     * 设置：水票总共兑换数量
     */
    private Integer totalCount;
    /**
     * 设置：用户已使用水票数量
     */
    private Integer useCount;
    /**
     * 设置：用户剩余水票数量
     */
    private Integer remainderCount;
    /**
     * 设置：购买价格
     */
	private BigDecimal amount;
    /**
     * 设置：用户水票状态，10：新下单，20：已支付，30：已关闭
     */
	private Integer status;
    /**
     * 设置：兑付结束时间
     */
    private Date finishTime;
    /**
     * 设置：过期时间
     */
    private Date endTime;
    /**
     * 设置：创建时间
     */
    private Date creationTime;
    /**
     * 设置：获赠水币的用户id
     */
    private Long benifitUserId;
    /**
     * 设置：获赠水币的用户名
     */
    private String benifitUserName;
    /**
     * 设置：送出桶数
     */
    private Integer benifitCount;
    /**
     * 设置：赠送水币的用户id
     */
    private Long fromUserId;
    /**
     * 设置：赠送水币的用户名
     */
    private String fromUserName;
    /**
     * 设置：得到桶数
     */
    private Integer fromCount;

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
	 * 设置：水票标题
	 */
	public void setProductTicketTitle(String productTicketTitle) {
		this.productTicketTitle = productTicketTitle;
	}
	/**
	 * 获取：水票标题
	 */
	public String getProductTicketTitle() {
		return productTicketTitle;
	}
	/**
	 * 设置：购买价格
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * 获取：购买价格
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * 设置：用户水票状态，10：新下单，20：已支付，30：已关闭
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：用户水票状态，10：新下单，20：已支付，30：已关闭
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：购买时间
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	/**
	 * 获取：购买时间
	 */
	public Date getCreationTime() {
		return creationTime;
	}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserName() {
        return userName;
    }

    public void setUserName(Long userName) {
        this.userName = userName;
    }

    public Long getProductTicketId() {
        return productTicketId;
    }

    public void setProductTicketId(Long productTicketId) {
        this.productTicketId = productTicketId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductTicketNum() {
        return productTicketNum;
    }

    public void setProductTicketNum(String productTicketNum) {
        this.productTicketNum = productTicketNum;
    }

    public String getProductTicketSubtitle() {
        return productTicketSubtitle;
    }

    public void setProductTicketSubtitle(String productTicketSubtitle) {
        this.productTicketSubtitle = productTicketSubtitle;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getUseCount() {
        return useCount;
    }

    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    public Integer getRemainderCount() {
        return remainderCount;
    }

    public void setRemainderCount(Integer remainderCount) {
        this.remainderCount = remainderCount;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getBenifitUserId() {
        return benifitUserId;
    }

    public void setBenifitUserId(Long benifitUserId) {
        this.benifitUserId = benifitUserId;
    }

    public String getBenifitUsername() {
        return benifitUserName;
    }

    public void setBenifitUsername(String benifitUserName) {
        this.benifitUserName = benifitUserName;
    }

    public Integer getBenifitCount() {
        return benifitCount;
    }

    public void setBenifitCount(Integer benifitCount) {
        this.benifitCount = benifitCount;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUsername() {
        return fromUserName;
    }

    public void setFromUsername(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Integer getFromCount() {
        return fromCount;
    }

    public void setFromCount(Integer fromCount) {
        this.fromCount = fromCount;
    }
}
