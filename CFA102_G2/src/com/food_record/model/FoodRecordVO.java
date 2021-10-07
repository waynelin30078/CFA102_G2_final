package com.food_record.model;

public class FoodRecordVO {

	private Integer mealNo;
	private Integer fdNo;
	private Integer fdPortion;
	private Integer fdWt;
	private Double singlelCal;
	private Double singleCho;
	private Double singlePro;
	private Double singleFat;

	public FoodRecordVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FoodRecordVO(Integer mealNo, Integer fdNo, Integer fdPortion, Integer fdWt, Double singlelCal,
			Double singleCho, Double singlePro, Double singleFat) {
		super();
		this.mealNo = mealNo;
		this.fdNo = fdNo;
		this.fdPortion = fdPortion;
		this.fdWt = fdWt;
		this.singlelCal = singlelCal;
		this.singleCho = singleCho;
		this.singlePro = singlePro;
		this.singleFat = singleFat;
	}

	public Integer getMealNo() {
		return mealNo;
	}

	public void setMealNo(Integer mealNo) {
		this.mealNo = mealNo;
	}

	public Integer getFdNo() {
		return fdNo;
	}

	public void setFdNo(Integer fdNo) {
		this.fdNo = fdNo;
	}

	public Integer getFdPortion() {
		return fdPortion;
	}

	public void setFdPortion(Integer fdPortion) {
		this.fdPortion = fdPortion;
	}

	public Integer getFdWt() {
		return fdWt;
	}

	public void setFdWt(Integer fdWt) {
		this.fdWt = fdWt;
	}

	public Double getSinglelCal() {
		return singlelCal;
	}

	public void setSinglelCal(Double singlelCal) {
		this.singlelCal = singlelCal;
	}

	public Double getSingleCho() {
		return singleCho;
	}

	public void setSingleCho(Double singleCho) {
		this.singleCho = singleCho;
	}

	public Double getSinglePro() {
		return singlePro;
	}

	public void setSinglePro(Double singlePro) {
		this.singlePro = singlePro;
	}

	public Double getSingleFat() {
		return singleFat;
	}

	public void setSingleFat(Double singleFat) {
		this.singleFat = singleFat;
	}

}
