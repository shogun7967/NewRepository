package MyVoteCheck.VoteCheck;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("VoteAuth")
public class VoteAuthResource {
	VoteAuthRepository repo = new VoteAuthRepository();
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<VoteAuth> getAuthentication()
	{
		return repo.getAuthentications();
	}
	
	@GET
	@Path("MobID/{un}/{mid}")
	@Produces(MediaType.APPLICATION_JSON)
	public VoteAuth CreateStudent(@PathParam("un") String un,@PathParam("mid") String mid)	    
	{
		System.out.println("I am inside Mobile ID Updation");
		//System.out.println("un "+un+"pw "+pw+"ty "+ty);
		try {
			repo.CreateMobID(un,mid,1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Successfully Written");
		VoteAuth l1=new VoteAuth();
		l1.setUname(un);
		l1.setMobID(mid);
		l1.setAuth(1);
		return l1;
	}
	
	@GET
	@Path("GetAuth")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Login> GetAuth()	    
	{
		System.out.println("I am inside Getting List for updation Updation");
		return repo.GetAllAuth();
		
	}
	
	@GET
	@Path("Approve/{uname}")
	@Produces(MediaType.APPLICATION_JSON)
	public void UpdateApprove(@PathParam("uname") String uname)	    
	{
		System.out.println("I am inside Getting List for updation Updation");
		repo.SetUpdateStat(uname);
		
	}
	
}
