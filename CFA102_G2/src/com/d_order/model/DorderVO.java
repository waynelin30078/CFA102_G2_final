package com.d_order.model;

import java.sql.Date;
import java.sql.Timestamp;

public class DorderVO {

	private Integer dorderNo;
	private Integer dno;
	private Integer mno;
	private Timestamp subStart;
	private Timestamp subEnd;
	private Integer mthFee;
	private String dreview;
	private Integer dscore;
	private Integer autoSubs;
	
	public DorderVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	public DorderVO(Integer dno, Integer mno, Timestamp subStart, Timestamp subEnd, Integer mthFee, String dreview,
			Integer dscore, Integer autoSubs) {
		super();
		this.dno = dno;
		this.mno = mno;
		this.subStart = subStart;
		this.subEnd = subEnd;
		this.mthFee = mthFee;
		this.dreview = dreview;
		this.dscore = dscore;
		this.autoSubs = autoSubs;
	}
	public Integer getDorderNo() {
		return dorderNo;
	}
	public void setDorderNo(Integer dorderNo) {
		this.dorderNo = dorderNo;
	}
	public Integer getDno() {
		return dno;
	}
	public void setDno(Integer dno) {
		this.dno = dno;
	}
	public Integer getMno() {
		return mno;
	}
	public void setMno(Integer mno) {
		this.mno = mno;
	}
	public Timestamp getSubStart() {
		return subStart;
	}
	public void setSubStart(Timestamp subStart) {
		this.subStart = subStart;
	}
	public Timestamp getSubEnd() {
		return subEnd;
	}
	public void setSubEnd(Timestamp subEnd) {
		this.subEnd = subEnd;
	}
	public Integer getMthFee() {
		return mthFee;
	}
	public void setMthFee(Integer mthFee) {
		this.mthFee = mthFee;
	}
	public String getDreview() {
		return dreview;
	}
	public void setDreview(String dreview) {
		this.dreview = dreview;
	}
	public Integer getDscore() {
		return dscore;
	}
	public void setDscore(Integer dscore) {
		this.dscore = dscore;
	}
	public Integer getAutoSubs() {
		return autoSubs;
	}
	public void setAutoSubs(Integer autoSubs) {
		this.autoSubs = autoSubs;
	}



}