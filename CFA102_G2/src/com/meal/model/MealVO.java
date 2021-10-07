package com.meal.model;

public class MealVO {

	private Integer mealNo;
	private Integer diaryNo;
	private String mealName;
	private Double mealCal;
	private Double mealCho;
	private Double mealPro;
	private Double mealFat;

	public MealVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MealVO(Integer diaryNo, String mealName, Double mealCal, Double mealCho, Double mealPro, Double mealFat) {
		super();

		this.diaryNo = diaryNo;
		this.mealName = mealName;
		this.mealCal = mealCal;
		this.mealCho = mealCho;
		this.mealPro = mealPro;
		this.mealFat = mealFat;
	}

	public Integer getMealNo() {
		return mealNo;
	}

	public void setMealNo(Integer mealNo) {
		this.mealNo = mealNo;
	}

	public Integer getDiaryNo() {
		return diaryNo;
	}

	public void setDiaryNo(Integer diaryNo) {
		this.diaryNo = diaryNo;
	}

	public String getMealName() {
		return mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

	public Double getMealCal() {
		return mealCal;
	}

	public void setMealCal(Double mealCal) {
		this.mealCal = mealCal;
	}

	public Double getMealCho() {
		return mealCho;
	}

	public void setMealCho(Double mealCho) {
		this.mealCho = mealCho;
	}

	public Double getMealPro() {
		return mealPro;
	}

	public void setMealPro(Double mealPro) {
		this.mealPro = mealPro;
	}

	public Double getMealFat() {
		return mealFat;
	}

	public void setMealFat(Double mealFat) {
		this.mealFat = mealFat;
	}

}
