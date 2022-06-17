package services;



import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import model.Administrator;
import model.Customer;
import model.Gender;
import repository.CustomerRepository;

@Path("customers")
public class CustomerService {
	
	CustomerRepository repo = new CustomerRepository();
	
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
			
			repo.setBasePath("C:\\Users\\KORISNIK\\Desktop\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
			
			Customer newCustomer = new Customer(customer.getUsername(), customer.getPassword(), customer.getName(), customer.getLastName(), customer.getGender(), customer.getDateOfBirth());
			repo.create(newCustomer);
			
			return customer;
		}
		else {
			return null;
		}
		
	}
	
	private boolean isUsernameUnique(Customer customer) {
		repo.setBasePath("C:\\Users\\KORISNIK\\Desktop\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		boolean isUnique = true;
		ArrayList<Customer> customers = repo.getAll();
		for (Customer c : customers) {
			if (c.getUsername().equals(customer.getUsername())) {
				isUnique = false;
			}
		}
		
		return isUnique;
		
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
