package com.paidang.dao.model;



/**
 *
 */
public class OutOrder {

	/**
	 *
	 */
	private Integer id;

	/**
	 *
	 */
	private Integer userId;

	/**
	 *购物车订单支付同一id
	 */
	private String outOrderId;

	/**
	 *订单id
	 */
	private String orderId;

	/**
	 *
	 */
	private java.util.Date createTime;

	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
	}

	public void setUserId(Integer userId) {
		this.userId=userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setOutOrderId(String outOrderId) {
		this.outOrderId=outOrderId == null ? outOrderId : outOrderId.trim();
	}

	public String getOutOrderId() {
		return outOrderId;
	}

	public void setOrderId(String orderId) {
		this.orderId=orderId == null ? orderId : orderId.trim();
	}

	public String getOrderId() {
		return orderId;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime=createTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

}
