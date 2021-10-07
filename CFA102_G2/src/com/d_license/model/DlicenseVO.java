package com.d_license.model;

public class DlicenseVO {

	private Integer dlicNo;
	private Integer dno;
	private String licDesc;
	private String licFile;

	public DlicenseVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DlicenseVO(Integer dno, String licDesc, String licFile) {
		super();

		this.dno = dno;
		this.licDesc = licDesc;
		this.licFile = licFile;
	}
 
	public Integer getDlicNo() {
		return dlicNo;
	}

	public void setDlicNo(Integer dlicNo) {
		this.dlicNo = dlicNo;
	}

	public Integer getDno() {
		return dno;
	}

	public void setDno(Integer dno) {
		this.dno = dno;
	}

	public String getLicDesc() {
		return licDesc;
	}

	public void setLicDesc(String licDesc) {
		this.licDesc = licDesc;
	}

	public String getLicFile() {
		return licFile;
	}

	public void setLicFile(String licFile) {
		this.licFile = licFile;
	}

}
