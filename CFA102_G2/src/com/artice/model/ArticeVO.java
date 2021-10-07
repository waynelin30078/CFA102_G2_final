package com.artice.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class ArticeVO implements Serializable {

	private Integer articNo;
	private Integer articType;
	private String articTitle;
	private String articContent;
	private byte[] articPhoto;
	private Timestamp articDate;
	private Integer articState;

	public ArticeVO() {
		super();
	}

	public ArticeVO(Integer articNo, Integer articType, String articTitle, String articContent, byte[] articPhoto,
			Timestamp articDate, Integer articState) {
		super();
		this.articNo = articNo;
		this.articType = articType;
		this.articTitle = articTitle;
		this.articContent = articContent;
		this.articPhoto = articPhoto;
		this.articDate = articDate;
		this.articState = articState;
	}

	public Integer getArticNo() {
		return articNo;
	}

	public void setArticNo(Integer articNo) {
		this.articNo = articNo;
	}

	public Integer getArticType() {
		return articType;
	}

	public void setArticType(Integer articType) {
		this.articType = articType;
	}

	public String getArticTitle() {
		return articTitle;
	}

	public void setArticTitle(String articTitle) {
		this.articTitle = articTitle;
	}

	public String getArticContent() {
		return articContent;
	}

	public void setArticContent(String articContent) {
		this.articContent = articContent;
	}

	public byte[] getArticPhoto() {
		return articPhoto;
	}

	public void setArticPhoto(byte[] articPhoto) {
		this.articPhoto = articPhoto;
	}

	public Timestamp getArticDate() {
		return articDate;
	}

	public void setArticDate(Timestamp articDate) {
		this.articDate = articDate;
	}


	public Integer getArticState() {
		return articState;
	}

	public void setArticState(Integer articState) {
		this.articState = articState;
	}

}
