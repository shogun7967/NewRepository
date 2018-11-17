package com.student.MyLoginCheck;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


import org.omg.CORBA.portable.OutputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import com.student.MyLoginCheck.Login;



@Path("Login")
public class LoginResource {
	
	LoginRepository repo = new LoginRepository();
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Login> getStudents()
	{
		return repo.getLogins();
	}
		
	@GET
	@Path("EmpDetail/{un}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<EmpDetails> GetEmpDetail(@PathParam("un") String un)
	{
		return repo.GetEmployee(un);
	}
	
	@POST
	@Path("RestPost/{MSG}")
	@Produces({MediaType.APPLICATION_JSON})
	public String AddMessage(@PathParam("MSG") String msg)
	{
		return msg;
	}
	
	@GET
	@Path("UDOB/{un}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Login> GetUnameDOB(@PathParam("dob") String un)
	{
		System.out.println("Path is found correctly");
		return repo.GetUnameDob(un);
	}
	
	@GET
	@Path("Const")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Constituency> GetConstitution()
	{
		System.out.println("got Const");
		return repo.GetAllConst();
	}
	
	@GET
	@Path("Time")
	@Produces({MediaType.APPLICATION_JSON})
	public CurrentTime GetTime()
	{
		System.out.println("Get Time");
		return repo.GetSystemTime();
	}
	
	@GET
	@Path("GetZeroVotes/{dt}/{con}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<GetZeroVRNames> GetZeroVotes(@PathParam("dt") String dt,@PathParam("con") String con)
	{
		System.out.println("got Const");
		return repo.GetZero(dt,con);
	}
	
	@GET
	@Path("PayRoll/{empcode}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Payroll> GetPayrollDetail(@PathParam("empcode") String empcode)
	{
		return repo.GetEmpPay(empcode);
	}
	
	@GET
	@Path("UpdateRatings/{uc}/{rt}/{rm}")
	@Produces({MediaType.APPLICATION_JSON})
	public void UpdateRatings(@PathParam("uc") String uc,@PathParam("rt") float rt,@PathParam("rm") String rm)
	{
		 repo.UpdatetheRatings(uc,rt,rm);
	}
	
	@GET
	@Path("AttDetail/{un}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<GetAtt> GetEmpAtt(@PathParam("un") String un)
	{
		return repo.GetAttendance(un);
	}
	
	@GET
	@Path("LogReg/{un}/{pw}/{ty}/{em}/{constt}/{dob}/{aad}/{nam}")
	@Produces(MediaType.APPLICATION_JSON)
	public Login CreateStudent(@PathParam("un") String un,@PathParam("pw") String pw,@PathParam("ty") int ty,@PathParam("em") String em,@PathParam("constt") String constt,@PathParam("dob") String dob,@PathParam("aad") String aad,@PathParam("nam") String nam)	    
	{
		System.out.println("I am inside POST");
		System.out.println("un "+un+"pw "+pw+"ty "+ty);
		try {
			repo.Create(un,pw,ty,em,constt,dob,aad,nam);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successfully Written");
		Login l1=new Login();
		l1.setPassword(pw);
		l1.setUsername(un);
		l1.setType(ty);
		l1.setEmail(em);
		l1.setConsti(constt);
		l1.setDob(dob);
		l1.setAadhar(aad);
		l1.setName(nam);
		return l1;
	}
	
	
	
	@GET
	@Path("ConstAdd/{cc}/{cn}")
	@Produces(MediaType.APPLICATION_JSON)
	public void CreateConsti(@PathParam("cc") String cc,@PathParam("cn") String cn)	    
	{
		System.out.println("I am inside VotingCandidate");
		//System.out.println("un "+un+"pw "+pw+"ty "+ty);
		try {
			repo.WriteConstituency(cc,cn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successfully Written");
		
	}
	
	@GET
	@Path("DeleteConst/{cn}")
	@Produces(MediaType.APPLICATION_JSON)
	public void CreateConsti(@PathParam("cn") String cn)	    
	{
		System.out.println("I am inside Delete Constituencty");
		//System.out.println("un "+un+"pw "+pw+"ty "+ty);
		try {
			repo.DeleteConst(cn);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println("Successfully Deleted the Constituency");
		
	}
	
	@GET
	@Path("UpdateConst/{cn}/{cc}")
	@Produces(MediaType.APPLICATION_JSON)
	public void UpdateCons(@PathParam("cn") String cn,@PathParam("cc") String cc)	    
	{
		System.out.println("I am inside Update Constituencty");
	
		try {
			repo.UpdateConst(cn,cc);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println("Successfully Updated the Constituency");
		
	}
	
	@GET
	@Path("Voted/{vn}/{dv}/{co}")
	@Produces(MediaType.APPLICATION_JSON)
	public void CreateVoted(@PathParam("vn") String vn,@PathParam("dv") String dv,@PathParam("co") String co)	    
	{
		System.out.println("I am inside VotingCandidate");
		//System.out.println("un "+un+"pw "+pw+"ty "+ty);
		try {
			repo.CreateVotedCandidates(vn,dv,co);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successfully Written");
		
	}
	
	
	@GET
	@Path("GetCanList/{dv}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<VotingCandidates> GetListCan(@PathParam("dv") String dv)	    
	{
		System.out.println("I am inside POST");
		return repo.GetCanList(dv);
			
	}
	
	@GET
	@Path("LeaveBalance/{ec}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<LeaveBalance> GetLeaveBal(@PathParam("ec") String ec)	    
	{
		System.out.println("I am inside POST");
		return repo.GetEmpLeavBal(ec);
			
	}
	
	@GET
	@Path("AppliedLeaves/{ec}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AppliedLeaves> GetAppliedLeave(@PathParam("ec") String ec)	    
	{
		System.out.println("I am inside POST");
		return repo.GetEmpAppliedLeavBal(ec);
			
	}
	
	@GET
	@Path("VoteResults/{dv}/{con}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<VoteResult> GetVResults(@PathParam("dv") String dv,@PathParam("con") String con)	    
	{
		System.out.println("I am inside POST");
		return repo.GetVoteResult(dv,con);
			
	}
	
	@GET
	@Path("Leave/{ec}/{sd}/{ed}/{nol}")
	@Produces(MediaType.APPLICATION_JSON)
	public Leave ApplyLeave(@PathParam("ec") String ec,@PathParam("sd") String sd,@PathParam("ed") String ed,@PathParam("nol") float nol)	    
	{
		System.out.println("I am inside POST");
		//System.out.println("un "+un+"pw "+pw+"ty "+ty);
		try {
			repo.ApplyLeave(ec,sd,ed,nol);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successfully Written");
		Leave l1=new Leave();
		l1.setEmpcode(ec);
		l1.setStartdt(sd);
		l1.setEnddt(ed);
		l1.setNol(nol);
		return l1;
	}
	
	
	@GET
	@Path("VaPo/{vacpos}/{JD}/{NV}/{LD}")
	@Produces(MediaType.APPLICATION_JSON)
	public VacantPositions CreateVacPos(@PathParam("vacpos") String vacpos,@PathParam("JD") String jd,@PathParam("NV") int nv,@PathParam("LD") String ld)	    
	{
		System.out.println("I am inside Vacant Position");
		//System.out.println("un "+un+"pw "+pw+"ty "+ty);
		try {
			repo.CreateVaPo(vacpos,jd,nv,ld);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successfully Written");
		VacantPositions l1=new VacantPositions();
		l1.setVacantPosition(vacpos);
		l1.setJobDesc(jd);
		l1.setNospos(nv);
		l1.setLastdate(ld);
		return l1;
	}
	
	@GET
	@Path("GetApplicant")
	@Produces(MediaType.APPLICATION_JSON)
	public  List<ApplicationRegistration> GetAllApplicants() throws SQLException 	    
	{
		System.out.println("I am inside get Applicants Position");

			return repo.GetApplicants();
		
		
	}

	@GET
	@Path("GetShortListed")
	@Produces(MediaType.APPLICATION_JSON)
	public  List<ShortListed> GetShortListedCandidates() throws SQLException 	    
	{
		System.out.println("I am inside get Short Listed Applicants Position");
		return repo.GetShortListedApplicant();
	}
	
	@GET
	@Path("GetApplicant/{status}/{un}")
	@Produces(MediaType.APPLICATION_JSON)
	public  void UpdateStatus(@PathParam("status") String status,@PathParam("un") String un) throws SQLException 	    
	{
		System.out.println("Moving to Status Update");
		repo.StatusUpdate(status,un);
		
		
	}
	
	@GET
	@Path("DeleteApplicant/{an}")
	@Produces(MediaType.APPLICATION_JSON)
	public  void UpdateDelete(@PathParam("an") String an) throws SQLException 	    
	{
		System.out.println("Moving to Status Update");
		repo.DeleteApplicant(an);
		
		
	}
	
	@GET
	@Path("Log/{fn}/{ln}/{dob}/{add1}/{add2}/{city}/{pin}/{email}/{mob}/{pos}")
	@Produces(MediaType.APPLICATION_JSON)
	public ApplicationRegistration CreateStudent(@PathParam("fn") String fn,@PathParam("ln") String ln,@PathParam("dob") String dob,@PathParam("add1") String add1,@PathParam("add2") String add2,@PathParam("city") String city,@PathParam("pin") String pin,@PathParam("email") String email,@PathParam("mob") String mob,@PathParam("pos") String pos)	    
	{
		System.out.println("I am inside POST");
		//System.out.println("un "+un+"pw "+pw+"ty "+ty);
		try {
			repo.HRMSRegistration(fn,ln,dob,add1,add2,city,pin,email,mob,pos);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successfully Written");
		ApplicationRegistration l1=new ApplicationRegistration();
		l1.setFname(fn);
		l1.setLname(ln);
		l1.setDob(dob);
		l1.setAdd1(add1);
		l1.setAdd2(add2);
		l1.setCity(city);
		l1.setPin(pin);
		l1.setEmail(email);
		l1.setMobile(mob);
		l1.setPosition(pos);
		return l1;
	}
	
	@GET
	@Path("EmpLog/{fn}/{ln}/{dob}/{add1}/{add2}/{city}/{pin}/{email}/{mob}/{empc}")
	@Produces(MediaType.APPLICATION_JSON)
	public void CreateEmp(@PathParam("fn") String fn,@PathParam("ln") String ln,@PathParam("dob") String dob,@PathParam("add1") String add1,@PathParam("add2") String add2,@PathParam("city") String city,@PathParam("pin") String pin,@PathParam("email") String email,@PathParam("mob") String mob,@PathParam("empc") String empc)	    
	{
		System.out.println("I am inside POST");
		//System.out.println("un "+un+"pw "+pw+"ty "+ty);
		try {
			repo.HRMSEmpRegistration(fn,ln,dob,add1,add2,city,pin,email,mob,empc);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successfully Written");
	
	}
	
	@GET
	@Path("EmpAttUpdate/{ec}/{ad}/{stt}/{ent}")
	@Produces(MediaType.APPLICATION_JSON)
	public void CreateEmpAtt(@PathParam("ec") String ec,@PathParam("ad") String ad,@PathParam("stt") String stt,@PathParam("ent") String ent)	    
	{
		System.out.println("I am inside POST");
		try {
			repo.HRMSAttRegistration(ec,ad,stt,ent);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successfully Written");
	
	}
	
	@GET
	@Path("HrmsPayroll/{ec}/{ba}/{da}/{hra}/{cca}/{pf}/{ptax}")
	@Produces(MediaType.APPLICATION_JSON)
	public void CreateEmpPayroll(@PathParam("ec") String ec,@PathParam("ba") float ba,@PathParam("da") float da,@PathParam("hra") float hra,@PathParam("cca") float cca,@PathParam("pf") float pf,@PathParam("ptax") float ptax)	    
	{
		System.out.println("I am inside POST");
		try {
			repo.HRMSPayrollUpdate(ec,ba,da,hra,cca,pf,ptax);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successfully Written");
	
	}
	
	@GET
	@Path("UtilityUpdate/{ut}/{pn}/{cn}/{mn}/{add1}/{add2}/{man}/{uc}")
	@Produces(MediaType.APPLICATION_JSON)
	public void CreateUtilUpdate(@PathParam("ut") String ut,@PathParam("pn") String pn,@PathParam("cn") String cn,@PathParam("mn") String mn,@PathParam("add1") String add1,@PathParam("add2") String add2,@PathParam("man") String man,@PathParam("uc") String uc)	    
	{
		System.out.println("I am inside POST");
		try {
			repo.UpdateUtilityTable(ut,pn,cn,mn,add1,add2,man,uc);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successfully Written");
	
	}
	
	@GET
	@Path("UpdProf/{fn}/{ln}/{dob}/{add1}/{add2}/{city}/{pin}/{email}/{mob}/{rn}")
	@Produces(MediaType.APPLICATION_JSON)
	public EmpDetails UpdateProfile(@PathParam("fn") String fn,@PathParam("ln") String ln,@PathParam("dob") String dob,@PathParam("add1") String add1,@PathParam("add2") String add2,@PathParam("city") String city,@PathParam("pin") String pin,@PathParam("email") String email,@PathParam("mob") String mob,@PathParam("rn") String rn)	    
	{
		System.out.println("I am inside Update Profile");
		System.out.println(fn+ln+dob+add1+add2+city+pin+email+mob+rn);
		
		try {
			repo.UpdateProfile(fn,ln,dob,add1,add2,city,pin,email,mob,rn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successfully Written");
		EmpDetails l1=new EmpDetails();
		l1.setFname(fn);
		l1.setLname(ln);
		l1.setDob(dob);
		l1.setAdd1(add1);
		l1.setAdd2(add2);
		l1.setCity(city);
		l1.setPin(pin);
		l1.setEmail(email);
		l1.setMobile(mob);
		l1.setEmpcode(rn);
		return l1;
	}
	
	@GET
	@Path("Log")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Utility> getUtility()
	{
		System.out.println("Inside Get Utility");
		return repo.getUtility();
	}
	
	@GET
	@Path("Log/{studid}/{present}")
	@Produces(MediaType.APPLICATION_JSON)
	public void TakeAttendance(@PathParam("studid") String studid,@PathParam("present") String present)	    
	{
		System.out.println("I am inside takingattendance");
		repo.UpdateAtt(studid,present);
		System.out.println("Successfully Written");
	}
	
	@GET
	@Path("DeleteUtility/{pname}")
	@Produces(MediaType.APPLICATION_JSON)
	public void DelUtil(@PathParam("pname") String pname)	    
	{
		System.out.println("I am inside Utility Delete");
		repo.DeleteUtility(pname);
		System.out.println("Successfully Deleted");
	}
	
	@GET
	@Path("VotedOnce/{un}")
	@Produces(MediaType.APPLICATION_JSON)
	public void UpdateVoted(@PathParam("un") String un)	    
	{
		System.out.println("I am inside takingattendance");
		repo.UpdateVotedOnce(un);
		System.out.println("Successfully Written");
	}
	
	@GET
	@Path("Log/{username}")
	@Produces(MediaType.APPLICATION_XML)
	public Login getStudent(@PathParam("username") String un)
	{
		System.out.println("I am inside GetStudent");
		return repo.getLogin(un);
	}
	@GET
	@Path("Log/Jobs")
	@Produces(MediaType.APPLICATION_JSON)
	public List<VacantPositions> getJobs()
	{
		System.out.println("I am inside GetJobs");
		return repo.getJobs();
	}
	
	@GET
	@Path("GetEmail/{un}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<AppReg> GetAppEmail(@PathParam("un") String un)
	{
		return repo.GetEmail(un);
	}
	
	
	
	@GET
	@Path("UpdatePass/{un}/{npw}")
	@Produces(MediaType.APPLICATION_JSON)
	public void UpdatePassWd(@PathParam("un") String un,@PathParam("npw") String npw)	    
	{
		System.out.println("I am inside takingattendance");
		repo.UpdatePassWord(un,npw);
		System.out.println("Successfully Written");
	}
	
	@GET
	@Path("GetEmpNames")
	@Produces(MediaType.APPLICATION_JSON)
	public List<EmpDetails> GetEmpNames()	    
	{
		
		return repo.MyGetEmpNames();
		
	}
}
