package services;



import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import comparators.CustomerLastNameComparator;
import comparators.CustomerNameComparator;
import comparators.CustomerUserNameComparator;
import comparators.SportObjectGradeComparator;
import comparators.SportObjectLocationComparator;
import comparators.SportObjectNameComparator;
import dto.CustomerDTO;

import dto.SearchDTO;
import dto.SportObjectDTO;
import model.Administrator;
import model.Customer;
import model.Manager;
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
	
	@POST
	@Path("search")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<CustomerDTO> search(SearchDTO search) {
		if (search.getSearchText().isBlank()) {
			return getAll();
		}
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<CustomerDTO> retVal = new ArrayList<CustomerDTO>();
		ArrayList<Customer> customers = customerRepo.getAll();
		
		for (Customer s : customers) {
			if (s.getName().toLowerCase().trim().contains(search.getSearchText().toLowerCase().trim())) {	
					retVal.add(new CustomerDTO(s.getUsername(),s.getName(),s.getLastName(),s.getGender(),s.getDateOfBirth()));	
			}
		}
	    
		for (Customer s : customers) {
			if (s.getLastName().toLowerCase().trim().contains(search.getSearchText().toLowerCase().trim())) {	
					int cnt = 0;
					for (CustomerDTO so : retVal) {
						if (so.getUsername().equals(s.getUsername()))
							cnt++;
					}
					if (cnt == 0)
						retVal.add(new CustomerDTO(s.getUsername(),s.getName(),s.getLastName(),s.getGender(),s.getDateOfBirth()));	
			}
		}

		for (Customer s : customers) {
			if (s.getUsername().toLowerCase().trim().contains(search.getSearchText().toLowerCase().trim())) {	
					int cnt = 0;
					for (CustomerDTO so : retVal) {
						if (so.getUsername().equals(s.getUsername()))
							cnt++;
					}
					if (cnt == 0)
						retVal.add(new CustomerDTO(s.getUsername(),s.getName(),s.getLastName(),s.getGender(),s.getDateOfBirth()));	
			}
		}	
		return retVal;
	}
	
	public ArrayList<CustomerDTO> nameACS(ArrayList<CustomerDTO> retVal) {
		Collections.sort(retVal, new CustomerNameComparator());
		return retVal;
	}
	
	public ArrayList<CustomerDTO> nameDESC(ArrayList<CustomerDTO> retVal) {
		Collections.sort(retVal, new CustomerNameComparator());
		Collections.reverse(retVal);
		return retVal;
	}
	
	public ArrayList<CustomerDTO> lastNameACS(ArrayList<CustomerDTO> retVal) {
		Collections.sort(retVal, new CustomerLastNameComparator());
		return retVal;
	}
	
	public ArrayList<CustomerDTO> lastNameDESC(ArrayList<CustomerDTO> retVal) {
		Collections.sort(retVal, new CustomerLastNameComparator());
		Collections.reverse(retVal);
		return retVal;
	}
	
	public ArrayList<CustomerDTO> userNameACS(ArrayList<CustomerDTO> retVal) {
		Collections.sort(retVal, new CustomerUserNameComparator());
		return retVal;
	}
	
	public ArrayList<CustomerDTO> userNameDESC(ArrayList<CustomerDTO> retVal) {
		Collections.sort(retVal, new CustomerUserNameComparator());
		Collections.reverse(retVal);
		return retVal;
	}	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@GET
	@Path("getAll1")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<CustomerDTO> getAll1() {
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<Customer> customers = customerRepo.getAll();
		ArrayList<CustomerDTO> retVal = new ArrayList<CustomerDTO>();
		for (Customer s : customers) {			
			retVal.add(new CustomerDTO(s.getUsername(),s.getName(), s.getLastName(),s.getGender(),s.getDateOfBirth()));	
		}
		retVal = nameACS(retVal);
		return retVal;
	}
	@GET
	@Path("getAll2")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<CustomerDTO> getAll2() {
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<Customer> customers = customerRepo.getAll();
		ArrayList<CustomerDTO> retVal = new ArrayList<CustomerDTO>();
		for (Customer s : customers) {			
			retVal.add(new CustomerDTO(s.getUsername(),s.getName(), s.getLastName(),s.getGender(),s.getDateOfBirth()));	
		}
		retVal = nameDESC(retVal);
		return retVal;
	}
	@GET
	@Path("getAll3")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<CustomerDTO> getAll3() {
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<Customer> customers = customerRepo.getAll();
		ArrayList<CustomerDTO> retVal = new ArrayList<CustomerDTO>();
		for (Customer s : customers) {			
			retVal.add(new CustomerDTO(s.getUsername(),s.getName(), s.getLastName(),s.getGender(),s.getDateOfBirth()));	
		}
		retVal = lastNameACS(retVal);
		return retVal;
	}
	@GET
	@Path("getAll4")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<CustomerDTO> getAll4() {
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<Customer> customers = customerRepo.getAll();
		ArrayList<CustomerDTO> retVal = new ArrayList<CustomerDTO>();
		for (Customer s : customers) {			
			retVal.add(new CustomerDTO(s.getUsername(),s.getName(), s.getLastName(),s.getGender(),s.getDateOfBirth()));	
		}
		retVal = lastNameDESC(retVal);
		return retVal;
	}
	@GET
	@Path("getAll5")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<CustomerDTO> getAll5() {
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<Customer> customers = customerRepo.getAll();
		ArrayList<CustomerDTO> retVal = new ArrayList<CustomerDTO>();
		for (Customer s : customers) {			
			retVal.add(new CustomerDTO(s.getUsername(),s.getName(), s.getLastName(),s.getGender(),s.getDateOfBirth()));	
		}
		retVal = userNameACS(retVal);
		return retVal;
	}
	@GET
	@Path("getAll6")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<CustomerDTO> getAll6() {
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<Customer> customers = customerRepo.getAll();
		ArrayList<CustomerDTO> retVal = new ArrayList<CustomerDTO>();
		for (Customer s : customers) {			
			retVal.add(new CustomerDTO(s.getUsername(),s.getName(), s.getLastName(),s.getGender(),s.getDateOfBirth()));	
		}
		retVal = userNameDESC(retVal);
		return retVal;
	}
}
//	@GET
//	@Path("createAuto")	
//	@Produces(MediaType.TEXT_PLAIN)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public void createCustomerAuto()
//	{
//		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
//		sportObjectRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
//		ArrayList<SportObject> visitedObjects = new ArrayList<SportObject>();		
//		SportObject so = sportObjectRepo.read("4");
//		visitedObjects.add(so);
//		Customer newCustomer = customerRepo.read("2022-06-17T23:24:10.874440");
//		newCustomer.setVisitedObjects(visitedObjects);
//		//Map<String, Administrator> mapa = new HashMap<String, Administrator>();
//		//mapa.put(newCustomer.getId(), newCustomer);
//		//repo.writeFile(mapa);
//		customerRepo.update(newCustomer);
//		
//		//System.out.println("Created new customer: ");
//	}

//		System.out.println("Created new customer: ");
//	}
