package com.dietician.model;

import java.io.Serializable;
import java.sql.Date;

public class DieticianVO implements Serializable {

	private Integer dno;
	private String dname;
	private String daccount;
	private String dpassword;
	private Date dbirthDay;
	private String dpic;
	private String dphone;
	private String daddress;
	private String demail;
	private String edu;
	private String exp;
	private String lic;
	private String prof;
	private Integer clPrice;
	private Integer mprice;
	private String intro;
	private Integer dstate;
	private Integer totalScore;
	private Integer totalReviewer;
	private Integer donState;
	private String offDay;
	private String optTime;

	public DieticianVO() {
		super();
	}

	public DieticianVO(String dname, String daccount, String dpassword, Date dbirthDay, String dpic, String dphone,
			String daddress, String demail, String edu, String exp, String lic, String prof, Integer clPrice,
			Integer mprice, String intro, Integer dstate, Integer totalScore, Integer totalReviewer, Integer donState,
			String offDay, String optTime) {
		super();
		this.dname = dname;
		this.daccount = daccount;
		this.dpassword = dpassword;
		this.dbirthDay = dbirthDay;
		this.dpic = dpic;
		this.dphone = dphone;
		this.daddress = daddress;
		this.demail = demail;
		this.edu = edu;
		this.exp = exp;
		this.lic = lic;
		this.prof = prof;
		this.clPrice = clPrice;
		this.mprice = mprice;
		this.intro = intro;
		this.dstate = dstate;
		this.totalScore = totalScore;
		this.totalReviewer = totalReviewer;
		this.donState = donState;
		this.offDay = offDay;
		this.optTime = optTime;
	}

	public Integer getDno() {
		return dno;
	}

	public void setDno(Integer dno) {
		this.dno = dno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getDaccount() {
		return daccount;
	}

	public void setDaccount(String daccount) {
		this.daccount = daccount;
	}

	public String getDpassword() {
		return dpassword;
	}

	public void setDpassword(String dpassword) {
		this.dpassword = dpassword;
	}

	public Date getDbirthDay() {
		return dbirthDay;
	}

	public void setDbirthDay(Date dbirthDay) {
		this.dbirthDay = dbirthDay;
	}

	public String getDpic() {
		return dpic;
	}

	public void setDpic(String dpic) {
		this.dpic = dpic;
	}

	public String getDphone() {
		return dphone;
	}

	public void setDphone(String dphone) {
		this.dphone = dphone;
	}

	public String getDaddress() {
		return daddress;
	}

	public void setDaddress(String daddress) {
		this.daddress = daddress;
	}

	public String getDemail() {
		return demail;
	}

	public void setDemail(String demail) {
		this.demail = demail;
	}

	public String getEdu() {
		return edu;
	}

	public void setEdu(String edu) {
		this.edu = edu;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getLic() {
		return lic;
	}

	public void setLic(String lic) {
		this.lic = lic;
	}

	public String getProf() {
		return prof;
	}

	public void setProf(String prof) {
		this.prof = prof;
	}

	public Integer getClPrice() {
		return clPrice;
	}

	public void setClPrice(Integer clPrice) {
		this.clPrice = clPrice;
	}

	public Integer getMprice() {
		return mprice;
	}

	public void setMprice(Integer mprice) {
		this.mprice = mprice;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Integer getDstate() {
		return dstate;
	}

	public void setDstate(Integer dstate) {
		this.dstate = dstate;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public Integer getTotalReviewer() {
		return totalReviewer;
	}

	public void setTotalReviewer(Integer totalReviewer) {
		this.totalReviewer = totalReviewer;
	}

	public Integer getDonState() {
		return donState;
	}

	public void setDonState(Integer donState) {
		this.donState = donState;
	}

	public String getOffDay() {
		return offDay;
	}

	public void setOffDay(String offDay) {
		this.offDay = offDay;
	}

	public String getOptTime() {
		return optTime;
	}

	public void setOptTime(String optTime) {
		this.optTime = optTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dno == null) ? 0 : dno.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DieticianVO other = (DieticianVO) obj;
		if (dno == null) {
			if (other.dno != null)
				return false;
		} else if (!dno.equals(other.dno))
			return false;
		return true;
	}
}