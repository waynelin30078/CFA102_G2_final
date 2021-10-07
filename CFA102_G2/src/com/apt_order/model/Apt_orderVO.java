package com.apt_order.model;

import java.sql.Timestamp;

public class Apt_orderVO implements java.io.Serializable{
	private Integer aptOrderNo;
	private Integer mno;
	private Integer dno;
	private Timestamp orderTime;
	private Timestamp orderDate;
	private Integer clPrice;
	public Integer getAptOrderNo() {
		return aptOrderNo;
	}
	public void setAptOrderNo(Integer aptOrderNo) {
		this.aptOrderNo = aptOrderNo;
	}
	public Integer getMno() {
		return mno;
	}
	public void setMno(Integer mno) {
		this.mno = mno;
	}
	public Integer getDno() {
		return dno;
	}
	public void setDno(Integer dno) {
		this.dno = dno;
	}
	public Timestamp getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}
	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	public Integer getClPrice() {
		return clPrice;
	}
	public void setClPrice(Integer clPrice) {
		this.clPrice = clPrice;
	}
	public Integer getClState() {
		return clState;
	}
	public void setClState(Integer clState) {
		this.clState = clState;
	}
	public String getAptReviews() {
		return aptReviews;
	}
	public void setAptReviews(String aptReviews) {
		this.aptReviews = aptReviews;
	}
	private Integer clState;
	private String aptReviews;
	


}
