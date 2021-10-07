package com.p_order.model;

import java.sql.Timestamp;

public class P_orderVO implements java.io.Serializable {

	private Integer porderNo;
	private Integer mno;
	private Timestamp porderDate;
	private Integer porderTotal;
	private String porderName;
	private String porderPhone;
	private String porderAddress;
	private Integer ppayment;
	private String pcreditCard;
	private String pcreditCardCVV;
	private Integer pshipping;
	private Integer porderState;

	public P_orderVO() {
		super();
	}

	public P_orderVO(Integer porderNo, Integer mno, Timestamp porderDate, Integer porderTotal, String porderName,
			String porderPhone, String porderAddress, Integer ppayment, String pcreditCard, String pcreditCardCVV,
			Integer pshipping, Integer porderState) {
		super();
		this.porderNo = porderNo;
		this.mno = mno;
		this.porderDate = porderDate;
		this.porderTotal = porderTotal;
		this.porderName = porderName;
		this.porderPhone = porderPhone;
		this.porderAddress = porderAddress;
		this.ppayment = ppayment;
		this.pcreditCard = pcreditCard;
		this.pcreditCardCVV = pcreditCardCVV;
		this.pshipping = pshipping;
		this.porderState = porderState;
	}

	public Integer getPorderNo() {
		return porderNo;
	}

	public void setPorderNo(Integer porderNo) {
		this.porderNo = porderNo;
	}

	public Integer getMno() {
		return mno;
	}

	public void setMno(Integer mno) {
		this.mno = mno;
	}

	public Timestamp getPorderDate() {
		return porderDate;
	}

	public void setPorderDate(Timestamp porderDate) {
		this.porderDate = porderDate;
	}

	public Integer getPorderTotal() {
		return porderTotal;
	}

	public void setPorderTotal(Integer porderTotal) {
		this.porderTotal = porderTotal;
	}

	public String getPorderName() {
		return porderName;
	}

	public void setPorderName(String porderName) {
		this.porderName = porderName;
	}

	public String getPorderPhone() {
		return porderPhone;
	}

	public void setPorderPhone(String porderPhone) {
		this.porderPhone = porderPhone;
	}

	public String getPorderAddress() {
		return porderAddress;
	}

	public void setPorderAddress(String porderAddress) {
		this.porderAddress = porderAddress;
	}

	public Integer getPpayment() {
		return ppayment;
	}

	public void setPpayment(Integer ppayment) {
		this.ppayment = ppayment;
	}

	public String getPcreditCard() {
		return pcreditCard;
	}

	public void setPcreditCard(String pcreditCard) {
		this.pcreditCard = pcreditCard;
	}

	public String getPcreditCardCVV() {
		return pcreditCardCVV;
	}

	public void setPcreditCardCVV(String pcreditCardCVV) {
		this.pcreditCardCVV = pcreditCardCVV;
	}

	public Integer getPshipping() {
		return pshipping;
	}

	public void setPshipping(Integer pshipping) {
		this.pshipping = pshipping;
	}

	public Integer getPorderState() {
		return porderState;
	}

	public void setPorderState(Integer porderState) {
		this.porderState = porderState;
	}

}
