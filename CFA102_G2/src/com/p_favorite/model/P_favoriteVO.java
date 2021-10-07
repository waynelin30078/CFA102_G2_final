package com.p_favorite.model;

public class P_favoriteVO implements java.io.Serializable {

	private Integer mno;
	private Integer pno;
	
	public P_favoriteVO() {
		super();
	}

	public P_favoriteVO(Integer mno, Integer pno) {
		super();
		this.mno = mno;
		this.pno = pno;
	}

	public Integer getMno() {
		return mno;
	}

	public void setMno(Integer mno) {
		this.mno = mno;
	}

	public Integer getPno() {
		return pno;
	}

	public void setPno(Integer pno) {
		this.pno = pno;
	}


}
