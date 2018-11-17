package com.student.MyLoginCheck;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class LoginRepository {
	
	Connection con;
	List<Login> logins;
	List<Utility> utilities;
	public LoginRepository()
	{
		try{
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","root");
		}
        catch(Exception e)
        {
            System.out.println(e);
        }
		
	}
	
	public List<Login> getLogins()
	{
		List<Login> logins= new ArrayList<>();
		String sql = "select * from login";
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
			
			logins.add(ln);
        }
		
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		return logins;
	}
	
	
	
	
	public List<Constituency> GetAllConst()
	{
		List<Constituency> logins= new ArrayList<>();
		String sql = "select * from voteconstituency";
		try{
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
        {
			Constituency ln=new Constituency();
			ln.setConstcode(rs.getString(1));
			ln.setConstname(rs.getString(2));
			logins.add(ln);
        }
		
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		return logins;
	}
	
	public List<GetZeroVRNames> GetZero(String dt,String con1)
	{
		List<GetZeroVRNames> logins= new ArrayList<>();
		String sql = "select votername from votecandidates where votedate='"+dt+"' and consti ='"+con1+"' and votername not in (select canname from votevotes where votedate='"+dt+"' and consti ='"+con1+"')";
		try{
			Statement stmt=con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
        {
			GetZeroVRNames ln=new GetZeroVRNames();
			ln.setZeroname(rs.getString(1));
			logins.add(ln);
        }
		
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		return logins;
	}
	
	public List<Login> GetUnameDob(String un)
	{
		System.out.println("inside data fetch function");
		List<Login> logins= new ArrayList<>();
		String sql = "select * from login where uname='"+un+"'";
		try{
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
        {
			System.out.println("inside loop");
			Login ln=new Login();
			ln.setUsername(rs.getString(1));
			ln.setPassword(rs.getString(2));
			ln.setType(rs.getInt(3));
			ln.setEmail(rs.getString(4));
			ln.setConsti(rs.getString(5));
			ln.setDob(rs.getString(6));  
			logins.add(ln);
        }
		
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		return logins;
	}
	
	public List<VotingCandidates> GetCanList(String dv)
	{
		List<VotingCandidates> Candidates= new ArrayList<>();
		String sql = "select votername from votecandidates where votedate='"+dv+"'";
		try{
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
        {
			VotingCandidates ln=new VotingCandidates();
			ln.setVoteCName(rs.getString(1));
			Candidates.add(ln);
        }
		
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		return Candidates;
	}
	
	public List<LeaveBalance> GetEmpLeavBal(String ec)
	{
		List<LeaveBalance> LeaveBal= new ArrayList<>();
		String sql = "select * from hrmsleave where empcode='"+ec+"'";
		System.out.println(sql);
		try{
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
        {
			LeaveBalance ln=new LeaveBalance();
			ln.setEmpCode(rs.getString(1));
			ln.setTotalLeave(rs.getFloat(2));
			ln.setLeaveAvailed(rs.getFloat(3));
			ln.setBalanceLeave(rs.getFloat(4));
			LeaveBal.add(ln);
        }
		
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		return LeaveBal;
	}
	
	public List<AppliedLeaves> GetEmpAppliedLeavBal(String ec)
	{
		List<AppliedLeaves> LeaveBal= new ArrayList<>();
		String sql = "select * from hrmsleavetrans where empcode='"+ec+"'";
		System.out.println(sql);
		
		try{
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
        {
			AppliedLeaves ln=new AppliedLeaves();
			ln.setEmpCode(rs.getString(1));
			Date mystdate = rs.getDate(2);  
			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");  
			String stDate = dateFormat.format(mystdate);
			ln.setStartdt(stDate);
			Date mystdate1 = rs.getDate(3);  
			DateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yy");  
			String endDate = dateFormat.format(mystdate1);
			ln.setEnddate(endDate);
			ln.setTotalleaves(rs.getFloat(4));
			LeaveBal.add(ln);
        }
		
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		return LeaveBal;
	}
	
	public List<VoteResult> GetVoteResult(String dv,String co)
	{
		List<VoteResult> Candidates= new ArrayList<>();
		String sql = "select canname, count(*) as TotalCount from votevotes where votedate='"+dv+"' and Consti='"+co+"' group by canname";
		System.out.println(sql);
		try{
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
        {
			VoteResult ln=new VoteResult();
			ln.setCanName(rs.getString(1));
			ln.setVotecount(rs.getInt(2));
			Candidates.add(ln);
        }
		
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		return Candidates;
	}
	public List<Payroll> GetEmpPay(String Empcode)
	{
		List<Payroll> Payroll= new ArrayList<>();
		String sql = "select * from hrmspayroll where empcode='"+Empcode+"'";
		try{
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
        {
			Payroll ln=new Payroll();
			ln.setEmpCode(rs.getString(1));
			ln.setBasic(rs.getFloat(2));
			ln.setDA(rs.getFloat(3));
			ln.setHRA(rs.getFloat(4));
			ln.setCCA(rs.getFloat(5));
			ln.setPf(rs.getFloat(6));
			ln.setPtax(rs.getFloat(7));
			Payroll.add(ln);
        }
		
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		return Payroll;
	}
	
	//Get Utility
	public List<Utility> getUtility()
	{
		System.out.println("Inside get Utility");
		List<Utility> utilities= new ArrayList<>();
		String sql = "select * from utilitymaster";
		try{
			Statement stmt1 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql);
			while(rs1.next())
			{
				System.out.println("inside while loop");
				Utility ln=new Utility();
				ln.setUtilitytype(rs1.getString(1));
				ln.setUtilityprovidername(rs1.getString(2));
				ln.setCompanyname(rs1.getString(3));
				ln.setMobilenumber(rs1.getString(4));
				ln.setAdd1(rs1.getString(5));
				ln.setAdd2(rs1.getString(6));
				ln.setManagername(rs1.getString(7));
				ln.setRemarks(rs1.getString(8));
				ln.setRatings(rs1.getInt(9));
				ln.setUcode(rs1.getString(10));
				
				utilities.add(ln);
				}
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return utilities;
	}
	
	//Get Employee
		public List<EmpDetails> GetEmployee(String un)
		{
			System.out.println("Inside get Utility");
			List<EmpDetails> Emp= new ArrayList<>();
			String sql = "select * from hrmsemp where empcode='"+un+"'";
			try{
				Statement stmt1 = con.createStatement();
				ResultSet rs1 = stmt1.executeQuery(sql);
				while(rs1.next())
				{
					System.out.println("inside while loop");
					EmpDetails ln=new EmpDetails();
					ln.setFname(rs1.getString(1));
					ln.setLname(rs1.getString(2));
					ln.setDob(rs1.getString(3));
					ln.setAdd1(rs1.getString(4));
					ln.setAdd2(rs1.getString(5));
					ln.setCity(rs1.getString(6));
					ln.setPin(rs1.getString(7));
					ln.setEmail(rs1.getString(8));
					ln.setMobile(rs1.getString(9));
					ln.setEmpcode(rs1.getString(10));
					Emp.add(ln);
					}
			}catch(Exception e)
			{
				System.out.println(e);
			}
			return Emp;
		}
	//------------
		
	//Get Employee
	public List<GetAtt> GetAttendance(String un)
	{
		System.out.println("Inside get Attendance");
		List<GetAtt> EmpAtt= new ArrayList<>();
		String sql = "select * from hrmsatttable where empcode='"+un+"'";
		try{
			Statement stmt1 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql);
			while(rs1.next())
			{
				System.out.println("inside while loop");
				GetAtt ln=new GetAtt();
				ln.setEmpCode(rs1.getString(1));
				Date mydate = rs1.getDate(2);  
				DateFormat dateFormat = new SimpleDateFormat("dd-mmm-yy");  
				String strDate = dateFormat.format(mydate);
				ln.setAttDate(strDate);
				ln.setStTime(rs1.getString(3));
				ln.setEnTime(rs1.getString(4));
				EmpAtt.add(ln);
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return EmpAtt;
	}
			//------------
	public Login getLogin(String user)
	{
		String sql = "select * from login where uname='"+user+"'";
		Login ln=new Login();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
	        {
				
				ln.setUsername(rs.getString(1));
				ln.setPassword(rs.getString(2));
				ln.setType(rs.getInt(3));
	           
	        }
			
			}catch(Exception e)
			{
				System.out.println(e);
			}
		
			return ln;
	}
	
	
	public CurrentTime GetSystemTime()
	{
		String sql = "select to_char(SYSDATE, 'HH24:MI') from dual";
		CurrentTime ln=new CurrentTime();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		
				if(rs.next())
				{	
					ln.setNow(rs.getString(1));     
				}
			
			}catch(Exception e)
			{
				System.out.println(e);
			}
			
			return ln;
	}
	
	public List<ShortListed> GetShortListedApplicant()
	{
		String sql = "select Firstname,status from hrmsappreg where status='Selected'";
		List<ShortListed> shortlisted=new ArrayList<>();
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
	        {
				ShortListed ln=new ShortListed();
				ln.setFirstname(rs.getString(1));
				ln.setStatus(rs.getString(2));
				shortlisted.add(ln);
				
	        }
			
			}catch(Exception e)
			{
				System.out.println(e);
			}
		
			return shortlisted;
	}
	public void UpdateAtt(String studid,String Attd)
	{
		String sql = "Update attfacultyallot set attendance='"+Attd+"' where studid='"+studid+"'";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void DeleteUtility(String ProvName)
	{
		String sql = "delete from utilitymaster where utilityprovidername='"+ProvName+"'";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void UpdateVotedOnce(String un)
	{
		String sql = "Update voteauth set auth=0 where uname='"+un+"'";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void UpdatetheRatings(String uc,float rt,String rm)
	{
		String sql = "Update utilitymaster set RATINGS="+rt+",REMARKS='"+rm+"' where UCODE='"+uc+"'";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//CreateVoteCandidates
	
	public void CreateVoteCandidates(String vn,String dv) throws SQLException 
	{

		String sql = "Insert into votecandidates (Votername,Votedate) values (?,?)";
		try{
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, vn);
				java.util.Date MyDate =  new SimpleDateFormat("DD-MMM-YYYY").parse(dv); 
				stmt.setString(2,dv);
				stmt.executeUpdate();
			}catch(Exception e)
			{
				System.out.println(e);
			}
	}
	
	public void WriteConstituency(String ccode,String cname) throws SQLException 
	{

		String sql = "Insert into voteconstituency (constcode,constname) values (?,?)";
		try{
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, ccode);
				stmt.setString(2,cname);
				stmt.executeUpdate();
			}catch(Exception e)
			{
				System.out.println(e);
			}
	}
	
	public void CreateVotedCandidates(String vn,String dv,String co) throws SQLException 
	{

		String sql = "Insert into votevotes (Votedate,canname,Consti) values (?,?,?)";
		try{
				PreparedStatement stmt = con.prepareStatement(sql);
				java.util.Date MyDate =  new SimpleDateFormat("DD-MMM-YYYY").parse(dv); 
				stmt.setString(1,dv);
				stmt.setString(2,vn);
				stmt.setString(3, co);
				stmt.executeUpdate();
			}catch(Exception e)
			{
				System.out.println(e);
			}
	}
	//repo.Create(un,pw,ty,em,constt,dob,aad,nam);
	
	public void Create(String un,String pw,int ty,String em,String constt,String dob,String aad,String nam ) throws SQLException 
	{

		String sql = "Insert into login values (?,?,?,?,?,?,?,?)";
		try{
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, un);
				stmt.setString(2, pw);
				stmt.setInt(3, ty);
				stmt.setString(4, em);
				stmt.setString(5, constt);
				stmt.setString(6, dob);
				stmt.setString(7, aad);
				stmt.setString(8, nam);
				stmt.executeUpdate();
				
			}catch(Exception e)
			{
				System.out.println(e);
			}
		
		
	}
	
	public void ApplyLeave(String ec,String sd,String ed,float nol) throws SQLException 
	{

		String sql = "Insert into hrmsleaveTrans values (?,?,?,?)";
		try{
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, ec);
				stmt.setString(2, sd);
				stmt.setString(3, ed);
				stmt.setFloat(4, nol);
				stmt.executeUpdate();
			}catch(Exception e)
			{
				System.out.println(e);
			}
		
		
	}
	//vacpos,jd,nv,ld
	public void CreateVaPo(String vacpos,String jd,int nv,String ld) throws SQLException 
	{

		String sql = "Insert into hrmsvacpos values (?,?,?,?)";
		try{
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, vacpos);
				stmt.setString(2, jd);
				stmt.setInt(3, nv);
				stmt.setString(4, ld);
				stmt.executeUpdate();
			}catch(Exception e)
			{
				System.out.println(e);
			}
			System.out.println("Job Description is done");
		
	}
	
	public void StatusUpdate(String status,String un) throws SQLException 
	{
		un=un.trim();
		
		String sql = "update hrmsappreg set STATUS ='"+status+"' where FIRSTNAME = '"+un+"'";
		System.out.println("SQL : "+sql);
		try{
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.executeUpdate();
				con.close();
			}catch(Exception e)
			{
				System.out.println(e);
			}
			System.out.println("Status Updated is done");
		
	}
	public void DeleteApplicant(String an) throws SQLException 
	{
		an=an.trim();
		
		String sql = "delete from hrmsappreg where firstname ='"+an+"'";
		System.out.println("SQL : "+sql);
		try{
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.executeUpdate();
				con.close();
			}catch(Exception e)
			{
				System.out.println(e);
			}
			System.out.println("Status Updated is done");
		
	}
	
	public void DeleteConst(String an) throws SQLException 
	{
		an=an.trim();
		
		String sql = "delete from voteconstituency where CONSTCODE ='"+an+"'";
		System.out.println("SQL : "+sql);
		try{
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.executeUpdate();
				con.close();
			}catch(Exception e)
			{
				System.out.println(e);
			}
			System.out.println("Delete is done");
		
	}
	//HRMSEmpRegistration
	
	public void HRMSEmpRegistration(String fn,String ln,String dob,String add1,String add2,String city,String pin,String email,String mobile, String empcode) throws SQLException 
	{

		String sql = "Insert into hrmsemp values (?,?,?,?,?,?,?,?,?,?)";
		try{
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, fn);
				stmt.setString(2, ln);
				stmt.setString(3,dob);
				stmt.setString(4,add1);
				stmt.setString(5,add2);
				stmt.setString(6,city);
				stmt.setString(7,pin);
				stmt.setString(8,email);
				stmt.setString(9,mobile);
				stmt.setString(10,empcode);
				stmt.executeUpdate();
			}catch(Exception e)
			{
				System.out.println(e);
			}
	}
	
	public void HRMSAttRegistration(String ec,String ad,String stt,String ent) throws SQLException 
	{

		String sql = "Insert into hrmsatttable  values (?,?,?,?)";
		try{
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, ec);
				stmt.setString(2, ad);
				stmt.setString(3,stt);
				stmt.setString(4,ent);
				
				stmt.executeUpdate();
			}catch(Exception e)
			{
				System.out.println(e);
			}
	}
	
	public void HRMSPayrollUpdate(String ec,float ba,float da,float hra,float cca,float pf,float ptax) throws SQLException 
	{

		String sql = "Insert into hrmspayroll  values (?,?,?,?,?,?,?)";
		try{
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, ec);
				stmt.setFloat(2, ba);
				stmt.setFloat(3,da);
				stmt.setFloat(4,hra);
				stmt.setFloat(5,cca);
				stmt.setFloat(6,pf);
				stmt.setFloat(7,ptax);
				
				stmt.executeUpdate();
			}catch(Exception e)
			{
				System.out.println(e);
			}
	}

	public void UpdateUtilityTable(String ut,String pn,String cn,String mn,String add1,String add2,String man,String uc) throws SQLException 
	{

		String sql = "Insert into UTILITYMASTER (UTILITYTYPE,UTILITYPROVIDERNAME,COMPANYNAME,MOBILENUMBER,ADD1,ADD2,MANAGERNAME,UCODE)  values (?,?,?,?,?,?,?,?)";
		try{
				
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, ut);
				stmt.setString(2, pn);
				stmt.setString(3,cn);
				stmt.setString(4,mn);
				stmt.setString(5,add1);
				stmt.setString(6,add2);
				stmt.setString(7,man);
				stmt.setString(8,uc);
				
				stmt.executeUpdate();
			}catch(Exception e)
			{
				System.out.println(e);
			}
	}
	public void HRMSRegistration(String fn,String ln,String dob,String add1,String add2,String city,String pin,String email,String mobile, String position) throws SQLException 
	{
		System.out.println("Inside HRMSRegistration FUNCTION");
		String sql = "Insert into HRMSAppreg (FIRSTNAME,LASTNAME,DOB,ADD1,ADD2,CITY,PINCODE,EMAIL,MOBILE,POSITION) values (?,?,?,?,?,?,?,?,?,?)";
		try{
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, fn);
				stmt.setString(2, ln);
				stmt.setString(3,dob);
				stmt.setString(4,add1);
				stmt.setString(5,add2);
				stmt.setString(6,city);
				stmt.setString(7,pin);
				stmt.setString(8,email);
				stmt.setString(9,mobile);
				stmt.setString(10,position);
				stmt.executeUpdate();
			}catch(Exception e)
			{
				System.out.println(e);
			}
	}
	
	public void UpdateProfile(String fn,String ln,String dob,String add1,String add2,String city,String pin,String email,String mobile, String rollno) throws SQLException 
	{

		String sql = "update hrmsemp set FIRSTNAME='"+fn+"',lastname='"+ln+"',dob='"+dob+"',add1='"+add1+"', add2='"+add2+"', city='"+city+"', pincode='"+pin+"', email='"+email+"', mobile='"+mobile+"' where empcode='"+rollno+"'";
		try{
				System.out.println("Inside UpdateProfile");
				System.out.println(sql);
				System.out.println(fn+ln+dob+add1+add2+city+pin+email+mobile+rollno);
				PreparedStatement stmt = con.prepareStatement(sql);
				/*stmt.setString(1, fn);
				stmt.setString(2, ln);
				stmt.setString(3,dob);
				stmt.setString(4,add1);
				stmt.setString(5,add2);
				stmt.setString(6,city);
				stmt.setString(7,pin);
				stmt.setString(8,email);
				stmt.setString(9,mobile);
				stmt.setString(10,rollno);*/
				stmt.executeUpdate();
			}catch(Exception e)
			{
				System.out.println(e);
			}
		
	}
	
	public void UpdateConst(String cn,String cc) throws SQLException 
	{

		String sql = "update voteconstituency set CONSTNAME='"+cn+"' where CONSTCODE='"+cc+"'";
		try{
				System.out.println("Inside Update Constituency");
				
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.executeUpdate();
			}catch(Exception e)
			{
				System.out.println(e);
			}
		
	}
	
	
	public List<ApplicationRegistration> GetApplicants() throws SQLException 
	{

		
		List<ApplicationRegistration> Applicants= new ArrayList<>();
		String sql = "select * from HRMSAppreg";
		try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next())
				{
					ApplicationRegistration ln=new ApplicationRegistration();
					ln.setFname(rs.getString(1));
					ln.setLname(rs.getString(2));
					ln.setDob(rs.getString(3));
					ln.setAdd1(rs.getString(4));
					ln.setAdd2(rs.getString(5));
					ln.setCity(rs.getString(6));
					ln.setPin(rs.getString(7));
					ln.setEmail(rs.getString(8));
					ln.setMobile(rs.getString(9));
					ln.setPosition(rs.getString(10));
					Applicants.add(ln);
				
				}
			}catch(Exception e)
			{
				System.out.println(e);
			}
		return Applicants;
		
	}
	
	public List<VacantPositions> getJobs()
	{
		List<VacantPositions> jobs= new ArrayList<>();
		String sql = "select * from hrmsvacpos";
		try{
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
        {
			System.out.println("Inside RS.Next");
			VacantPositions ln=new VacantPositions();
			ln.setVacantPosition(rs.getString(1));
			System.out.println(rs.getString(1));
			ln.setJobDesc(rs.getString(2));
			System.out.println(rs.getString(2));
			ln.setNospos(rs.getInt(3));
			ln.setLastdate(rs.getString(4));
			System.out.println(rs.getString(4));
            jobs.add(ln);
        }
		
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		return jobs;
	}
	
	public List<AppReg> GetEmail(String un)
	{
		System.out.println("Inside get Attendance");
		List<AppReg> appliReg= new ArrayList<>();
		String sql = "select * from hrmsappreg where firstname='"+un+"'";
		try{
			Statement stmt1 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql);
			while(rs1.next())
			{
				System.out.println("inside while loop");
				AppReg ln=new AppReg();
				
				ln.setFn(rs1.getString(1));
				ln.setLn(rs1.getString(2));
				ln.setDob(rs1.getString(3));
				ln.setAdd1(rs1.getString(4));
				ln.setAdd2(rs1.getString(5));
				ln.setCity(rs1.getString(6));
				ln.setPin(rs1.getString(7));
				ln.setEmail(rs1.getString(8));
				ln.setMob(rs1.getString(9));
				ln.setPos(rs1.getString(10));
		
				appliReg.add(ln);
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return appliReg;
	}
	
	public void UpdatePassWord(String un,String npw)
	{
		String sql = "Update Login set passwe='"+npw+"' where uname='"+un+"'";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public List<EmpDetails> MyGetEmpNames()
	{
		System.out.println("Inside get Attendance");
		List<EmpDetails> appliReg= new ArrayList<>();
		String sql = "select * from hrmsemp";
		try{
			Statement stmt1 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql);
			while(rs1.next())
			{
				System.out.println("inside while loop");
				EmpDetails ln=new EmpDetails();
				
				ln.setFname(rs1.getString(1));
				ln.setLname(rs1.getString(2));
				ln.setDob(rs1.getString(3));
				ln.setAdd1(rs1.getString(4));
				ln.setAdd2(rs1.getString(5));
				ln.setCity(rs1.getString(6));
				ln.setPin(rs1.getString(7));
				ln.setEmail(rs1.getString(8));
				ln.setMobile(rs1.getString(9));
				ln.setEmpcode(rs1.getString(10));
		
				appliReg.add(ln);
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return appliReg;
	}
	
	

}
