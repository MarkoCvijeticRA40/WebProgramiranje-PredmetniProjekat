package services;



import java.time.LocalDate;
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

import comparators.CustomerComparePoints;
import comparators.CustomerLastNameComparator;
import comparators.CustomerNameComparator;
import comparators.CustomerUserNameComparator;
import dto.BuyMembership;
import dto.CustomerDTO;
import dto.SearchDTO;
import dto.UpdateUserDTO;
import dto.UsernameDTO;
import dto.UpdatePasswordDTO;
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

@Path("customers")
public class CustomerService {
	
	CustomerRepository customerRepo = new CustomerRepository();
	AdministratorRepository administratorRepo = new AdministratorRepository();
	ManagerRepository managerRepo = new ManagerRepository();
	TrainerRepository trainerRepo = new TrainerRepository();
	SportObjectRepository sportObjectRepo = new SportObjectRepository();
	MembershipRepository membershipRepo = new MembershipRepository();
	
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
	@Path("updateMembership")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateCustomerMembership(BuyMembership dto) {
		
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		membershipRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		
		Membership membership = membershipRepo.read(dto.getMembershipId());
		Customer customer = customerRepo.read(dto.getCustomerId());
		
		membership.setDayPaying(LocalDate.now());
		
		if(membership.getTypeMembership().equals("Yearly")) {
			membership.setStartValidation(LocalDate.now());
			membership.setEndValidation(LocalDate.now().plusYears(1));	
		}
		else if(membership.getTypeMembership().equals("Monthly")) {
			membership.setStartValidation(LocalDate.now());
			membership.setEndValidation(LocalDate.now().plusMonths(1));
		}
		else {
			membership.setStartValidation(LocalDate.now());
			membership.setEndValidation(LocalDate.now().plusWeeks(1));
		}
		membership.setStatus();
		
		customer.setMembership(membership);
		
		membership.setSendPoints("False");
		
		customerRepo.update(customer);
	}
	
	@POST
	@Path("updateCustomerPoints")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateCustomerPoints(CustomerDTO dto) {
		
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		
		Customer customer = customerRepo.read(dto.getId());
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
	@Path("getAll")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<CustomerDTO> getAll() {
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<Customer> customers = customerRepo.getAll();
		ArrayList<CustomerDTO> retVal = new ArrayList<CustomerDTO>();
		for (Customer c : customers) {			
			retVal.add(new CustomerDTO(c.getUsername(),c.getName(), c.getLastName(),c.getGender(),c.getDateOfBirth(),c.getPoints(),c.getCustomerType()));	
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
	@Path("updateCustomer")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Customer update(UpdateUserDTO customerDTO) {
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		Customer customer = customerRepo.read(customerDTO.getId());
		customer.setName(customerDTO.getName());
		customer.setLastName(customerDTO.getLastName());
		customerRepo.update(customer);
		return customer;
	}
	
	
	@POST
	@Path("updatePassword")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Customer updatePassword(UpdatePasswordDTO customerDTO) {
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		Customer customer = customerRepo.read(customerDTO.getId());
		customer.setPassword(customerDTO.getPassword());
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
					retVal.add(new CustomerDTO(s.getUsername(),s.getName(),s.getLastName(),s.getGender(),s.getDateOfBirth(),s.getPoints(),s.getCustomerType()));	
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
						retVal.add(new CustomerDTO(s.getUsername(),s.getName(),s.getLastName(),s.getGender(),s.getDateOfBirth(),s.getPoints(),s.getCustomerType()));	
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
						retVal.add(new CustomerDTO(s.getUsername(),s.getName(),s.getLastName(),s.getGender(),s.getDateOfBirth(),s.getPoints(),s.getCustomerType()));	
			}
		}
		for (Customer s : customers) {
			if (s.getCustomerType().getName().toLowerCase().trim().contains(search.getSearchText().toLowerCase().trim())) {	
					int cnt = 0;
					for (CustomerDTO so : retVal) {
						if (so.getUsername().equals(s.getUsername()))
							cnt++;
					}
					if (cnt == 0)
						retVal.add(new CustomerDTO(s.getUsername(),s.getName(),s.getLastName(),s.getGender(),s.getDateOfBirth(),s.getPoints(),s.getCustomerType()));	
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
			retVal.add(new CustomerDTO(s.getUsername(),s.getName(), s.getLastName(),s.getGender(),s.getDateOfBirth(),s.getPoints(),s.getCustomerType()));	
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
			retVal.add(new CustomerDTO(s.getUsername(),s.getName(), s.getLastName(),s.getGender(),s.getDateOfBirth(),s.getPoints(),s.getCustomerType()));	
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
			retVal.add(new CustomerDTO(s.getUsername(),s.getName(), s.getLastName(),s.getGender(),s.getDateOfBirth(),s.getPoints(),s.getCustomerType()));	
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
			retVal.add(new CustomerDTO(s.getUsername(),s.getName(), s.getLastName(),s.getGender(),s.getDateOfBirth(),s.getPoints(),s.getCustomerType()));	
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
			retVal.add(new CustomerDTO(s.getUsername(),s.getName(), s.getLastName(),s.getGender(),s.getDateOfBirth(),s.getPoints(),s.getCustomerType()));	
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
			retVal.add(new CustomerDTO(s.getUsername(),s.getName(), s.getLastName(),s.getGender(),s.getDateOfBirth(),s.getPoints(),s.getCustomerType()));	
		}
		retVal = userNameDESC(retVal);
		return retVal;
	}
	

	
	@POST
	@Path("delete")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteCustomer(UsernameDTO customerUsername) {
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		Customer customer = customerRepo.getCustomerByUsername(customerUsername.getUsername());
		customerRepo.delete(customer.getId());
	}

	public ArrayList<CustomerDTO> gradeACS(ArrayList<CustomerDTO> retVal) {
		Collections.sort(retVal, new CustomerComparePoints());
		return retVal;
	}
	
	public ArrayList<CustomerDTO> gradeDESC(ArrayList<CustomerDTO> retVal) {
		Collections.sort(retVal,new CustomerComparePoints());
		Collections.reverse(retVal);
		return retVal;
	}
	
	@GET
	@Path("getAll7")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<CustomerDTO> getAll7() {
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<Customer> customers = customerRepo.getAll();
		ArrayList<CustomerDTO> retVal = new ArrayList<CustomerDTO>();
		for (Customer s : customers) {			
			retVal.add(new CustomerDTO(s.getUsername(),s.getName(), s.getLastName(),s.getGender(),s.getDateOfBirth(),s.getPoints(),s.getCustomerType()));	
		}
		retVal = gradeACS(retVal);
		return retVal;
	}
	
	@GET
	@Path("getAll8")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<CustomerDTO> getAll8() {
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<Customer> customers = customerRepo.getAll();
		ArrayList<CustomerDTO> retVal = new ArrayList<CustomerDTO>();
		for (Customer s : customers) {			
			retVal.add(new CustomerDTO(s.getUsername(),s.getName(), s.getLastName(),s.getGender(),s.getDateOfBirth(),s.getPoints(),s.getCustomerType()));	
		}
		retVal = gradeDESC(retVal);
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
