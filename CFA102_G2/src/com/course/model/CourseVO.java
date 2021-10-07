package com.course.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;

public class CourseVO implements Serializable {
	private Integer cno;// 課程編號
	private Integer dno;// 講師編號
	private String cname;// 課程名稱
	private Integer cprice;
	private Integer cstate;
	private Timestamp cshelfDate;// 上架日期
	private String cintroduction;// 課程介紹
	private Integer ctype;
	private Integer quantity;// 購買人數
	private byte[] cpic;// 預覽圖
	private String cdescription;// 預覽介紹
	private Integer ctotalPeople;
	private Integer ctotalScore;

	public CourseVO() {
	};

	public CourseVO(Integer cno, Integer dno, String cname, Integer cprice, Integer cstate, Timestamp cshelfDate,
			String cintroduction, Integer ctype, Integer quantity, byte[] cpic, String cdescription,
			Integer ctotalPeople, Integer ctotalScore) {
		super();
		this.cno = cno;
		this.dno = dno;
		this.cname = cname;
		this.cprice = cprice;
		this.cstate = cstate;
		this.cshelfDate = cshelfDate;
		this.cintroduction = cintroduction;
		this.ctype = ctype;
		this.quantity = quantity;
		this.cpic = cpic;
		this.cdescription = cdescription;
		this.ctotalPeople = ctotalPeople;
		this.ctotalScore = ctotalScore;
	}

	public Integer getCno() {
		return cno;
	}

	public void setCno(Integer cno) {
		this.cno = cno;
	}

	public Integer getDno() {
		return dno;
	}

	public void setDno(Integer dno) {
		this.dno = dno;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Integer getCprice() {
		return cprice;
	}

	public void setCprice(Integer cprice) {
		this.cprice = cprice;
	}

	public Integer getCstate() {
		return cstate;
	}

	public void setCstate(Integer cstate) {
		this.cstate = cstate;
	}

	public Timestamp getCshelfDate() {
		return cshelfDate;
	}

	public void setCshelfDate(Timestamp cshelfDate) {
		this.cshelfDate = cshelfDate;
	}

	public String getCintroduction() {
		return cintroduction;
	}

	public void setCintroduction(String cintroduction) {
		this.cintroduction = cintroduction;
	}

	public Integer getCtype() {
		return ctype;
	}

	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public byte[] getCpic() {
		return cpic;
	}

	public void setCpic(byte[] cpic) {
		this.cpic = cpic;
	}

	public String getCdescription() {
		return cdescription;
	}

	public void setCdescription(String cdescription) {
		this.cdescription = cdescription;
	}

	public Integer getCtotalPeople() {
		return ctotalPeople;
	}

	public void setCtotalPeople(Integer ctotalPeople) {
		this.ctotalPeople = ctotalPeople;
	}

	public Integer getCtotalScore() {
		return ctotalScore;
	}

	public void setCtotalScore(Integer ctotalScore) {
		this.ctotalScore = ctotalScore;
	}

	@Override
	public String toString() {
		return "CourseVO [cno=" + cno + ", dno=" + dno + ", cname=" + cname + ", cprice=" + cprice + ", cstate="
				+ cstate + ", cshelfDate=" + cshelfDate + ", cintroduction=" + cintroduction + ", ctype=" + ctype
				+ ", quantity=" + quantity + ", cpic="  + ", cdescription=" + cdescription
				+ ", ctotalPeople=" + ctotalPeople + ", ctotalScore=" + ctotalScore + "]";
	}

}
