package com.food.model;

public class FoodVO {
	
	private Integer fdNo;
	private Integer mno;
	private String fdName;
	private Integer wtPerPortion;
	private Double calPerWt;
	private Double choPerWt;
	private Double proPerWt;
	private Double fatPerWt;
	private String fdBrand;
	private Integer fdState;

	public FoodVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FoodVO(Integer mno, String fdName, Integer wtPerPortion, Double calPerWt, Double choPerWt,
			Double proPerWt, Double fatPerWt, String fdBrand, Integer fdState) {
		super();
		
		this.mno = mno;
		this.fdName = fdName;
		this.wtPerPortion = wtPerPortion;
		this.calPerWt = calPerWt;
		this.choPerWt = choPerWt;
		this.proPerWt = proPerWt;
		this.fatPerWt = fatPerWt;
		this.fdBrand = fdBrand;
		this.fdState = fdState;
	}

	public Integer getFdNo() {
		return fdNo;
	}

	public void setFdNo(Integer fdNo) {
		this.fdNo = fdNo;
	}

	public Integer getMno() {
		return mno;
	}

	public void setMno(Integer mno) {
		this.mno = mno;
	}

	public String getFdName() {
		return fdName;
	}

	public void setFdName(String fdName) {
		this.fdName = fdName;
	}

	public Integer getWtPerPortion() {
		return wtPerPortion;
	}

	public void setWtPerPortion(Integer wtPerPortion) {
		this.wtPerPortion = wtPerPortion;
	}

	public Double getCalPerWt() {
		return calPerWt;
	}

	public void setCalPerWt(Double calPerWt) {
		this.calPerWt = calPerWt;
	}

	public Double getChoPerWt() {
		return choPerWt;
	}

	public void setChoPerWt(Double choPerWt) {
		this.choPerWt = choPerWt;
	}

	public Double getProPerWt() {
		return proPerWt;
	}

	public void setProPerWt(Double proPerWt) {
		this.proPerWt = proPerWt;
	}

	public Double getFatPerWt() {
		return fatPerWt;
	}

	public void setFatPerWt(Double fatPerWt) {
		this.fatPerWt = fatPerWt;
	}

	public String getFdBrand() {
		return fdBrand;
	}

	public void setFdBrand(String fdBrand) {
		this.fdBrand = fdBrand;
	}

	public Integer getFdState() {
		return fdState;
	}

	public void setFdState(Integer fdState) {
		this.fdState = fdState;
	}

}
