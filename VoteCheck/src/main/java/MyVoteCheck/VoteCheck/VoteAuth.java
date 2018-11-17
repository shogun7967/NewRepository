package MyVoteCheck.VoteCheck;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VoteAuth {
	private String uname;
	private int auth;
	private String mobID;
	
	public String getMobID() {
		return mobID;
	}
	public void setMobID(String mobID) {
		this.mobID = mobID;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	
	
	
	

}
