package com.c_order.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class C_OrderVO implements Serializable {
	private Integer corderno;
	private Integer mno;
	private Timestamp orderDate;
	private Integer total;
	private Integer orderState;
	private Integer paymentMethod;
	private String paymentInfo;

	public C_OrderVO() {
	};

	public C_OrderVO(Integer corderno, Integer mno, Timestamp orderDate, Integer total, Integer orderState,
			Integer paymentMethod, String paymentInfo) {
		super();
		this.corderno = corderno;
		this.mno = mno;
		this.orderDate = orderDate;
		this.total = total;
		this.orderState = orderState;

		this.paymentMethod = paymentMethod;
		this.paymentInfo = paymentInfo;
	}

	public Integer getCorderno() {
		return corderno;
	}

	public void setCorderno(Integer corderno) {
		this.corderno = corderno;
	}

	public Integer getMno() {
		return mno;
	}

	public void setMno(Integer mno) {
		this.mno = mno;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(String paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	@Override
	public String toString() {
		return "C_OrderVO [corderno=" + corderno + ", mno=" + mno + ", orderDate=" + orderDate + ", total=" + total
				+ ", orderState=" + orderState + ", paymentMethod=" + paymentMethod + ", paymentInfo=" + paymentInfo
				+ "]";
	}

}
