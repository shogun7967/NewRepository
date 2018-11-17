package MyVoteCheck.VoteCheck;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Login {
	
	private String username;
	private String password;
	private int type;
	private String email;
	private String consti;
	private String dob;
	private String aadhar;
	private String name;
	
	public String getAadhar() {
		return aadhar;
	}
	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getConsti() {
		return consti;
	}
	public void setConsti(String consti) {
		this.consti = consti;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	

}
