package services;



import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import model.Administrator;
import model.Customer;
import model.Manager;
import model.Trainer;
import repository.AdministratorRepository;
import repository.CustomerRepository;
import repository.ManagerRepository;
import repository.TrainerRepository;

@Path("customers")
public class CustomerService {
	
	CustomerRepository customerRepo = new CustomerRepository();
	AdministratorRepository administratorRepo = new AdministratorRepository();
	ManagerRepository managerRepo = new ManagerRepository();
	TrainerRepository trainerRepo = new TrainerRepository();
	
	@Context
	ServletContext ctx;
	
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("customers") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("customers", new CustomerService());
		}
	}
	

	
	@POST
	@Path("create")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Customer createCustomer(Customer customer)
	{
		if (isUsernameUnique(customer)) {
			
			customerRepo.setBasePath("C:\\Users\\marko\\eclipse-workspace\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
			
			Customer newCustomer = new Customer(customer.getUsername(), customer.getPassword(), customer.getName(), customer.getLastName(), customer.getGender(), customer.getDateOfBirth());
			customerRepo.create(newCustomer);
			
			return customer;
		}
		else {
			return null;
		}
		
	}
	
	private boolean isUsernameUnique(Customer customer) {
		administratorRepo.setBasePath("C:\\Users\\marko\\eclipse-workspace\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		customerRepo.setBasePath("C:\\Users\\marko\\eclipse-workspace\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		managerRepo.setBasePath("C:\\Users\\marko\\eclipse-workspace\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		trainerRepo.setBasePath("C:\\Users\\marko\\eclipse-workspace\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		
		boolean isUnique = true;
		
		ArrayList<Customer> customers = customerRepo.getAll();
		for (Customer c : customers) {
			if (c.getUsername().equals(customer.getUsername())) {
				isUnique = false;
			}
		}
		
		ArrayList<Administrator> administrators = administratorRepo.getAll();
		for (Administrator a : administrators) {
			if (a.getUsername().equals(customer.getUsername())) {
				isUnique = false;
			}
		}
		
		ArrayList<Manager> managers = managerRepo.getAll();
		for (Manager m : managers) {
			if (m.getUsername().equals(customer.getUsername())) {
				isUnique = false;
			}
		}
		
		ArrayList<Trainer> trainers = trainerRepo.getAll();
		for (Trainer t : trainers) {
			if (t.getUsername().equals(customer.getUsername())) {
				isUnique = false;
			}
		}
		
		return isUnique;
		
	}
	
	
	@POST
	@Path("update")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Customer updateCustomer(Customer customer) {
		customerRepo.setBasePath("C:\\Users\\marko\\eclipse-workspace\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		customerRepo.update(customer);
		return customer;
	}
	
//	@GET
//	@Path("createAuto")	
//	@Produces(MediaType.TEXT_PLAIN)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public void createCustomerAuto()
//	{
//		//repo.setBasePath(getDataDirPath());
//				
//		Administrator newCustomer = new Administrator("Nidzo", "nesto", "Nikola", "Nikolic", Gender.Male, new Date(1995,6,25));
//		Map<String, Administrator> mapa = new HashMap<String, Administrator>();
//		mapa.put(newCustomer.getId(), newCustomer);
//		repo.writeFile(mapa);
//		
//		System.out.println("Created new customer: ");
//	}
}
