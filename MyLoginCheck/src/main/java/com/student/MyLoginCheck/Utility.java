package com.student.MyLoginCheck;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Utility {
	
	private String utilitytype;
	private String utilityprovidername;
	private String companyname;
	private String mobilenumber;
	private String add1;
	private String add2;
	private String managername;
	private int ratings;
	private String remarks;
	private String ucode;
	
	public String getUcode() {
		return ucode;
	}
	public void setUcode(String ucode) {
		this.ucode = ucode;
	}
	public String getUtilitytype() {
		return utilitytype;
	}
	public void setUtilitytype(String utilitytype) {
		this.utilitytype = utilitytype;
	}
	
	public String getUtilityprovidername() {
		return utilityprovidername;
	}
	public void setUtilityprovidername(String utilityprovidername) {
		this.utilityprovidername = utilityprovidername;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getAdd1() {
		return add1;
	}
	public void setAdd1(String add1) {
		this.add1 = add1;
	}
	public String getAdd2() {
		return add2;
	}
	public void setAdd2(String add2) {
		this.add2 = add2;
	}
	public String getManagername() {
		return managername;
	}
	public void setManagername(String managername) {
		this.managername = managername;
	}
	public int getRatings() {
		return ratings;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

}
