package com.c_report.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class C_ReportVO implements Serializable {
	private Integer reportNo;
	private Integer mno;
	private Integer cno;
	private Integer creportState;
	private String creportContent;
	private Timestamp creportTime;

	public C_ReportVO() {
	};
	public C_ReportVO(Integer reportNo, Integer mno, Integer cno, Integer creportState, String creportContent,
			Timestamp creportTime) {
		super();
		this.reportNo = reportNo;
		this.mno = mno;
		this.cno = cno;
		this.creportState = creportState;
		this.creportContent = creportContent;
		this.creportTime = creportTime;
	};


	public Integer getReportNo() {
		return reportNo;
	}

	public void setReportNo(Integer reportNo) {
		this.reportNo = reportNo;
	}

	public Integer getMno() {
		return mno;
	}

	public void setMno(Integer mno) {
		this.mno = mno;
	}

	public Integer getCno() {
		return cno;
	}

	public void setCno(Integer cno) {
		this.cno = cno;
	}

	public Integer getCreportState() {
		return creportState;
	}

	public void setCreportState(Integer creportState) {
		this.creportState = creportState;
	}

	public String getCreportContent() {
		return creportContent;
	}

	public void setCreportContent(String creportContent) {
		this.creportContent = creportContent;
	}

	public Timestamp getCreportTime() {
		return creportTime;
	}

	public void setCreportTime(Timestamp creportTime) {
		this.creportTime = creportTime;
	}

	
}
