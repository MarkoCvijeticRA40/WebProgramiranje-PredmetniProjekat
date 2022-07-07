package services;

import java.time.LocalDateTime;
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


import dto.IdDTO;
import dto.PersonalTrainingDTO;
import comparators.TrainerLastNameComparator;
import comparators.TrainerNameComparator;
import comparators.TrainerUserNameComparator;
import dto.SearchDTO;
import dto.TrainerDTO;
import dto.UsernameDTO;
import model.Administrator;
import model.Customer;
import model.HistoryOfAllTrainings;
import model.Manager;
import model.Trainer;
import model.Training;
import repository.AdministratorRepository;
import repository.CustomerRepository;
import repository.ManagerRepository;
import repository.ScheduledTrainingsRepository;
import repository.TrainerRepository;
import repository.TrainingRepository;

@Path("trainers")
public class TrainerService {

	TrainerRepository trainerRepo = new TrainerRepository();
	CustomerRepository customerRepo = new CustomerRepository();
	AdministratorRepository administratorRepo = new AdministratorRepository();
	ManagerRepository managerRepo = new ManagerRepository();
	TrainingRepository trainingRepo = new TrainingRepository();
	ScheduledTrainingsRepository scheduleRepo = new ScheduledTrainingsRepository();
	
	
	@Context
	ServletContext ctx;
	
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("trainers") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("trainers", new TrainerService());
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
	
	@POST
	@Path("getAllTrainersFromSportObject")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<TrainerDTO> getAllTrainersFromSportObject(IdDTO sportObjectId) {
		trainingRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<Training> trainings = trainingRepo.getAll();
		ArrayList<TrainerDTO> retVal = new ArrayList<TrainerDTO>();
		for (Training t : trainings) {
			if (t.getSportObject().getId().equals(sportObjectId.getId())) {
				int cnt = 0;
				for (TrainerDTO d : retVal) {
					if (d.getUsername().equals(t.getTrainer().getUsername()))
						cnt++;
				}
				if (cnt == 0) {
					retVal.add(new TrainerDTO(t.getTrainer().getUsername(),t.getTrainer().getName(), t.getTrainer().getLastName(), t.getTrainer().getGender(),t.getTrainer().getDateOfBirth()));
				}
			}
		}
		return retVal;
	}
	
	@POST
	@Path("getTrainerByUsername")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Trainer getTrainerByUsername(UsernameDTO username) {
		trainerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<Trainer> trainers = trainerRepo.getAll();
		for (Trainer t : trainers) {
			if (t.getUsername().equals(username.getUsername()))
				return t;
		}
		
		return null;
	}
	
	@POST
	@Path("search")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<TrainerDTO> search(SearchDTO search) {
		if (search.getSearchText().isBlank()) {
			return getAll();
		}
		trainerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<TrainerDTO> retVal = new ArrayList<TrainerDTO>();
		ArrayList<Trainer> trainers = trainerRepo.getAll();
		
		for (Trainer s : trainers) {
			if (s.getName().toLowerCase().trim().contains(search.getSearchText().toLowerCase().trim())) {	
					retVal.add(new TrainerDTO(s.getUsername(),s.getName(),s.getLastName(),s.getGender(),s.getDateOfBirth()));	
			}
		}
	    
		for (Trainer s : trainers) {
			if (s.getLastName().toLowerCase().trim().contains(search.getSearchText().toLowerCase().trim())) {	
					int cnt = 0;
					for (TrainerDTO so : retVal) {
						if (so.getUsername().equals(s.getUsername()))
							cnt++;
					}
					if (cnt == 0)
						retVal.add(new TrainerDTO(s.getUsername(),s.getName(),s.getLastName(),s.getGender(),s.getDateOfBirth()));	
			}
		}

		for (Trainer s : trainers) {
			if (s.getUsername().toLowerCase().trim().contains(search.getSearchText().toLowerCase().trim())) {	
					int cnt = 0;
					for (TrainerDTO so : retVal) {
						if (so.getUsername().equals(s.getUsername()))
							cnt++;
					}
					if (cnt == 0)
						retVal.add(new TrainerDTO(s.getUsername(),s.getName(),s.getLastName(),s.getGender(),s.getDateOfBirth()));	
			}
		}	
		return retVal;
	}

