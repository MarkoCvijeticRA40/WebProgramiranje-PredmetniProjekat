package services;



import java.io.File;
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

import model.Customer;
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
	
	public String getDataDirPath() {
		return (ctx.getRealPath("") + "WEB-INF" + File.separator + "classes" + File.separator + "data" + File.separator);
	}

	
	@POST
	@Path("create")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Customer createCustomer(Customer customer)
	{
		repo.setBasePath(getDataDirPath());
				
		Customer newCustomer = new Customer(customer.getUsername(), customer.getPassword(), customer.getName(), customer.getLastName());
		repo.create(newCustomer);
		
		return customer;
		
	}
	
	@GET
	@Path("createAuto")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Customer createCustomerAuto()
	{
		repo.setBasePath(getDataDirPath());
				
		Customer newCustomer = new Customer("bilosta", "123", "Nikola", "Nikolic");
		Map<String, Customer> mapa = new HashMap<String, Customer>();
		mapa.put(newCustomer.getId(), newCustomer);
		repo.writeFile(mapa);
		
		return newCustomer;
	}
}
