package com.appointment.model;

import java.sql.Date;


public class AppointmentVO implements java.io.Serializable {
	private Integer aptNo;
	private Integer dno;
	private Date rdate;
	private String rstate;
	private Date serchdate;

	
	
	public Date getSerchdate() {
		return serchdate;
	}
	public void setSerchdate(Date serchdate) {
		this.serchdate = serchdate;
	}
	public Integer getAptNo() {
		return aptNo;
	}
	public void setAptNo(Integer aptNo) {
		this.aptNo = aptNo;
	}
	public Integer getDno() {
		return dno;
	}
	public void setDno(Integer dno) {
		this.dno = dno;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public String getRstate() {
		return rstate;
	}
	public void setRstate(String rstate) {
		this.rstate = rstate;
	}
	
	

	

}