	public ArrayList<TrainerDTO> nameACS(ArrayList<TrainerDTO> retVal) {
		Collections.sort(retVal, new TrainerNameComparator());
		return retVal;
	}
	
	public ArrayList<TrainerDTO> nameDESC(ArrayList<TrainerDTO> retVal) {
		Collections.sort(retVal, new TrainerNameComparator());
		Collections.reverse(retVal);
		return retVal;
	}
	
	public ArrayList<TrainerDTO> userNameACS(ArrayList<TrainerDTO> retVal) {
		Collections.sort(retVal, new TrainerUserNameComparator());
		return retVal;
	}
	
	public ArrayList<TrainerDTO> userNameDESC(ArrayList<TrainerDTO> retVal) {
		Collections.sort(retVal, new TrainerUserNameComparator());
		Collections.reverse(retVal);
		return retVal;
	}
	
	public ArrayList<TrainerDTO> lastNameACS(ArrayList<TrainerDTO> retVal) {
		Collections.sort(retVal, new TrainerLastNameComparator());
		return retVal;
	}
	
	public ArrayList<TrainerDTO> lastNameDESC(ArrayList<TrainerDTO> retVal) {
		Collections.sort(retVal, new TrainerLastNameComparator());
		Collections.reverse(retVal);
		return retVal;
	}	

	@GET
	@Path("getAllNameDESC")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<TrainerDTO> getAllNameDESC() {
		trainerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<Trainer> trainers = trainerRepo.getAll();
		ArrayList<TrainerDTO> retVal = new ArrayList<TrainerDTO>();
		for (Trainer s : trainers) {			
			retVal.add(new TrainerDTO(s.getUsername(),s.getName(), s.getLastName(), s.getGender(),s.getDateOfBirth()));	
		}
		retVal = nameDESC(retVal);
		return retVal;
	}
	
	@GET
	@Path("getAllNameACS")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<TrainerDTO> getAllNameACS() {
		trainerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<Trainer> trainers = trainerRepo.getAll();
		ArrayList<TrainerDTO> retVal = new ArrayList<TrainerDTO>();
		for (Trainer s : trainers) {			
			retVal.add(new TrainerDTO(s.getUsername(),s.getName(), s.getLastName(), s.getGender(),s.getDateOfBirth()));	
		}
		retVal = nameACS(retVal);
		return retVal;
	}
	
	@GET
	@Path("getAllUserNameDESC")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<TrainerDTO> getAllUserNameDESC() {
		trainerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<Trainer> trainers = trainerRepo.getAll();
		ArrayList<TrainerDTO> retVal = new ArrayList<TrainerDTO>();
		for (Trainer s : trainers) {			
			retVal.add(new TrainerDTO(s.getUsername(),s.getName(), s.getLastName(), s.getGender(),s.getDateOfBirth()));	
		}
		retVal = userNameDESC(retVal);
		return retVal;
	}
	
	@GET
	@Path("getAllUserNameACS")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<TrainerDTO> getAllUserNameACS() {
		trainerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<Trainer> trainers = trainerRepo.getAll();
		ArrayList<TrainerDTO> retVal = new ArrayList<TrainerDTO>();
		for (Trainer s : trainers) {			
			retVal.add(new TrainerDTO(s.getUsername(),s.getName(), s.getLastName(), s.getGender(),s.getDateOfBirth()));	
		}
		retVal = userNameACS(retVal);
		return retVal;
	}
	
	@GET
	@Path("getAllLastNameDESC")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<TrainerDTO> getAllLastNameDECS() {
		trainerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<Trainer> trainers = trainerRepo.getAll();
		ArrayList<TrainerDTO> retVal = new ArrayList<TrainerDTO>();
		for (Trainer s : trainers) {			
			retVal.add(new TrainerDTO(s.getUsername(),s.getName(), s.getLastName(), s.getGender(),s.getDateOfBirth()));	
		}
		retVal = lastNameDESC(retVal);
		return retVal;
	}
	
