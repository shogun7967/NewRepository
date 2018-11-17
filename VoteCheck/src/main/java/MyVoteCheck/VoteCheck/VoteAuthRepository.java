package MyVoteCheck.VoteCheck;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class VoteAuthRepository {
	Connection con;
	List<VoteAuth> voteauth;
	
	public VoteAuthRepository()
	{
		try{
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.6:1521:XE","system","root");
		}
        catch(Exception e)
        {
            System.out.println(e);
        }	
	}
	
	public List<VoteAuth> getAuthentications()
	{
		List<VoteAuth> voteauth = new ArrayList<>();
		String sql = "select * from voteauth";
		//where uname='"+"shogun"+"'";
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
	        {
				System.out.println("Inside the rs.next");
				VoteAuth ln=new VoteAuth();
				ln.setUname(rs.getString(1));
				ln.setAuth(rs.getInt(2));
				ln.setMobID(rs.getString(3));
				voteauth.add(ln);
	        }
			
			}catch(Exception e)
			{
				System.out.println(e);
			}
			return voteauth;
	}
	
	public void CreateMobID(String un,String mid,int ty) throws SQLException 
	{

		String sql = "Insert into voteauth values (?,?,?)";
		try{
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, un);
				stmt.setInt(2, 0);
				stmt.setString(3, mid);
				stmt.executeUpdate();
			}catch(Exception e)
			{
				System.out.println(e);
			}
	
	}
	
	public List<Login> GetAllAuth()
	{
		List<Login> voteauth = new ArrayList<>();
		String sql = "select * from login where uname in (select uname from voteauth where auth=0)";
				
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
	        {
				Login ln=new Login();
				ln.setUsername(rs.getString(1));
				ln.setPassword(rs.getString(2));
				ln.setType(rs.getInt(3));
				ln.setEmail(rs.getString(4));
				ln.setConsti(rs.getString(5));
				ln.setDob(rs.getString(6));
				ln.setAadhar(rs.getString(7));
				ln.setName(rs.getString(8));
				voteauth.add(ln);
	        }
			
			}catch(Exception e)
			{
				System.out.println(e);
			}
			return voteauth;
	}
	
	public void SetUpdateStat(String uname)
	{
		String sql = "Update voteauth set auth=1 where uname='"+uname+"'";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
