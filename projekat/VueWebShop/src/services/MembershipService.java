package services;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.BuyMembership;
import dto.ManagerDTO;
import dto.MembershipDTO;
import model.Customer;
import model.IdGenerator;
import model.Manager;
import model.Membership;
import model.MembershipStatus;
import repository.CustomerRepository;
import repository.MembershipRepository;

@Path("memberships")
public class MembershipService {

MembershipRepository repo = new MembershipRepository();
	
	MembershipRepository membershipRepo = new MembershipRepository();
	CustomerRepository customerRepo = new CustomerRepository();
	
	@Context
	ServletContext ctx;
	
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("comments") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("comments", new CommentService());
		}
	}
	
	@GET
	@Path("updateMembership")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateCustomerMembership() {
		
		membershipRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		
		Membership membership1 = membershipRepo.read("NS99H0GJ05");
		Membership membership2 = membershipRepo.read("M24GO2GSPJ");
		Membership membership3 = membershipRepo.read("0N12HUKAO0");
			
		membership1.setNotChangeValue(24000.00);
		membership2.setNotChangeValue(2000.00);
		membership3.setNotChangeValue(500.00);
		
		membershipRepo.update(membership1);
		membershipRepo.update(membership2);
		membershipRepo.update(membership3);
		
	}
	
	@GET
	@Path("getAll")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<MembershipDTO> getAll() {
		repo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<Membership> memberships = repo.getAll();
		ArrayList<MembershipDTO> retVal = new ArrayList<MembershipDTO>();
		for (Membership m : memberships) {			
			retVal.add(new MembershipDTO(m.getId(), m.getTypeMembership(), m.getDayPaying(), m.getValue(), m.getStartValidation(), m.getEndValidation(), m.getMembershipStatus(), m.getNumberOfTerms()));
		}
		return retVal;
	}
	
	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Membership createMembership(MembershipDTO membershipDTO) {
		
		repo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		
		Membership membership = new Membership(IdGenerator.getInstance().generateId(repo.getKeySet(), 10),membershipDTO.getTypeMembership(), membershipDTO.getStartValidation(), membershipDTO.getValue(), membershipDTO.getEndValidation(),membershipDTO.getDayPaying(), membershipDTO.getMembershipStatus(), membershipDTO.getNumberOfTerms());

		repo.create(membership);
		
		return membership;
	}
	

	/*@GET
	@Path("createAuto")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void createMembershipAuto()
	{
		repo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		
		String id = IdGenerator.getInstance().generateId(repo.getKeySet(),10);
		String type = "Weekly";
		double value = 700.21;
		LocalDate startValidation = null; 
		LocalDate endValidation = null;
		LocalDate dayPaying = null;
		MembershipStatus status = MembershipStatus.NoActive;
		int numberOfTerms = 3;
		Membership newMembership = new Membership(id, type, dayPaying, value, startValidation, endValidation, status, numberOfTerms);
		//Map<String, Membership> mapa = new HashMap<String, Membership>();
		//mapa.put(newMembership.getId(), newMembership);
		repo.create(newMembership);
		System.out.println("Created new membership: ");
	}
	*/
}