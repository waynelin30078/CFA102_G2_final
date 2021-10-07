package com.admin.model;

import java.io.Serializable;

public class AdminVO implements Serializable {
	private Integer ano;
	private String aname;
	private String aid;
	private String apsw;

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getApsw() {
		return apsw;
	}

	public void setApsw(String apsw) {
		this.apsw = apsw;
	}

}
