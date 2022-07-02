package services;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.CreateTrainingDTO;
import model.IdGenerator;
import model.SportObject;
import model.Trainer;
import model.Training;
import repository.SportObjectRepository;
import repository.TrainerRepository;
import repository.TrainingRepository;

@Path("trainings")
public class TrainingService {
	
	TrainingRepository repo = new TrainingRepository();
	SportObjectRepository sportObjectRepo = new SportObjectRepository();
	TrainerRepository trainerRepo = new TrainerRepository();
	
	@Context
	ServletContext ctx;
	
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("trainings") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("trainings", new TrainingService());
		}
	}
	
	@POST
	@Path("createTraining")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Training createTraining(CreateTrainingDTO trainingDTO)
	{
		repo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		sportObjectRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		trainerRepo.setBasePath("WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		
		SportObject sportObject = sportObjectRepo.read(trainingDTO.getSportObjectId());
		Trainer trainer = trainerRepo.read(trainingDTO.getTrainerId());
		
		Training training = new Training(IdGenerator.getInstance().generateId(repo.getKeySet(), 5), trainingDTO.getName(), trainingDTO.getType(), sportObject, trainingDTO.getDurationInMinutes(), trainer, trainingDTO.getDescription(), trainingDTO.getImage());
		repo.create(training);
		
		return training;
	}
	
//	@GET
//	@Path("createAuto")	
//	@Produces(MediaType.TEXT_PLAIN)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public void createAdministratorAuto()
//	{
//		repo.setBasePath("C:\\Users\\KORISNIK\\Desktop\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
//		repoSO.setBasePath("C:\\Users\\KORISNIK\\Desktop\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
//		repoT.setBasePath("C:\\Users\\KORISNIK\\Desktop\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
//				
//		SportObject sportObject = repoSO.read("4");
//		Trainer trainer = repoT.read("2022-06-19T22:17:20.869546400");
//		Training training = new Training("3", "PULL trening", "Personalni", sportObject, 60, trainer, "Trening za poveæanje fizièke snage", "images" + File.separator + "pull.png");
//		Map<String, Training> mapa = new HashMap<String, Training>();
//		mapa.put(training.getId(), training);
//		//repo.writeFile(mapa);
//		repo.create(training);
//		//System.out.println("Created new customer: ");
//	}
	
}
