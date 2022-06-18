package services;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.UserDTO;
import model.Administrator;
import model.Customer;
import model.Manager;
import model.Trainer;
import repository.AdministratorRepository;
import repository.CustomerRepository;
import repository.ManagerRepository;
import repository.TrainerRepository;

@Path("users")
public class UserService {
	
	AdministratorRepository administratorRepo = new AdministratorRepository();
	CustomerRepository customerRepo = new CustomerRepository();
	ManagerRepository managerRepo = new ManagerRepository();
	TrainerRepository trainerRepo = new TrainerRepository();
	
	@Context
	ServletContext ctx;
	
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("users") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("users", new UserService());
		}
	}
	
	@POST
	@Path("login")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String logIn(UserDTO user) {
		administratorRepo.setBasePath("C:\\Users\\marko\\eclipse-workspace\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		customerRepo.setBasePath("C:\\Users\\marko\\eclipse-workspace\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		managerRepo.setBasePath("C:\\Users\\marko\\eclipse-workspace\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		trainerRepo.setBasePath("C:\\Users\\marko\\eclipse-workspace\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		
		ArrayList<Administrator> administrators = administratorRepo.getAll();
		for (Administrator a : administrators) {
			if (a.getUsername().equals(user.getUsername()) && a.getPassword().equals(user.getPassword())) {
				return "administrator";
			}
		}
		
		ArrayList<Customer> customers = customerRepo.getAll();
		for (Customer c : customers) {
			if (c.getUsername().equals(user.getUsername()) && c.getPassword().equals(user.getPassword())) {
				return "customer";
			}
		}
		
		ArrayList<Manager> managers = managerRepo.getAll();
		for (Manager m : managers) {
			if (m.getUsername().equals(user.getUsername()) && m.getPassword().equals(user.getPassword())) {
				return "manager";
			}
		}
		
		ArrayList<Trainer> trainers = trainerRepo.getAll();
		for (Trainer t : trainers) {
			if (t.getUsername().equals(user.getUsername()) && t.getPassword().equals(user.getPassword())) {
				return "trainer";
			}
		}
		
		return "none";
	}

}
