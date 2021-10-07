package com.product.model;

import java.sql.Date;

public class ProductVO implements java.io.Serializable {

	private Integer pno;
	private String categoryName;
	private String pname;
	private Integer pprice;
	private String pinfo;
	private Integer pquantity;
	private Date ponDate;
	private Date poffDate;
	private String pimage1;
	private String pimage2;
	private String pimage3;
	private Integer pratingsQuantity;
	private Integer ptotalRatings;
	private Integer pstate;
	private Integer quantity;

	public ProductVO() {
		super();
	}

	public ProductVO(Integer pno, String categoryName, String pname, Integer pprice, String pinfo, Integer pquantity,
			Date ponDate, Date poffDate, String pimage1, String pimage2, String pimage3,
			Integer pratingsQuantity, Integer ptotalRatings, Integer pstate) {
		super();
		this.pno = pno;
		this.categoryName = categoryName;
		this.pname = pname;
		this.pprice = pprice;
		this.pinfo = pinfo;
		this.pquantity = pquantity;
		this.ponDate = ponDate;
		this.poffDate = poffDate;
		this.pimage1 = pimage1;
		this.pimage2 = pimage2;
		this.pimage3 = pimage3;
		this.pratingsQuantity = pratingsQuantity;
		this.ptotalRatings = ptotalRatings;
		this.pstate = pstate;
	}

	public Integer getPno() {
		return pno;
	}

	public void setPno(Integer pno) {
		this.pno = pno;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getPprice() {
		return pprice;
	}

	public void setPprice(Integer pprice) {
		this.pprice = pprice;
	}

	public String getPinfo() {
		return pinfo;
	}

	public void setPinfo(String pinfo) {
		this.pinfo = pinfo;
	}

	public Integer getPquantity() {
		return pquantity;
	}

	public void setPquantity(Integer pquantity) {
		this.pquantity = pquantity;
	}

	public Date getPonDate() {
		return ponDate;
	}

	public void setPonDate(Date ponDate) {
		this.ponDate = ponDate;
	}

	public Date getPoffDate() {
		return poffDate;
	}

	public void setPoffDate(Date poffDate) {
		this.poffDate = poffDate;
	}

	public String getPimage1() {
		return pimage1;
	}

	public void setPimage1(String pimage1) {
		this.pimage1 = pimage1;
	}

	public String getPimage2() {
		return pimage2;
	}

	public void setPimage2(String pimage2) {
		this.pimage2 = pimage2;
	}

	public String getPimage3() {
		return pimage3;
	}

	public void setPimage3(String pimage3) {
		this.pimage3 = pimage3;
	}

	public Integer getPratingsQuantity() {
		return pratingsQuantity;
	}

	public void setPratingsQuantity(Integer pratingsQuantity) {
		this.pratingsQuantity = pratingsQuantity;
	}

	public Integer getPtotalRatings() {
		return ptotalRatings;
	}

	public void setPtotalRatings(Integer ptotalRatings) {
		this.ptotalRatings = ptotalRatings;
	}

	public Integer getPstate() {
		return pstate;
	}

	public void setPstate(Integer pstate) {
		this.pstate = pstate;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}	
	
	
	@Override
	public String toString() {
		return "ProductVO [pno=" + pno + ", categoryName=" + categoryName + ", pname=" + pname + ", pprice=" + pprice
				+ ", pinfo=" + pinfo + ", pquantity=" + pquantity + ", ponDate=" + ponDate + ", poffDate=" + poffDate
				+ ", pimage1=" + pimage1 + ", pimage2=" + pimage2 + ", pimage3=" + pimage3 + ", pratingsQuantity="
				+ pratingsQuantity + ", ptotalRatings=" + ptotalRatings + ", pstate=" + pstate + ", quantity="
				+ quantity + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime * result + ((pimage1 == null) ? 0 : pimage1.hashCode());
		result = prime * result + ((pimage2 == null) ? 0 : pimage2.hashCode());
		result = prime * result + ((pimage3 == null) ? 0 : pimage3.hashCode());
		result = prime * result + ((pinfo == null) ? 0 : pinfo.hashCode());
		result = prime * result + ((pname == null) ? 0 : pname.hashCode());
		result = prime * result + ((pno == null) ? 0 : pno.hashCode());
		result = prime * result + ((poffDate == null) ? 0 : poffDate.hashCode());
		result = prime * result + ((ponDate == null) ? 0 : ponDate.hashCode());
		result = prime * result + ((pprice == null) ? 0 : pprice.hashCode());
		result = prime * result + ((pquantity == null) ? 0 : pquantity.hashCode());
		result = prime * result + ((pratingsQuantity == null) ? 0 : pratingsQuantity.hashCode());
		result = prime * result + ((pstate == null) ? 0 : pstate.hashCode());
		result = prime * result + ((ptotalRatings == null) ? 0 : ptotalRatings.hashCode());
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
		ProductVO other = (ProductVO) obj;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		if (pimage1 == null) {
			if (other.pimage1 != null)
				return false;
		} else if (!pimage1.equals(other.pimage1))
			return false;
		if (pimage2 == null) {
			if (other.pimage2 != null)
				return false;
		} else if (!pimage2.equals(other.pimage2))
			return false;
		if (pimage3 == null) {
			if (other.pimage3 != null)
				return false;
		} else if (!pimage3.equals(other.pimage3))
			return false;
		if (pinfo == null) {
			if (other.pinfo != null)
				return false;
		} else if (!pinfo.equals(other.pinfo))
			return false;
		if (pname == null) {
			if (other.pname != null)
				return false;
		} else if (!pname.equals(other.pname))
			return false;
		if (pno == null) {
			if (other.pno != null)
				return false;
		} else if (!pno.equals(other.pno))
			return false;
		if (poffDate == null) {
			if (other.poffDate != null)
				return false;
		} else if (!poffDate.equals(other.poffDate))
			return false;
		if (ponDate == null) {
			if (other.ponDate != null)
				return false;
		} else if (!ponDate.equals(other.ponDate))
			return false;
		if (pprice == null) {
			if (other.pprice != null)
				return false;
		} else if (!pprice.equals(other.pprice))
			return false;
		if (pquantity == null) {
			if (other.pquantity != null)
				return false;
		} else if (!pquantity.equals(other.pquantity))
			return false;
		if (pratingsQuantity == null) {
			if (other.pratingsQuantity != null)
				return false;
		} else if (!pratingsQuantity.equals(other.pratingsQuantity))
			return false;
		if (pstate == null) {
			if (other.pstate != null)
				return false;
		} else if (!pstate.equals(other.pstate))
			return false;
		if (ptotalRatings == null) {
			if (other.ptotalRatings != null)
				return false;
		} else if (!ptotalRatings.equals(other.ptotalRatings))
			return false;
		return true;
	}

	
}
