package com.p_order_detail.model;

public class P_order_detailVO implements java.io.Serializable {

	private Integer porderNo;
	private Integer pno;
	private Integer porderQuantity;
	private Integer pprice;
	private Integer pratings;

	public P_order_detailVO() {
		super();
	}

	public P_order_detailVO(Integer porderNo, Integer pno, Integer porderQuantity, Integer pprice, Integer pratings) {
		super();
		this.porderNo = porderNo;
		this.pno = pno;
		this.porderQuantity = porderQuantity;
		this.pprice = pprice;
		this.pratings = pratings;
	}

	public Integer getPorderNo() {
		return porderNo;
	}

	public void setPorderNo(Integer porderNo) {
		this.porderNo = porderNo;
	}

	public Integer getPno() {
		return pno;
	}

	public void setPno(Integer pno) {
		this.pno = pno;
	}

	public Integer getPorderQuantity() {
		return porderQuantity;
	}

	public void setPorderQuantity(Integer porderQuantity) {
		this.porderQuantity = porderQuantity;
	}

	public Integer getPprice() {
		return pprice;
	}

	public void setPprice(Integer pprice) {
		this.pprice = pprice;
	}

	public Integer getPratings() {
		return pratings;
	}

	public void setPratings(Integer pratings) {
		this.pratings = pratings;
	}

}
