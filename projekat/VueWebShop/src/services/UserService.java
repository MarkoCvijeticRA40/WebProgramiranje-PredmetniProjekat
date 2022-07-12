package services;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.BuyMembership;
import dto.CustomerDTO;
import dto.UserDTO;
import model.Administrator;
import model.Customer;
import model.Manager;
import model.Membership;
import model.Trainer;
import repository.AdministratorRepository;
import repository.CustomerRepository;
import repository.ManagerRepository;
import repository.MembershipRepository;
import repository.SportObjectRepository;
import repository.TrainerRepository;

@Path("users")
public class UserService {
	
	AdministratorRepository administratorRepo = new AdministratorRepository();
	CustomerRepository customerRepo = new CustomerRepository();
	ManagerRepository managerRepo = new ManagerRepository();
	TrainerRepository trainerRepo = new TrainerRepository();
	SportObjectRepository sportRepo = new SportObjectRepository();
	MembershipRepository memberRepo = new MembershipRepository();
	
	@Context
	ServletContext ctx;
	
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("id") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("id", "");
		}
	}
	
	private void setLoggedInUser(String id) {
		ctx.setAttribute("id", id);
	}
	
	@POST
	@Path("login")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String logIn(UserDTO user) {
		administratorRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		managerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		trainerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		sportRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		memberRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		
		ArrayList<Administrator> administrators = administratorRepo.getAll();
		for (Administrator a : administrators) {
			if (a.getUsername().equals(user.getUsername()) && a.getPassword().equals(user.getPassword())) {
				setLoggedInUser(a.getId());
				return "administrator";
			}
		}
		
		ArrayList<Customer> customers = customerRepo.getAll();
		for (Customer c : customers) {
			if (c.getUsername().equals(user.getUsername()) && c.getPassword().equals(user.getPassword())) {
				setLoggedInUser(c.getId());
				if (c.getMembership() != null) {
					c.customerType.setName(c);
					updateCustomerPoints(c);
					c.getMembership().setStatus();
					customerRepo.update(c);
				}
				
				ArrayList<Membership> memberships = memberRepo.getAll();
				for(Membership m : memberships) {
					if(c.customerType.name.equals("Gold")) {
						m.setValue(m.getNotChangeValue()*0.8);
						memberRepo.update(m);
					}
					if(c.customerType.name.equals("Silver")) {
						m.setValue(m.getNotChangeValue()*0.9);
						memberRepo.update(m);
					}
					if(c.customerType.name.equals("Bronze")) {
						m.setValue(m.getNotChangeValue()*1.00);
						memberRepo.update(m);
					}
				}
				return "customer";
			}		
		}
		
		ArrayList<Manager> managers = managerRepo.getAll();
		for (Manager m : managers) {
			if (m.getUsername().equals(user.getUsername()) && m.getPassword().equals(user.getPassword())) {
				setLoggedInUser(m.getId());
				return "manager";
			}
		}
		
		ArrayList<Trainer> trainers = trainerRepo.getAll();
		for (Trainer t : trainers) {
			if (t.getUsername().equals(user.getUsername()) && t.getPassword().equals(user.getPassword())) {
				setLoggedInUser(t.getId());
				return "trainer";
			}
		}
		return "none";
	}
	
	public void updateCustomerPoints(Customer customer) {
		
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		
		Membership membership = customer.getMembership();
		
		LocalDate today = LocalDate.now();
		
		int compareValue = today.compareTo(membership.getEndValidation());
		
		if(membership.getSendPoints().equals("False")) {
			if (compareValue >= 0) {
				membership.setSendPoints("True");
			    
				customer.setPoints(customer.getPoints() + (membership.getValue()/1000 * membership.getUsedTerms()));
			}
			
			if(membership.getUsedTerms() <= membership.getMaxTerms() * 1 / 3) {
				membership.setSendPoints("True");
			    customer.setPoints(customer.getPoints() - (membership.getValue()/1000 * 133 * 4 ));
			}	
		}	
		customerRepo.update(customer);
	}
	
	
	@GET
	@Path("activeCustomer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Customer getActiveCustomer() {
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		String id = (String)ctx.getAttribute("id");
		return customerRepo.read(id);
	}
	
	@GET
	@Path("activeAdministrator")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Administrator getActiveAdministrator() {
		administratorRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		String id = (String)ctx.getAttribute("id");
		return administratorRepo.read(id);
	}
	
	@GET
	@Path("activeManager")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Manager getActiveManager() {
		managerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		String id = (String)ctx.getAttribute("id");
		return managerRepo.read(id);
	}
	
	@GET
	@Path("activeTrainer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Trainer getActiveTrainer() {
		trainerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		String id = (String)ctx.getAttribute("id");
		return trainerRepo.read(id);
	}
}