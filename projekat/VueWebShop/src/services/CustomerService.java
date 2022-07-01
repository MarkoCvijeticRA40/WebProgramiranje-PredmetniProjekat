package services;



import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.CustomerDTO;
import model.Administrator;
import model.Customer;
import model.Gender;
import model.Manager;
import model.SportObject;
import model.Trainer;
import repository.AdministratorRepository;
import repository.CustomerRepository;
import repository.ManagerRepository;
import repository.SportObjectRepository;
import repository.TrainerRepository;

@Path("customers")
public class CustomerService {
	
	CustomerRepository customerRepo = new CustomerRepository();
	AdministratorRepository administratorRepo = new AdministratorRepository();
	ManagerRepository managerRepo = new ManagerRepository();
	TrainerRepository trainerRepo = new TrainerRepository();
	SportObjectRepository sportObjectRepo = new SportObjectRepository();
	
	@Context
	ServletContext ctx;
	
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("customers") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("customers", new CustomerService());
		}
	}
	//
	
	@GET
	@Path("getAll")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<CustomerDTO> getAll() {
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<Customer> customers = customerRepo.getAll();
		ArrayList<CustomerDTO> retVal = new ArrayList<CustomerDTO>();
		for (Customer s : customers) {			
			retVal.add(new CustomerDTO(s.getUsername(),s.getName(), s.getLastName(),s.getGender(),s.getDateOfBirth()));	
		}
		return retVal;
	}
	
	
	@POST
	@Path("create")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Customer createCustomer(Customer customer)
	{
		if (isUsernameUnique(customer)) {
			
			customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
			
			Customer newCustomer = new Customer(customer.getUsername(), customer.getPassword(), customer.getName(), customer.getLastName(), customer.getGender(), customer.getDateOfBirth());
			customerRepo.create(newCustomer);
			
			return customer;
		}
		else {
			return null;
		}
		
	}
	
	private boolean isUsernameUnique(Customer customer) {
		administratorRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		managerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		trainerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		
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
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		customerRepo.update(customer);
		return customer;
	}
	
	@GET
	@Path("createAuto")	
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public void createCustomerAuto()
	{
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		sportObjectRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<SportObject> visitedObjects = new ArrayList<SportObject>();		
		SportObject so = sportObjectRepo.read("4");
		visitedObjects.add(so);
		Customer newCustomer = customerRepo.read("2022-06-17T23:24:10.874440");
		newCustomer.setVisitedObjects(visitedObjects);
		//Map<String, Administrator> mapa = new HashMap<String, Administrator>();
		//mapa.put(newCustomer.getId(), newCustomer);
		//repo.writeFile(mapa);
		customerRepo.update(newCustomer);
		
		//System.out.println("Created new customer: ");
	}
}
