package com.diary.model;

import java.sql.Date;

public class DiaryVO {

	private Integer diaryNo;
	private Integer mno;
	private Integer dno;
	private Date diaryDate;
	private Integer ht;
	private Integer wt;
	private Double bodyFat;
	private Integer wc;
	private String bodyPic;
	private Integer viewState;
	private String reply;
	private Double totalCal;
	private Double totalCho;
	private Double totalPro;
	private Double totalFat;
	private Double totalCalBurn;

	public DiaryVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DiaryVO(Integer mno, Integer dno, Date diaryDate, Integer ht, Integer wt, Double bodyFat,
			Integer wc, String bodyPic, Integer viewState, String reply, Double totalCal, Double totalCho,
			Double totalPro, Double totalFat, Double totalCalBurn) {
		super();
		
		this.mno = mno;
		this.dno = dno;
		this.diaryDate = diaryDate;
		this.ht = ht;
		this.wt = wt;
		this.bodyFat = bodyFat;
		this.wc = wc;
		this.bodyPic = bodyPic;
		this.viewState = viewState;
		this.reply = reply;
		this.totalCal = totalCal;
		this.totalCho = totalCho;
		this.totalPro = totalPro;
		this.totalFat = totalFat;
		this.totalCalBurn = totalCalBurn;
	}

	public Integer getDiaryNo() {
		return diaryNo;
	}

	public void setDiaryNo(Integer diaryNo) {
		this.diaryNo = diaryNo;
	}

	public Integer getMno() {
		return mno;
	}

	public void setMno(Integer mno) {
		this.mno = mno;
	}

	public Integer getDno() {
		return dno;
	}

	public void setDno(Integer dno) {
		this.dno = dno;
	}

	public Date getDiaryDate() {
		return diaryDate;
	}

	public void setDiaryDate(Date diaryDate) {
		this.diaryDate = diaryDate;
	}

	public Integer getHt() {
		return ht;
	}

	public void setHt(Integer ht) {
		this.ht = ht;
	}

	public Integer getWt() {
		return wt;
	}

	public void setWt(Integer wt) {
		this.wt = wt;
	}

	public Double getBodyFat() {
		return bodyFat;
	}

	public void setBodyFat(Double bodyFat) {
		this.bodyFat = bodyFat;
	}

	public Integer getWc() {
		return wc;
	}

	public void setWc(Integer wc) {
		this.wc = wc;
	}

	public String getBodyPic() {
		return bodyPic;
	}

	public void setBodyPic(String bodyPic) {
		this.bodyPic = bodyPic;
	}

	public Integer getViewState() {
		return viewState;
	}

	public void setViewState(Integer viewState) {
		this.viewState = viewState;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public Double getTotalCal() {
		return totalCal;
	}

	public void setTotalCal(Double totalCal) {
		this.totalCal = totalCal;
	}

	public Double getTotalCho() {
		return totalCho;
	}

	public void setTotalCho(Double totalCho) {
		this.totalCho = totalCho;
	}

	public Double getTotalPro() {
		return totalPro;
	}

	public void setTotalPro(Double totalPro) {
		this.totalPro = totalPro;
	}

	public Double getTotalFat() {
		return totalFat;
	}

	public void setTotalFat(Double totalFat) {
		this.totalFat = totalFat;
	}

	public Double getTotalCalBurn() {
		return totalCalBurn;
	}

	public void setTotalCalBurn(Double totalCalBurn) {
		this.totalCalBurn = totalCalBurn;
	}

}
