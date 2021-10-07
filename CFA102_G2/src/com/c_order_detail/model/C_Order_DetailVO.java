package com.c_order_detail.model;

import java.io.Serializable;

public class C_Order_DetailVO implements Serializable {
	private Integer corderno;
	private Integer cno;
	private Integer cprice;
	private Integer cevaluation;
	private String creviews;
	private Integer cprogress;

	public C_Order_DetailVO() {
	}

	public Integer getCorderno() {
		return corderno;
	}

	public void setCorderno(Integer corderno) {
		this.corderno = corderno;
	}

	public Integer getCno() {
		return cno;
	}

	public void setCno(Integer cno) {
		this.cno = cno;
	}

	public Integer getCprice() {
		return cprice;
	}

	public void setCprice(Integer cprice) {
		this.cprice = cprice;
	}

	public Integer getCevaluation() {
		return cevaluation;
	}

	public void setCevaluation(Integer cevaluation) {
		this.cevaluation = cevaluation;
	}

	public String getCreviews() {
		return creviews;
	}

	public void setCreviews(String creviews) {
		this.creviews = creviews;
	}

	public Integer getCprogress() {
		return cprogress;
	}

	public void setCprogress(Integer cprogress) {
		this.cprogress = cprogress;
	}

	@Override
	public String toString() {
		return "C_Order_DetailVO [corderno=" + corderno + ", cno=" + cno + ", cprice=" + cprice + ", cevaluation="
				+ cevaluation + ", creviews=" + creviews + ", cprogress=" + cprogress + "]";
	};

	

}
