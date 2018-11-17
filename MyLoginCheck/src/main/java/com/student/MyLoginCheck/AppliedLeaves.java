package com.student.MyLoginCheck;

import java.sql.Date;

public class AppliedLeaves {
	
	private String EmpCode;
	private String startdt;
	private String enddate;
	private float totalleaves;
	public String getEmpCode() {
		return EmpCode;
	}
	public void setEmpCode(String empCode) {
		EmpCode = empCode;
	}
	public String getStartdt() {
		return startdt;
	}
	public void setStartdt(String startdt) {
		this.startdt = startdt;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public float getTotalleaves() {
		return totalleaves;
	}
	public void setTotalleaves(float totalleaves) {
		this.totalleaves = totalleaves;
	}
	
	

}
