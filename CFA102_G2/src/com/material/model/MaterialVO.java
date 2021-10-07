package com.material.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class MaterialVO implements Serializable {
	private Integer matNo;
	private Integer cno;
	private String matFile;
	private Timestamp matUpdateTime;
	public MaterialVO() {}
	public Integer getMatNo() {
		return matNo;
	}
	public void setMatNo(Integer matNo) {
		this.matNo = matNo;
	}
	public Integer getCno() {
		return cno;
	}
	public void setCno(Integer cno) {
		this.cno = cno;
	}
	public String getMatFile() {
		return matFile;
	}
	public void setMatFile(String matFile) {
		this.matFile = matFile;
	}
	public Timestamp getMatUpdateTime() {
		return matUpdateTime;
	}
	public void setMatUpdateTime(Timestamp matUpdateTime) {
		this.matUpdateTime = matUpdateTime;
	}
	public MaterialVO(Integer matNo, Integer cno, String matFile, Timestamp matUpdateTime) {
		super();
		this.matNo = matNo;
		this.cno = cno;
		this.matFile = matFile;
		this.matUpdateTime = matUpdateTime;
	};
	
	
	
}
