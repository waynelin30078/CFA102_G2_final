package com.promotion.model;

import java.sql.Date;

public class PromotionVO implements java.io.Serializable {

	private Integer promNo;
	private String promName;
	private Date promStartDate;
	private Date promEndDate;

	public PromotionVO() {
		super();
	}

	public PromotionVO(Integer promNo, String promName, Date promStartDate, Date promEndDate) {
		super();
		this.promNo = promNo;
		this.promName = promName;
		this.promStartDate = promStartDate;
		this.promEndDate = promEndDate;
	}

	public Integer getPromNo() {
		return promNo;
	}

	public void setPromNo(Integer promNo) {
		this.promNo = promNo;
	}

	public String getPromName() {
		return promName;
	}

	public void setPromName(String promName) {
		this.promName = promName;
	}

	public Date getPromStartDate() {
		return promStartDate;
	}

	public void setPromStartDate(Date promStartDate) {
		this.promStartDate = promStartDate;
	}

	public Date getPromEndDate() {
		return promEndDate;
	}

	public void setPromEndDate(Date promEndDate) {
		this.promEndDate = promEndDate;
	}

}