	@GET
	@Path("getAllLastNameACS")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<TrainerDTO> getAllLastNameACS() {
		trainerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<Trainer> trainers = trainerRepo.getAll();
		ArrayList<TrainerDTO> retVal = new ArrayList<TrainerDTO>();
		for (Trainer s : trainers) {			
			retVal.add(new TrainerDTO(s.getUsername(),s.getName(), s.getLastName(), s.getGender(),s.getDateOfBirth()));	
		}
		retVal = lastNameACS(retVal);
		return retVal;
	}
	
	
	@POST
	@Path("getTrainings")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<Training> getTrainings(IdDTO trainerId) {
		trainingRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		ArrayList<Training> retVal = new ArrayList<Training>();
		
		for (Training t : trainingRepo.getAll()) {
			if (t.getTrainer().getId().equals(trainerId.getId()))
				retVal.add(t);
		}
		
		return retVal;
	}
	
	
	@POST
	@Path("getGroupTrainings")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<HistoryOfAllTrainings> getGroupTrainings(IdDTO trainerId) {
		scheduleRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		
		ArrayList<HistoryOfAllTrainings> retVal = new ArrayList<HistoryOfAllTrainings>();
		
		for (HistoryOfAllTrainings h : scheduleRepo.getAll()) {
			if (h.getTraining().getType().equals("Grupni") && h.getTrainer().getId().equals(trainerId.getId()) && h.getApplicationDate().compareTo(LocalDateTime.now()) >= 0) {
				retVal.add(h);
			}
		}
		
		return retVal;
	}
	
	@POST
	@Path("getPersonalTrainings")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<PersonalTrainingDTO> getPersonalTrainings(IdDTO trainerId) {
		scheduleRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		
		ArrayList<PersonalTrainingDTO> retVal = new ArrayList<PersonalTrainingDTO>();
		
		for (HistoryOfAllTrainings h : scheduleRepo.getAll()) {
			if (h.getTraining().getType().equals("Personalni") && h.getTrainer().getId().equals(trainerId.getId()) && h.getApplicationDate().compareTo(LocalDateTime.now()) >= 0) {
				if (h.getApplicationDate().minusDays(2).compareTo(LocalDateTime.now()) < 0) {
					retVal.add(new PersonalTrainingDTO(h.getId(), h.getApplicationDate(), h.getTraining(), h.getCustomer(), h.getTrainer(), "false"));
				}
				else {
					retVal.add(new PersonalTrainingDTO(h.getId(), h.getApplicationDate(), h.getTraining(), h.getCustomer(), h.getTrainer(), "true"));
				}
			}
		}
		
		return retVal;
	}


//
//	@GET
//	@Path("createAuto")	
//	@Produces(MediaType.TEXT_PLAIN)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public void createAdministratorAuto()
//	{
//		trainerRepo.setBasePath("C:\\Users\\KORISNIK\\Desktop\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
//		trainingRepo.setBasePath("C:\\Users\\KORISNIK\\Desktop\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
//		customerRepo.setBasePath("C:\\Users\\KORISNIK\\Desktop\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
//		
//		
//		Trainer trainer = trainerRepo.read("2022-06-19T21:32:21.213536");
//		Training training = trainingRepo.read("1");
//		Customer customer = customerRepo.read("2022-06-17T16:43:25.833348900");
//		TrainingHistory t1 = new TrainingHistory(LocalDateTime.of(2022, 6, 2, 14, 0), training, customer);
//		TrainingHistory t2 = new TrainingHistory(LocalDateTime.of(2022, 6, 4, 18, 0), training, customer);
//		TrainingHistory t3 = new TrainingHistory(LocalDateTime.of(2022, 6, 5, 18, 0), training, customer);
//		TrainingHistory t4 = new TrainingHistory(LocalDateTime.of(2022, 7, 3, 20, 0), training, customer);
//		
//		
//		ArrayList<TrainingHistory> history = new ArrayList<TrainingHistory>();
//		history.add(t1);
//		history.add(t2);
//		history.add(t3);
//		history.add(t4);
//				
//		trainer.setTrainingHistory(history);
//		trainerRepo.update(trainer);
//	}
}
