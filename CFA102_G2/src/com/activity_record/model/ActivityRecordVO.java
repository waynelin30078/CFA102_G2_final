package com.activity_record.model;

public class ActivityRecordVO {
	
	private Integer diaryNo;
	private Integer actNo;
	private Double actHr;
	private Integer wt;
	private Double calBurn;

	public ActivityRecordVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActivityRecordVO(Integer diaryNo, Integer actNo, Double actHr, Integer wt, Double calBurn) {
		super();
		this.diaryNo = diaryNo;
		this.actNo = actNo;
		this.actHr = actHr;
		this.wt = wt;
		this.calBurn = calBurn;
	}

	public Integer getDiaryNo() {
		return diaryNo;
	}

	public void setDiaryNo(Integer diaryNo) {
		this.diaryNo = diaryNo;
	}

	public Integer getActNo() {
		return actNo;
	}

	public void setActNo(Integer actNo) {
		this.actNo = actNo;
	}

	public Double getActHr() {
		return actHr;
	}

	public void setActHr(Double actHr) {
		this.actHr = actHr;
	}

	public Integer getWt() {
		return wt;
	}

	public void setWt(Integer wt) {
		this.wt = wt;
	}

	public Double getCalBurn() {
		return calBurn;
	}

	public void setCalBurn(Double calBurn) {
		this.calBurn = calBurn;
	}

}
