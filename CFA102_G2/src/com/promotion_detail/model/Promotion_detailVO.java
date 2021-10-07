package com.promotion_detail.model;

public class Promotion_detailVO implements java.io.Serializable {

	private Integer promNo;
	private Integer pno;
	private Integer pprice;

	public Promotion_detailVO() {
		super();
	}

	public Promotion_detailVO(Integer promNo, Integer pno, Integer pprice) {
		super();
		this.promNo = promNo;
		this.pno = pno;
		this.pprice = pprice;
	}

	public Integer getPromNo() {
		return promNo;
	}

	public void setPromNo(Integer promNo) {
		this.promNo = promNo;
	}

	public Integer getPno() {
		return pno;
	}

	public void setPno(Integer pno) {
		this.pno = pno;
	}

	public Integer getPprice() {
		return pprice;
	}

	public void setPprice(Integer pprice) {
		this.pprice = pprice;
	}

}
