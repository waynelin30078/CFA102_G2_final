package com.specialty.model;

public class SpecialtyVO {

	private Integer specialtyNo;
	private String specialtyName;

	public SpecialtyVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SpecialtyVO(Integer specialtyNo, String specialtyName) {
		super();
		this.specialtyNo = specialtyNo;
		this.specialtyName = specialtyName;
	}

	public Integer getSpecialtyNo() {
		return specialtyNo;
	}

	public void setSpecialtyNo(Integer specialtyNo) {
		this.specialtyNo = specialtyNo;
	}

	public String getSpecialtyName() {
		return specialtyName;
	}

	public void setSpecialtyName(String specialtyName) {
		this.specialtyName = specialtyName;
	}

}
