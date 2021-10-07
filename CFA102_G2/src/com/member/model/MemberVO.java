package com.member.model;

import java.sql.Date;

public class MemberVO implements java.io.Serializable{
	private Integer mno;
	private String mname;
	private String mid;
	private String mpsw;
	private String mmail;
	private String mphone;
	private byte[] mimg;
	private Date mbday;
	private Integer msex;  
	private String mintro;
	private Integer mstate;
	private String cardID;
	private Integer cardDate;
	private Integer cardNum;
	private Integer dno;
	private Integer dailyCal;
	private Integer dailyCho;
	private Integer dailyPro;
	private Integer dailyFat;
	private String dietPlan;
	public Integer getMno() {
		return mno;
	}
	public void setMno(Integer mno) {
		this.mno = mno;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMpsw() {
		return mpsw;
	}
	public void setMpsw(String mpsw) {
		this.mpsw = mpsw;
	}
	public String getMmail() {
		return mmail;
	}
	public void setMmail(String mmail) {
		this.mmail = mmail;
	}
	public String getMphone() {
		return mphone;
	}
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
	public byte[] getMimg() {
		return mimg;
	}
	public void setMimg(byte[] mimg) {
		this.mimg = mimg;
	}
	public Date getMbday() {
		return mbday;
	}
	public void setMbday(Date mbday) {
		this.mbday = mbday;
	}
	public Integer getMsex() {
		return msex;
	}
	public void setMsex(Integer msex) {
		this.msex = msex;
	}
	public String getMintro() {
		return mintro;
	}
	public void setMintro(String mintro) {
		this.mintro = mintro;
	}
	public Integer getMstate() {
		return mstate;
	}
	public void setMstate(Integer mstate) {
		this.mstate = mstate;
	}
	public String getCardID() {
		return cardID;
	}
	public void setCardID(String cardID) {
		this.cardID = cardID;
	}
	public Integer getCardDate() {
		return cardDate;
	}
	public void setCardDate(Integer cardDate) {
		this.cardDate = cardDate;
	}
	public Integer getCardNum() {
		return cardNum;
	}
	public void setCardNum(Integer cardNum) {
		this.cardNum = cardNum;
	}
	public Integer getDno() {
		return dno;
	}
	public void setDno(Integer dno) {
		this.dno = dno;
	}
	public Integer getDailyCal() {
		return dailyCal;
	}
	public void setDailyCal(Integer dailyCal) {
		this.dailyCal = dailyCal;
	}
	public Integer getDailyCho() {
		return dailyCho;
	}
	public void setDailyCho(Integer dailyCho) {
		this.dailyCho = dailyCho;
	}
	public Integer getDailyPro() {
		return dailyPro;
	}
	public void setDailyPro(Integer dailyPro) {
		this.dailyPro = dailyPro;
	}
	public Integer getDailyFat() {
		return dailyFat;
	}
	public void setDailyFat(Integer dailyFat) {
		this.dailyFat = dailyFat;
	}
	public String getDietPlan() {
		return dietPlan;
	}
	public void setDietPlan(String dietPlan) {
		this.dietPlan = dietPlan;
	}
	
	
	
	

	
}
