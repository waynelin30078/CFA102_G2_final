package com.notify.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class NotifyVO implements Serializable {

	private Integer notiNO;
	private Integer notiType;
	private String notiContent;
	private Timestamp notiTime;
	private Integer mNo;
	private Integer dNo;

	public NotifyVO(Integer notiNO, Integer notiType, String notiContent, Timestamp notiTime, Integer mNo, Integer dNo) {
		super();
		this.notiNO = notiNO;
		this.notiType = notiType;
		this.notiContent = notiContent;
		this.notiTime = notiTime;
		this.mNo = mNo;
		this.dNo = dNo;
	}

	public NotifyVO() {
	}

	public Integer getNotiNO() {
		return notiNO;
	}

	public void setNotiNO(Integer notiNO) {
		this.notiNO = notiNO;
	}

	public Integer getNotiType() {
		return notiType;
	}

	public void setNotiType(Integer notiType) {
		this.notiType = notiType;
	}

	public String getNotiContent() {
		return notiContent;
	}

	public void setNotiContent(String notiContent) {
		this.notiContent = notiContent;
	}

	public Timestamp getNotiTime() {
		return notiTime;
	}

	public void setNotiTime(Timestamp notiTime) {
		this.notiTime = notiTime;
	}

	public Integer getmNO() {
		return mNo;
	}

	public void setmNO(Integer mNo) {
		this.mNo = mNo;
	}

	public Integer getdNO() {
		return dNo;
	}

	public void setdNO(Integer dNo) {
		this.dNo = dNo;
	}

}
