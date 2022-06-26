package services;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.ManagerDTO;
import dto.TrainerDTO;
import model.Administrator;
import model.Customer;
import model.Manager;
import model.Trainer;
import repository.AdministratorRepository;
import repository.CustomerRepository;
import repository.ManagerRepository;
import repository.TrainerRepository;

@Path("trainers")
public class TrainerService {

	TrainerRepository trainerRepo = new TrainerRepository();
	CustomerRepository customerRepo = new CustomerRepository();
	AdministratorRepository administratorRepo = new AdministratorRepository();
	ManagerRepository managerRepo = new ManagerRepository();
	
	
	@Context
	ServletContext ctx;
	
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("trainers") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("trainers", new AdministratorService());
		}
	}
	
	@GET
	@Path("getAll")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<TrainerDTO> getAll() {
		trainerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<Trainer> trainers = trainerRepo.getAll();
		ArrayList<TrainerDTO> retVal = new ArrayList<TrainerDTO>();
		for (Trainer s : trainers) {			
			retVal.add(new TrainerDTO(s.getUsername(),s.getName(), s.getLastName(), s.getGender(),s.getDateOfBirth()));	
		}
		return retVal;
	}
	
	@POST
	@Path("createTrainer")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Trainer createTrainer(Trainer trainer)
	{
		if (isUsernameUnique(trainer)) {
			
			trainerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
			Trainer newTrainer = new Trainer(trainer.getUsername(), trainer.getPassword(), trainer.getName(), trainer.getLastName(), trainer.getGender(), trainer.getDateOfBirth());
			trainerRepo.create(newTrainer);
			
			return trainer;
		}
		else {
			return null;
		}
		
	}
	
	@POST
	@Path("update")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Trainer updateTrainer(Trainer trainer) {
		trainerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		trainerRepo.update(trainer);
		return trainer;
	}
	
	private boolean isUsernameUnique(Trainer trainer) {
		administratorRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		managerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		trainerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		
		boolean isUnique = true;
		
		ArrayList<Customer> customers = customerRepo.getAll();
		for (Customer c : customers) {
			if (c.getUsername().equals(trainer.getUsername())) {
				isUnique = false;
			}
		}
		
		ArrayList<Administrator> administrators = administratorRepo.getAll();
		for (Administrator a : administrators) {
			if (a.getUsername().equals(trainer.getUsername())) {
				isUnique = false;
			}
		}
		
		ArrayList<Manager> managers = managerRepo.getAll();
		for (Manager m : managers) {
			if (m.getUsername().equals(trainer.getUsername())) {
				isUnique = false;
			}
		}
		
		ArrayList<Trainer> trainers = trainerRepo.getAll();
		for (Trainer t : trainers) {
			if (t.getUsername().equals(trainer.getUsername())) {
				isUnique = false;
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
//		Trainer newCustomer = new Trainer("zvezda", "neki466", "Manojlo", "Markovic", Gender.Male, new Date(1978,5,20));
//		Map<String, Trainer> mapa = new HashMap<String, Trainer>();
//		mapa.put(newCustomer.getId(), newCustomer);
//		repo.writeFile(mapa);
//		
//		System.out.println("Created new customer: ");
//	}
