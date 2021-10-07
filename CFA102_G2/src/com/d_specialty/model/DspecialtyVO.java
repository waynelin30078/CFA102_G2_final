package com.d_specialty.model;

public class DspecialtyVO {
	private Integer specialtyNo;
	private Integer dno;

	public DspecialtyVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DspecialtyVO(Integer specialtyNo, Integer dno) {
		super();
		this.specialtyNo = specialtyNo;
		this.dno = dno;
	}

	public Integer getSpecialtyNo() {
		return specialtyNo;
	}

	public void setSpecialtyNo(Integer specialtyNo) {
		this.specialtyNo = specialtyNo;
	}

	public Integer getDno() {
		return dno;
	}

	public void setDno(Integer dno) {
		this.dno = dno;
	}

}
