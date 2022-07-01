package services;

import java.time.LocalTime;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.CreateManagerDTO;
import dto.CustomerDTO;
import dto.ManagerDTO;
import dto.UpdateManagerDTO;
import dto.UsernameDTO;
import model.Address;
import model.Administrator;
import model.Customer;
import model.IdGenerator;
import model.Location;
import model.Manager;
import model.SportObject;
import model.Trainer;
import model.WorkTime;
import repository.AdministratorRepository;
import repository.CustomerRepository;
import repository.ManagerRepository;
import repository.TrainerRepository;

@Path("managers")
public class ManagerService {
	
	CustomerRepository customerRepo = new CustomerRepository();
	AdministratorRepository administratorRepo = new AdministratorRepository();
	ManagerRepository managerRepo = new ManagerRepository();
	TrainerRepository trainerRepo = new TrainerRepository();
	
	@Context
	ServletContext ctx;
	
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("managers") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("managers", new ManagerService());
		}
	}
	
	@GET
	@Path("getAll")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<ManagerDTO> getAll() {
		managerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<Manager> managers = managerRepo.getAll();
		ArrayList<ManagerDTO> retVal = new ArrayList<ManagerDTO>();
		for (Manager m : managers) {			
			retVal.add(new ManagerDTO(m.getUsername(), m.getName(), m.getLastName(),m.getGender(),m.getDateOfBirth()));	
		}
		return retVal;
	}
	
	@GET
	@Path("getManagersWithoutSportObject")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<ManagerDTO> getManagersWithoutSportObject() {
		managerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<Manager> managers = managerRepo.getAll();
		ArrayList<ManagerDTO> retVal = new ArrayList<ManagerDTO>();
		for (Manager m : managers) {
			if (m.getSportObject() == null) {
				retVal.add(new ManagerDTO(m.getUsername(), m.getName(), m.getLastName(),m.getGender(),m.getDateOfBirth()));
			}
		}
		return retVal;
	}
	
	@POST
	@Path("getManagerByUsername")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Manager getManagerByUsername(UsernameDTO username) {
		managerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<Manager> managers = managerRepo.getAll();
		for (Manager m : managers) {
			if (m.getUsername().equals(username.getUsername()))
				return m;
		}
		
		return null;
	}
	
	@POST
	@Path("createManager")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Manager createManager(Manager manager)
	{
		if (isUsernameUnique(manager)) {
			
			managerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
			Manager newManager = new Manager(manager.getUsername(), manager.getPassword(), manager.getName(), manager.getLastName(), manager.getGender(), manager.getDateOfBirth());
			managerRepo.create(newManager);
			
			return manager;
		}
		else {
			return null;
		}
		
	}
	
	@POST
	@Path("createManagerDTO")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Manager createManager(CreateManagerDTO managerDTO)
	{
		managerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		int startTimeHours = Integer.parseInt(managerDTO.getStartTime().split(":")[0]);
		int startTimeMinutes = Integer.parseInt(managerDTO.getStartTime().split(":")[1]);
		
		int endTimeHours = Integer.parseInt(managerDTO.getEndTime().split(":")[0]);
		int endTimeMinutes = Integer.parseInt(managerDTO.getEndTime().split(":")[1]);
		
		SportObject sportObject = new SportObject(managerDTO.getObjectId(), managerDTO.getObjectName(), managerDTO.getType(), new Location(managerDTO.getLongitude(), managerDTO.getLatitude(), new Address(managerDTO.getStreetAndNumber(), managerDTO.getCity(), managerDTO.getPostalCode())), new WorkTime(LocalTime.of(startTimeHours, startTimeMinutes), LocalTime.of(endTimeHours, endTimeMinutes)), managerDTO.getImage());
		Manager manager = new Manager(managerDTO.getUsername(), managerDTO.getPassword(), managerDTO.getName(), managerDTO.getLastName(), managerDTO.getGender(), managerDTO.getDateOfBirth(), sportObject);
		
		if (isUsernameUnique(manager)) {
			managerRepo.create(manager);
			
			return manager;
		}
		else {
			return null;
		}
		
	}
	
	@POST
	@Path("update")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Manager updateManager(Manager manager) {
		managerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		managerRepo.update(manager);
		return manager;
	}
	
	@POST
	@Path("updateDTO")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateManager(UpdateManagerDTO managerDTO) {
		managerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		int startTimeHours = Integer.parseInt(managerDTO.getStartTime().split(":")[0]);
		int startTimeMinutes = Integer.parseInt(managerDTO.getStartTime().split(":")[1]);
		
		int endTimeHours = Integer.parseInt(managerDTO.getEndTime().split(":")[0]);
		int endTimeMinutes = Integer.parseInt(managerDTO.getEndTime().split(":")[1]);
		
		SportObject sportObject = new SportObject(managerDTO.getObjectId(), managerDTO.getObjectName(), managerDTO.getType(), new Location(managerDTO.getLongitude(), managerDTO.getLatitude(), new Address(managerDTO.getStreetAndNumber(), managerDTO.getCity(), managerDTO.getPostalCode())), new WorkTime(LocalTime.of(startTimeHours, startTimeMinutes), LocalTime.of(endTimeHours, endTimeMinutes)), managerDTO.getImage());
		Manager manager = new Manager(managerDTO.getId(), managerDTO.getUsername(), managerDTO.getPassword(), managerDTO.getName(), managerDTO.getLastName(), managerDTO.getGender(), managerDTO.getDateOfBirth(), sportObject);
		
		managerRepo.update(manager);
		
	}
	
	private boolean isUsernameUnique(Manager manager) {
		administratorRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		managerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		trainerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		
		boolean isUnique = true;
		
		ArrayList<Customer> customers = customerRepo.getAll();
		for (Customer c : customers) {
			if (c.getUsername().equals(manager.getUsername())) {
				isUnique = false;
			}
		}
		
		ArrayList<Administrator> administrators = administratorRepo.getAll();
		for (Administrator a : administrators) {
			if (a.getUsername().equals(manager.getUsername())) {
				isUnique = false;
			}
		}
		
		ArrayList<Manager> managers = managerRepo.getAll();
		for (Manager m : managers) {
			if (m.getUsername().equals(manager.getUsername())) {
				isUnique = false;
			}
		}
		
		ArrayList<Trainer> trainers = trainerRepo.getAll();
		for (Trainer t : trainers) {
			if (t.getUsername().equals(manager.getUsername())) {
				isUnique = false;
			}
		}
		return isUnique;
	}
	
	
	@POST
	@Path("isUnique")	
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String isUsernameUnique(UsernameDTO username) {
		administratorRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		managerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		trainerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		
		String isUnique = "YES";
		
		ArrayList<Customer> customers = customerRepo.getAll();
		for (Customer c : customers) {
			if (c.getUsername().equals(username.getUsername())) {
				isUnique = "NO";
			}
		}
		
		ArrayList<Administrator> administrators = administratorRepo.getAll();
		for (Administrator a : administrators) {
			if (a.getUsername().equals(username.getUsername())) {
				isUnique = "NO";
			}
		}
		
		ArrayList<Manager> managers = managerRepo.getAll();
		for (Manager m : managers) {
			if (m.getUsername().equals(username.getUsername())) {
				isUnique = "NO";
			}
		}
		
		ArrayList<Trainer> trainers = trainerRepo.getAll();
		for (Trainer t : trainers) {
			if (t.getUsername().equals(username.getUsername())) {
				isUnique = "NO";
			}
		}
		return isUnique;
	}
	
	
}
//	@GET
//	@Path("createAuto")	
//	@Produces(MediaType.TEXT_PLAIN)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public void createAdministratorAuto()
//	{
//		//repo.setBasePath(getDataDirPath());
//				
//		Manager newCustomer = new Manager("Puksa", "a4ssw5", "Predrag", "Markovic", Gender.Male, new Date(1989,10,18));
//		Map<String, Manager> mapa = new HashMap<String, Manager>();
//		mapa.put(newCustomer.getId(), newCustomer);
//		repo.writeFile(mapa);
//		
//		System.out.println("Created new customer: ");
//	}