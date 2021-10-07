package com.news.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;


public class NewsVO implements Serializable{
	private Integer newsNo;
	private String newsContent;
	private Timestamp newsDate;
	private String newsTitle;
	private Integer newsState;
	
	public NewsVO() {
		
	}
	
	public Integer getNewsNo() {
		return newsNo;
	}
	public void setNewsNo(Integer newsNo) {
		this.newsNo = newsNo;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public Timestamp getNewsDate() {
		return newsDate;
	}
	public void setNewsDate(Timestamp newsDate) {
		this.newsDate = newsDate;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public Integer getNewsState() {
		return newsState;
	}

	public void setNewsState(Integer newsState) {
		this.newsState = newsState;
	}


	
}

	