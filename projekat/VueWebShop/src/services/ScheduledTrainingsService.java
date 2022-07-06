package services;



import java.time.LocalDateTime;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.IdDTO;
import dto.ScheduleTrainingDTO;
import model.Customer;
import model.HistoryOfAllTrainings;
import model.IdGenerator;
import model.Trainer;
import model.Training;
import repository.CustomerRepository;
import repository.HistoryOfAllTrainingsRepository;
import repository.ScheduledTrainingsRepository;
import repository.TrainingRepository;

import javax.servlet.ServletContext;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import repository.ScheduledTrainingsRepository;


@Path("scheduledTrainings")
public class ScheduledTrainingsService {

	ScheduledTrainingsRepository repo = new ScheduledTrainingsRepository();
	TrainingRepository trainingRepo = new TrainingRepository();
	CustomerRepository customerRepo = new CustomerRepository();
	HistoryOfAllTrainingsRepository historyRepo = new HistoryOfAllTrainingsRepository();
	ScheduledTrainingsRepository scheduleRepo = new ScheduledTrainingsRepository(); 

	
	@Context
	ServletContext ctx;
	
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("scheduledTrainings") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("scheduledTrainings", new ScheduledTrainingsService());
		}
	}

	
	@POST
	@Path("schedule")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void scheduleTraining(ScheduleTrainingDTO trainingDTO) {
		repo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		trainingRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		customerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		historyRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		scheduleRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		
		int year = Integer.parseInt(trainingDTO.getDate().split("-")[0]);
		int month = Integer.parseInt(trainingDTO.getDate().split("-")[1]);
		int day = Integer.parseInt(trainingDTO.getDate().split("-")[2]);
		int hours = Integer.parseInt(trainingDTO.getTime().split(":")[0]);
		
		LocalDateTime date = LocalDateTime.of(year, month, day, hours, 0);
		
		String trainingName = trainingDTO.getTrainingName().split("-")[0];
		String trainingType = trainingDTO.getTrainingName().split("-")[1];
		String trainingSportObject = trainingDTO.getTrainingName().split("-")[2];
		
		Training training = trainingRepo.getTrainingByNameTypeAndSportObject(trainingName, trainingType, trainingSportObject);
		Customer customer = customerRepo.read(trainingDTO.getCustomerId());
		Trainer trainer = training.getTrainer();
		
		HistoryOfAllTrainings appointment = new HistoryOfAllTrainings(IdGenerator.getInstance().generateId(historyRepo.getKeySet(), 10), date, training, customer, trainer);
		scheduleRepo.create(appointment);

	}
	
	@POST
	@Path("cancelTraining")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void cancelTraining(IdDTO trainingId) {
		repo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		repo.delete(trainingId.getId());

	}
	
	
}
