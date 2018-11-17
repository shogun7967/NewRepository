package com.student.MyLoginCheck;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VacantPositions {
	
	private String VacantPosition;
	private String JobDesc;
	private int Nospos;
	private String lastdate;
	
	public String getVacantPosition() {
		return VacantPosition;
	}
	public void setVacantPosition(String vacantPosition) {
		VacantPosition = vacantPosition;
	}
	public String getJobDesc() {
		return JobDesc;
	}
	public void setJobDesc(String jobDesc) {
		JobDesc = jobDesc;
	}
	public int getNospos() {
		return Nospos;
	}
	public void setNospos(int nospos) {
		Nospos = nospos;
	}
	public String getLastdate() {
		return lastdate;
	}
	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}
	

}
