package services;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import model.Administrator;
import repository.AdministratorRepository;

@Path("administrators")
public class AdministratorService {

AdministratorRepository repo = new AdministratorRepository();
	
	@Context
	ServletContext ctx;
	
	@SuppressWarnings("unused")
	public void init() {
		if (ctx.getAttribute("administrators") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("administrators", new AdministratorService());
		}
	}
	
	@POST
	@Path("update")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Administrator updateAdministrator(Administrator administrator) {
		repo.setBasePath("C:\\Users\\KORISNIK\\Desktop\\WebProgramiranje-PredmetniProjekat\\projekat\\VueWebShop\\src\\data\\");
		repo.update(administrator);
		return administrator;
	}
	
//	@GET
//	@Path("createAuto")	
//	@Produces(MediaType.TEXT_PLAIN)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public void createAdministratorAuto()
//	{
//		//repo.setBasePath(getDataDirPath());
//				
//		Administrator newCustomer = new Administrator("Miki", "465466", "Milos", "Milic", Gender.Male, new Date(1955,8,25));
//		Map<String, Administrator> mapa = new HashMap<String, Administrator>();
//		mapa.put(newCustomer.getId(), newCustomer);
//		repo.writeFile(mapa);
//		
//		System.out.println("Created new customer: ");
//	}
}
