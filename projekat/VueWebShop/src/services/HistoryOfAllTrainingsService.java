package services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.IdDTO;
import model.Customer;
import model.HistoryOfAllTrainings;
import model.Trainer;
import model.Training;
import repository.CustomerRepository;
import repository.HistoryOfAllTrainingsRepository;
import repository.TrainerRepository;
import repository.TrainingRepository;

@Path("history")
public class HistoryOfAllTrainingsService {

	HistoryOfAllTrainingsRepository repo = new HistoryOfAllTrainingsRepository();
	TrainerRepository trainerRepo = new TrainerRepository();
	TrainingRepository trainingRepo = new TrainingRepository();
	CustomerRepository customerRepo = new CustomerRepository();
	
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
	@Path("getTrainings")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<HistoryOfAllTrainings> getTrainings(IdDTO customerId) {
		repo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		
		ArrayList<HistoryOfAllTrainings> retVal = new ArrayList<HistoryOfAllTrainings>();
//		Date currentTime = new Date();
//		Date thirtyDaysBeforeNow = new Date(currentTime.getTime() - TimeUnit.DAYS.toMillis(30));
		for (HistoryOfAllTrainings h : repo.getAll()) {
			if (h.getCustomer().getId().equals(customerId.getId())) {
				if (LocalDateTime.now().minusDays(30).compareTo(h.getApplicationDate()) <= 0) {
					retVal.add(h);
				}
			}
		}
		
		return retVal;
	}
	
	@GET
	@Path("createAuto")	
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public void createAdministratorAuto()
	{
		repo.setBasePath("C:\\Users\\KORISNIK\\Desktop\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		trainerRepo.setBasePath("C:\\Users\\KORISNIK\\Desktop\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		trainingRepo.setBasePath("C:\\Users\\KORISNIK\\Desktop\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		customerRepo.setBasePath("C:\\Users\\KORISNIK\\Desktop\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		
		
		Trainer trainer = trainerRepo.read("2022-06-19T21:32:21.213536");
		Training training = trainingRepo.read("1");
		Customer customer = customerRepo.read("2022-06-17T16:43:25.833348900");
		HistoryOfAllTrainings t1 = new HistoryOfAllTrainings("1",LocalDateTime.of(2022, 6, 2, 14, 0), training, customer, trainer);
		HistoryOfAllTrainings t2 = new HistoryOfAllTrainings("2", LocalDateTime.of(2022, 6, 4, 18, 0), training, customer, trainer);
		HistoryOfAllTrainings t3 = new HistoryOfAllTrainings("3", LocalDateTime.of(2022, 7, 3, 20, 0), training, customer, trainer);
		HistoryOfAllTrainings t4 = new HistoryOfAllTrainings("2", LocalDateTime.of(2022, 6, 5, 18, 0), training, customer, trainer);
		
//		Map<String, HistoryOfAllTrainings> mapa = new HashMap<String, HistoryOfAllTrainings>();
//		mapa.put(t1.getId(), t1);
//		repo.writeFile(mapa);
		repo.create(t4);
	}
}
