package com.activity.model;

public class ActivityVO {

	private Integer actNo;
	private String actName;
	private Double calPerKgHr;
	private Integer actState;

	public ActivityVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActivityVO(String actName, Double calPerKgHr, Integer actState) {
		super();
		this.actName = actName;
		this.calPerKgHr = calPerKgHr;
		this.actState = actState;
	}

	public Integer getActNo() {
		return actNo;
	}

	public void setActNo(Integer actNo) {
		this.actNo = actNo;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public Double getCalPerKgHr() {
		return calPerKgHr;
	}

	public void setCalPerKgHr(Double calPerKgHr) {
		this.calPerKgHr = calPerKgHr;
	}

	public Integer getActState() {
		return actState;
	}

	public void setActState(Integer actState) {
		this.actState = actState;
	}

}
