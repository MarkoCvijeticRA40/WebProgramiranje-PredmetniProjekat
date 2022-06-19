package services;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import model.Customer;
import model.Trainer;
import repository.TrainerRepository;

@Path("trainers")
public class TrainerService {

	TrainerRepository repo = new TrainerRepository();
	
	@Context
	ServletContext ctx;
	
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("trainers") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("trainers", new AdministratorService());
		}
	}
	
	@POST
	@Path("update")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Trainer updateTrainer(Trainer trainer) {
		repo.setBasePath("C:\\Users\\KORISNIK\\Desktop\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		repo.update(trainer);
		return trainer;
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
}
